package br.org.pti.integrator.application.datasources;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 2.0.0, 20/02/2020
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "br.org.pti.integrator.domain.repositories"
)
@RequiredArgsConstructor
@EnableTransactionManagement
public class DataSourceConfig {

    /**
     * @return DataSource
     */
    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * @param builder    EntityManagerFactoryBuilder
     * @param dataSource DataSource
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(final EntityManagerFactoryBuilder builder, final @Qualifier("dataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("br.org.pti.integrator.domain")
                .persistenceUnit("oracle")
                .build();
    }

    /**
     * @param entityManagerFactory EntityManagerFactory
     * @return PlatformTransactionManager
     */
    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
