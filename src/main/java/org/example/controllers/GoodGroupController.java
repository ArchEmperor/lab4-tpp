package org.example.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.models.Depot;
import org.example.models.GoodGroup;
import org.example.repositories.IBaseRepository;
import org.example.repositories.IUserRepository;
import org.example.services.JsonMapperService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/good_group",produces = MediaType.APPLICATION_JSON_VALUE)
public class GoodGroupController extends BaseController<GoodGroup> {
    public GoodGroupController(JsonMapperService jsonMapper, IUserRepository userRepository, IBaseRepository<GoodGroup> baseRepository) {
        super(jsonMapper, userRepository, baseRepository);
    }
    @Override
    public GoodGroup DeserializeBody(String requestBody) throws JsonProcessingException {
        return super.jsonMapper.Mapper.readValue(requestBody,GoodGroup.class);
    }
}
