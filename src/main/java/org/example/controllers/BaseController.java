package org.example.controllers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.models.Good;
import org.example.models.Roles;
import org.example.repositories.IBaseRepository;
import org.example.repositories.IUserRepository;
import org.example.services.JsonMapperService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public class BaseController<T> {
    public final JsonMapperService jsonMapper;
    public final IUserRepository userRepository;
    public final IBaseRepository<T> baseRepository;

    public BaseController(JsonMapperService jsonMapper, IUserRepository userRepository, IBaseRepository<T> baseRepository) {
        this.jsonMapper = jsonMapper;
        this.userRepository = userRepository;
        this.baseRepository = baseRepository;
    }
    @GetMapping()
    public ResponseEntity<String> get() throws JsonProcessingException {
        List<T> o = baseRepository.GetAll();
        if(o==null)
            return new ResponseEntity<String>(HttpStatusCode.valueOf(400));
        String obj=jsonMapper.Mapper.writeValueAsString(o);
        return new ResponseEntity<String>(obj,HttpStatusCode.valueOf(200));
        //return new ResponseEntity<String>("Get succeeded ", HttpStatusCode.valueOf(200));
    }

    @PostMapping()
    public ResponseEntity<String> add(@RequestBody String requestBody,@RequestHeader(value = "sessionId",required = false) String sessionId) throws JsonProcessingException {
        if(sessionId==null||!CheckIfRoleAtLeast(Roles.User, UUID.fromString(sessionId)))
            return new ResponseEntity<String>("Post not succeeded", HttpStatusCode.valueOf(401));
        baseRepository.Add(DeserializeBody(requestBody));

        return new ResponseEntity<String>("Post succeeded", HttpStatusCode.valueOf(200));
    }

    @PutMapping()
    public ResponseEntity<String> edit(@RequestBody String requestBody,@RequestHeader(value = "sessionId",required = false) String sessionId) throws JsonProcessingException {
        if(sessionId==null||!CheckIfRoleAtLeast(Roles.User, UUID.fromString(sessionId)))
            return new ResponseEntity<String>("Put not succeeded", HttpStatusCode.valueOf(401));
        baseRepository.Update(DeserializeBody(requestBody));
        return new ResponseEntity<String>("Put succeeded", HttpStatusCode.valueOf(200));
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestBody String requestBody,@RequestHeader(value = "sessionId",required = false) String sessionId) throws JsonProcessingException {
        if(sessionId==null||!CheckIfRoleAtLeast(Roles.Admin, UUID.fromString(sessionId)))
            return new ResponseEntity<String>("Delete not succeeded", HttpStatusCode.valueOf(403));
        baseRepository.Remove(jsonMapper.Mapper.readValue(requestBody, IdGet.class).getId());
        return new ResponseEntity<String>("Delete succeeded", HttpStatusCode.valueOf(200));
    }
    public T DeserializeBody(String requestBody) throws JsonProcessingException {
        return null;
    }
    public boolean CheckIfRoleAtLeast(Roles role, UUID sessionId){
        return userRepository.GetUserRole(sessionId).ordinal() >= role.ordinal();
    }
}
class IdGet{
    Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}