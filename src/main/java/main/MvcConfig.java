package main;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This class is used to setup the web view.
 * It maps the controller to the paths.
 *
 * @author team8
 * @version 1.0
 */

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/logistics").setViewName("logistics");
		registry.addViewController("/neworder").setViewName("neworder");
		registry.addViewController("/changeorder").setViewName("changeorder");
		registry.addViewController("/parcelstats").setViewName("parcelstats");
		registry.addViewController("/driver").setViewName("driver");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/stylesheets/**").addResourceLocations("classpath:stylesheets/");
		registry.addResourceHandler("/media/**").addResourceLocations("classpath:media/");
	}
}
