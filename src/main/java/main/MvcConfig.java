package main;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This class is used to setup the web view.
 * It maps the controllers to the paths.
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
		registry.addViewController("/driverstatsoverview").setViewName("driverstatsoverview");
		registry.addViewController("/driverstats").setViewName("driverstats");
		registry.addViewController("/logistics").setViewName("reactivate");
		registry.addViewController("/archive").setViewName("archive");
		registry.addViewController("/canceled").setViewName("canceled");
		registry.addViewController("/driver").setViewName("driver");
		registry.addViewController("/error").setViewName("error");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/stylesheets/**").addResourceLocations("classpath:stylesheets/");
		registry.addResourceHandler("/media/**").addResourceLocations("classpath:media/");
	}
}
