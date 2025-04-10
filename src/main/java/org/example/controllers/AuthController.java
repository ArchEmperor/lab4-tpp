package org.example.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.models.User;
import org.example.repositories.IUserRepository;
import org.example.services.JsonMapperService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    public final JsonMapperService jsonMapper;
    public final IUserRepository userRepository;

    public AuthController(JsonMapperService jsonMapperService, IUserRepository userRepository) {
        this.jsonMapper = jsonMapperService;
        this.userRepository = userRepository;
    }
    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> SignUp(@RequestParam("username") String username, @RequestParam("password") String password) throws JsonProcessingException {
            User user = userRepository.GetNewUser(username,password);
            if(user==null)
                return new ResponseEntity<String>(HttpStatusCode.valueOf(401));
            String session_id=jsonMapper.Mapper.writeValueAsString(user);
            return new ResponseEntity<String>(session_id,HttpStatusCode.valueOf(200));
    }


    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> Login(@RequestParam("username") String username, @RequestParam("password") String password) throws JsonProcessingException {
        User user = userRepository.GetUser(username,password);
        if(user==null)
            return new ResponseEntity<String>(HttpStatusCode.valueOf(401));
        String session_id=jsonMapper.Mapper.writeValueAsString(user);
        return new ResponseEntity<String>(session_id,HttpStatusCode.valueOf(200));
    }
}
