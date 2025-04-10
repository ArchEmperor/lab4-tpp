package org.example.repositories;

import org.example.models.Roles;
import org.example.models.User;
import org.example.services.DatabaseService;
import org.example.services.JsonMapperService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public interface IUserRepository{
    User GetNewUser(String username, String password);
    Roles GetUserRole(UUID sessionId);
    User GetUser(String username,String password);

}
