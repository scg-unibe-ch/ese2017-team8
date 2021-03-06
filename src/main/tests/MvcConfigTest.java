

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.Matchers;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;


import main.*;

@RunWith(MockitoJUnitRunner.class)
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
		when(registry.addViewController("/neworder")).thenReturn(controller);
		when(registry.addViewController("/changeorder")).thenReturn(controller);
		when(registry.addViewController("/parcelstats")).thenReturn(controller);
		when(registry.addViewController("/driverstatsoverview")).thenReturn(controller);
		when(registry.addViewController("/driverstats")).thenReturn(controller);
		when(registry.addViewController("/logistics")).thenReturn(controller);
		when(registry.addViewController("/archive")).thenReturn(controller);
		when(registry.addViewController("/driver")).thenReturn(controller);
		when(registry.addViewController("/canceled")).thenReturn(controller);
		when(registry.addViewController("/error")).thenReturn(controller);
		
		mc.addViewControllers(registry);

		verify(registry,times(14)).addViewController(anyString());

	}
		
}
