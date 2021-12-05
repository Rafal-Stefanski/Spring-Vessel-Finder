package com.rafalstefanski.springvesselfinder.config;

import com.rafalstefanski.springvesselfinder.repository.VesselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    private DataSource dataSource;
    private VesselRepository vesselRepository;

    @Autowired
    public DbConfig(DataSource dataSource, VesselRepository vesselRepository) {
        this.dataSource = dataSource;
        this.vesselRepository = vesselRepository;
    }
}
