package com.angular.spring.config;


import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import static org.hibernate.cfg.Environment.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@EnableWebSecurity
@ComponentScans(value = { @ComponentScan("com.angular.spring.dao"),
		@ComponentScan("com.angular.spring.service")})

public class AppConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private Environment env;
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean =new LocalSessionFactoryBean();
		Properties props = new Properties();
		props.put(DRIVER,env.getProperty("mysql.driver"));
		props.put(URL,env.getProperty("mysql.url"));
		props.put(USER,env.getProperty("mysql.user"));
		props.put(PASS,env.getProperty("mysql.password"));
		
		
		
		props.put(SHOW_SQL,env.getProperty("hibernate.show_sql"));
		props.put(HBM2DDL_AUTO,env.getProperty("hibernate.hbm2ddl.auto"));
		props.put(C3P0_MIN_SIZE,env.getProperty("hibernate.c3p0.min_size"));
		props.put(C3P0_MAX_SIZE,env.getProperty("hibernate.c3p0.max_size"));
		props.put(C3P0_ACQUIRE_INCREMENT,env.getProperty("hibernate.c3p0.acquire_increment"));
		props.put(C3P0_TIMEOUT,env.getProperty("hibernate.c3p0.timeout"));
		props.put(C3P0_MAX_STATEMENTS,env.getProperty("hibernate.c3p0.max_statements"));
		
		
		
		factoryBean.setHibernateProperties(props);
		factoryBean.setPackagesToScan("com.angular.spring.model");
		
		return factoryBean;
	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
		
			}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("Sasi").password("Rekha").roles("USER");
	}
	
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests().antMatchers("/api/").hasRole("USER").and().httpBasic();
		
	}
	
	
}