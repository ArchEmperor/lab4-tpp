package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JsonMapperService {
    public final ObjectMapper Mapper = new ObjectMapper();
}
