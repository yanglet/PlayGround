package com.example.mysql.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.example.mysql.repository"],
    entityManagerFactoryRef = "customEntityManagerFactory",
    transactionManagerRef = "customTransactionManager"
)
class CustomDataSourceConfig(private val jpaProperties: JpaProperties) {

    @Bean
    @ConfigurationProperties(prefix = "datasource.yanglet.master")
    fun masterDataSource(): DataSource {
        return DataSourceBuilder.create().type(HikariDataSource::class.java).build()
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.yanglet.slave")
    fun slaveDataSource(): DataSource =
        DataSourceBuilder.create().type(HikariDataSource::class.java).build()

    @Bean
    fun routingDataSource() = RoutingDataSource().apply {
        setTargetDataSources(
            hashMapOf<Any, Any>(
                "master" to masterDataSource(),
                "slave" to slaveDataSource()
            )
        )
        setDefaultTargetDataSource(masterDataSource())
    }

    @Bean
    @DependsOn("routingDataSource")
    fun lazyDataSource() = LazyConnectionDataSourceProxy(routingDataSource())

    @Bean("customEntityManagerFactory")
    @Primary
    fun customEntityManagerFactory() = LocalContainerEntityManagerFactoryBean().apply {
        dataSource = lazyDataSource()
        setPackagesToScan("com.example.mysql.repository.entity")
        jpaVendorAdapter = HibernateJpaVendorAdapter().apply {
            setShowSql(jpaProperties.isShowSql)
            setGenerateDdl(jpaProperties.isGenerateDdl)
            setJpaPropertyMap(jpaProperties.properties)
        }
        persistenceUnitName = "entityManager"
    }

    @Bean("customTransactionManager")
    @Primary
    fun customTransactionManager(): JpaTransactionManager {
        return JpaTransactionManager(customEntityManagerFactory().`object`!!)
    }

}