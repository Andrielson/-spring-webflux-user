package com.github.andrielson.spring.webflux.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;


@RequiredArgsConstructor
@Service
public class DataSetupService implements CommandLineRunner {

    private final R2dbcEntityTemplate entityTemplate;

    @Value("classpath:h2/init.sql")
    private Resource initSql;

    @Override
    public void run(String... args) throws Exception {
        var query = StreamUtils.copyToString(initSql.getInputStream(), StandardCharsets.UTF_8);
        System.out.println(query);
        entityTemplate
                .getDatabaseClient()
                .sql(query)
                .then()
                .subscribe();
    }
}
