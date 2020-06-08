package com.test.hplus;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.XmlViewResolver;

import com.test.hplus.converters.StringToEnumConverter;
import com.test.hplus.interceptors.LoggingInterceptor;

@Configuration
@ComponentScan(basePackages = "com.test")
@EntityScan("com.test.beans")
@EnableJpaRepositories("com.test.repository")
public class ApplicationConfig extends WebMvcConfigurationSupport {
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**", "/images/**").addResourceLocations("classpath:/static/css/",
				"classpath:/static/images/");
	}

	// This is the first view resolver that will try to displays the views
	// If it fails, "InternalResourceViewResolver" will respond in displaying the
	// view
	@Bean
	public XmlViewResolver xmlViewResolver() {
		XmlViewResolver viewResolver = new XmlViewResolver();
		viewResolver.setLocation(new ClassPathResource("views.xml"));
		viewResolver.setOrder(1);
		return viewResolver;

	}
	
	// This is the second view resolver in case the first one fails
	@Bean
	public InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setOrder(2);
		return viewResolver;

	}

	@Override
	protected void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToEnumConverter());
	}

	@Override
	protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setDefaultTimeout(5000);
		configurer.setTaskExecutor(mvcTaskExecutor());
	}

	@Bean    
	public AsyncTaskExecutor mvcTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setThreadNamePrefix("hplusapp-thread-");
		return threadPoolTaskExecutor;
	} 
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/*");
	}

}
