package com.backbase.moviesRankService.component;

import com.backbase.moviesRankService.config.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class InitialSetUp implements CommandLineRunner {
    @Autowired
    ApplicationProperties applicationProperties;

    @Override
    public void run(String... args) throws Exception {

    }
}
