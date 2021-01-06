package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@Controller
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
	
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/view/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        registry.viewResolver(resolver);
	}
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/admin/")
         		.addResourceLocations("/view/admin/");
		 
		 registry.addResourceHandler("/admin/")
  		.addResourceLocations("/view/admin/");
		 
		 registry.addResourceHandler("/admin/")
  		.addResourceLocations("/view/admin/");
		 
		 registry.addResourceHandler("/admin/")
  		.addResourceLocations("/view/admin/");
	 }
}
