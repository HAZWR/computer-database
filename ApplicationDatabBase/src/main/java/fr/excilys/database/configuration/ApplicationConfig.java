package fr.excilys.database.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
@ComponentScan(basePackages = "fr.excilys.database")
@PropertySources({ @PropertySource("classpath:db.properties") })
public class ApplicationConfig extends AbstractContextLoaderInitializer {
	// Stores all the properties loaded by the @PropertySource
    @Autowired
    private Environment env;
    
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(ApplicationConfig.class);
		return applicationContext;

	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("driverClassName"));
		dataSource.setUrl(env.getProperty("jdbcUrl"));
		dataSource.setUsername(env.getProperty("dataSource.user"));
		dataSource.setPassword(env.getProperty("dataSource.password"));
		return dataSource;
		
	}

}