package org.example.config;


import org.example.models.Depot;
import org.example.models.Good;
import org.example.models.GoodGroup;
import org.example.repositories.*;
import org.example.services.DatabaseService;
import org.example.services.JsonMapperService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages="org.example.controllers")
public class WebConfig implements WebMvcConfigurer {
    @Bean
    @Scope(value= ConfigurableBeanFactory.SCOPE_SINGLETON)
    public DatabaseService getDatabaseService(){
        return new DatabaseService();
    }

    @Bean
    @Scope(value= ConfigurableBeanFactory.SCOPE_SINGLETON)
    public JsonMapperService getJsonMapper(){
        return new JsonMapperService();
    }

    @Bean
    @Scope(value= ConfigurableBeanFactory.SCOPE_SINGLETON)
    public IUserRepository getUserRepository(){return new UserRepository(getDatabaseService());
    }
    @Bean
    @Scope(value= ConfigurableBeanFactory.SCOPE_SINGLETON)
    public IBaseRepository<Depot> getDepotRepository(){return new DepotRepository(getDatabaseService());
    }
    @Bean
    @Scope(value= ConfigurableBeanFactory.SCOPE_SINGLETON)
    public IBaseRepository<Good> getGoodRepository(){return new GoodRepository(getDatabaseService());
    }
    @Bean
    @Scope(value= ConfigurableBeanFactory.SCOPE_SINGLETON)
    public IBaseRepository<GoodGroup> getGroupRepository(){return new GoodGroupRepository(getDatabaseService());
    }

}
