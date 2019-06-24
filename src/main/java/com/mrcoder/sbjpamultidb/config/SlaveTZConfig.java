package com.mrcoder.sbjpamultidb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "slaveTZEntityManagerFactory",
        transactionManagerRef = "slaveTZTransactionManager",
        basePackages = {"com.mrcoder.sbjpamultidb.entity.slave"})//repository的目录
public class SlaveTZConfig {

    @Autowired
    @Qualifier("slaveTZDataSource")
    private DataSource slaveDataSource;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean(name = "slaveTZEntityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return slaveTZEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Resource
    private JpaProperties jpaProperties;


    @Bean(name = "slaveTZEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean slaveTZEntityManagerFactory(EntityManagerFactoryBuilder builder) {

        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        properties.put("hibernate.hbm2ddl.auto","none");
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        return builder
                .dataSource(slaveDataSource)
                .packages("com.mrcoder.sbjpamultidb.entity.slave")
                .persistenceUnit("slaveTZPersistenceUnit")
                .properties(properties)
                .build();
    }

    @Bean(name = "slaveTZTransactionManager")
    PlatformTransactionManager slaveTZTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(slaveTZEntityManagerFactory(builder).getObject());
    }

    @Bean(name = "slaveTZJdbcTemplate")
    @Qualifier("slaveTZJdbcTemplate")
    public JdbcTemplate slaveTZJdbcTemplate(@Qualifier("slaveTZDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}
