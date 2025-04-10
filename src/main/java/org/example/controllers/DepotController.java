package org.example.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.models.Depot;
import org.example.models.Good;
import org.example.repositories.IBaseRepository;
import org.example.repositories.IUserRepository;
import org.example.services.JsonMapperService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/depot",produces = MediaType.APPLICATION_JSON_VALUE)
public class DepotController extends BaseController<Depot> {
    public DepotController(JsonMapperService jsonMapper, IUserRepository userRepository, IBaseRepository<Depot> baseRepository) {
        super(jsonMapper, userRepository, baseRepository);
    }

    @Override
    public Depot DeserializeBody(String requestBody) throws JsonProcessingException {
        return super.jsonMapper.Mapper.readValue(requestBody,Depot.class);
    }
}
