package ru.novand.orientexpress.javaconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@PropertySource(value = "classpath:util.properties") //<context:property-placeholder location=".." />
//@EnableScheduling //need for scheduling TODO
public class ApplicationConfig {

    /**
     * @PropertySource annotation does not automatically
     * we need to initialize PropertySourcesPlaceholderConfigurer.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Autowired
    private Environment env;
    private static final String JPA_PACKAGES = "ru.novand.orientexpress.domain";

//    @Value("${jdbc.hsqldb.driverClass}")
//    private String driverClass;
//    @Value("${jdbc.hsqldb.url}")
//    private String jdbcUrl;
//    @Value("${jdbc.hsqldb.username}")
//    private String jdbcUserName;
//    @Value("${jdbc.hsqldb.password}")
//    private String jdbcPassword;

    @Value("${jdbc.mysql.driverClass}")
    private String driverClass;
    @Value("${jdbc.mysql.url}")
    private String jdbcUrl;
    @Value("${jdbc.mysql.username}")
    private String jdbcUserName;
    @Value("${jdbc.mysql.password}")
    private String jdbcPassword;

    @Value("classpath:dbschema.sql")
    private Resource dbschemaSqlScript;
    @Value("classpath:test-data.sql")
    private Resource testDataSqlScript;

    /**
     * <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
     *
     */
//    @Bean(name = "dataSource")
//    public DriverManagerDataSource getDriverManagerDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(driverClass);
//        dataSource.setUrl(jdbcUrl);
//        dataSource.setUsername(jdbcUserName);
//        dataSource.setPassword(jdbcPassword);
//        return dataSource;
//    }

    //new
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUserName);
        dataSource.setPassword(jdbcPassword);
        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource());
        initializer.setDatabasePopulator(getDatabasePopulator());
        return initializer;
    }

    private DatabasePopulator getDatabasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(dbschemaSqlScript);
        populator.addScript(testDataSqlScript);
        return populator;
    }

    /**
     * <bean id="entityManagerFactory"
     class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean" >
     */
    //new
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] {"ru.novand.orientexpress.domain.entities"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    //new
    @Bean
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    //new
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    //new
    Properties additionalProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        jpaProperties.put("hibernate.show_sql",true);
        jpaProperties.put("hibernate.use_sql_comments",true);
        jpaProperties.put("hibernate.format_sql","true");
        jpaProperties.put("hibernate.enable_lazy_load_no_trans","true");

//        jpaProperties.put("hibernate.hbm2ddl.auto","validate");

        return jpaProperties;
    }



}
