package com.anz.account.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.anz.account.domain")
@EnableJpaRepositories(basePackages = "com.anz.account.repository")
public class RepositoryConfig {
}
