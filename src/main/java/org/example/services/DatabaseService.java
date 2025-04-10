package org.example.services;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jdk.jfr.Percentage;
import org.springframework.stereotype.Service;

@Service

public class DatabaseService {
    public EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("jpa-hibernate-postgresql");

}
