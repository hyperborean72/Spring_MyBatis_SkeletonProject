package stepanov.andrey.configuration;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import stepanov.andrey.dao.CountryMapper;
import stepanov.andrey.service.AppService;

/**
 * @author: a.stepanov
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "stepanov.andrey")

@PropertySource(value = {"classpath:database.properties"})

public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        //check how to deal with setConnectionProperties()
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

//      //sqlSessionFactoryBean.setConfigLocation(new Resource("classpath:mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(new Resource[]{new ClassPathResource("/mappers/CountryMapper.xml")});
        return (SqlSessionFactory) sqlSessionFactoryBean.getObject();
    }

    @Bean
    public CountryMapper countryMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sessionTemplate.getMapper(CountryMapper.class);
    }

    @Bean
    public AppService appService() throws Exception {
        AppService appService = new AppService();
        appService.setCountryMapper(countryMapper());
        return appService;
    }

    /* useful in some cases
    *
    * @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("org/mybatis/spring/sample/db/database-schema.sql")
            .addScript("org/mybatis/spring/sample/db/database-test-data.sql")
            .build();
    }
    * */
}