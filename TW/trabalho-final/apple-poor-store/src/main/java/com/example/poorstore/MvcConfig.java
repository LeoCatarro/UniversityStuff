package com.example.poorstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/add-client").setViewName("new-client");
		registry.addViewController("/add-product").setViewName("new-product");
		registry.addViewController("/remove-product").setViewName("remove-product");
		registry.addViewController("/add-order").setViewName("new-order");
		registry.addViewController("/homepage").setViewName("homepage");
		registry.addViewController("/shopping-cart").setViewName("shopping-cart");
		registry.addViewController("/advanced-search").setViewName("advanced-search");
		registry.addViewController("/product").setViewName("product");
		registry.addViewController("/about").setViewName("about-this-page");
		registry.addViewController("/list-category").setViewName("list-category");
		registry.addViewController("/search").setViewName("search");
		registry.addViewController("/product/{id}").setViewName("product");
		registry.addViewController("/remove-product/{id}").setViewName("remove-product");

	}
}
