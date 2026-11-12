package com.apextech.property.senghang.common.config;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan({"com.apextech.property.senghang.entity"})
@EnableJpaRepositories({"com.apextech.property.senghang.repository"})
public class PersistenceConfiguration {}
