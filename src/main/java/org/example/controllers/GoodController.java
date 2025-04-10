package org.example.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.models.Depot;
import org.example.models.Good;
import org.example.models.Roles;
import org.example.repositories.IBaseRepository;
import org.example.repositories.IUserRepository;
import org.example.services.JsonMapperService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="/good",produces = MediaType.APPLICATION_JSON_VALUE)
public class GoodController extends BaseController<Good>{
    public GoodController(JsonMapperService jsonMapper, IUserRepository userRepository, IBaseRepository<Good> baseRepository) {
        super(jsonMapper, userRepository, baseRepository);
    }
    @Override
    public Good DeserializeBody(String requestBody) throws JsonProcessingException {
        return super.jsonMapper.Mapper.readValue(requestBody,Good.class);
    }
}
