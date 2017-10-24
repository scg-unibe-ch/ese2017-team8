package tests;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import org.mockito.*;
import org.mockito.Matchers;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;


import main.*;

public class MvcConfigTest 
{
	private MvcConfig mc;
	private ViewControllerRegistry registry;
	private ViewControllerRegistration controller;
	
	@Before
	public void constructor()
	{
		mc = new MvcConfig();
		registry = mock(ViewControllerRegistry.class);
		controller = mock(ViewControllerRegistration.class);
	}
	@Test
	public void addViewControllersTest()
	{
		when(registry.addViewController("/home")).thenReturn(controller);
		when(registry.addViewController("/")).thenReturn(controller);
		when(registry.addViewController("/login")).thenReturn(controller);
		when(registry.addViewController("/logistics")).thenReturn(controller);
		when(registry.addViewController("/driver")).thenReturn(controller);
		
		mc.addViewControllers(registry);
		
//		doThrow(new Exception()).when(registry.addViewController(AdditionalMatchers.not(Matchers.eq("/home"))));
//		doThrow(new Exception()).when(registry.addViewController(s));
		
		
//		doThrow(new Exception()).when(homeController).setViewName(AdditionalMatchers.not(Matchers.eq("home")));
//		registry.addViewController("/home").setViewName("home");
	}
	

	

}
