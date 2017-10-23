package tests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import main.AuthSuccessHandler;

public class AuthSuccessHandlerTest 
{
	private AuthSuccessHandler handler;
	Method m;
	
	@Before
	public void constructor() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
	{
		//	TODO
//		handler = new AuthSuccessHandler();
//		m= handler.getClass().getDeclaredMethod("determineTargetUrl", new Class<?>[0] );  //access the protected method determineTargetUrl
//		m.invoke(handler);
	}
	
	@Test
	public void determineTargetUrlTest()
	{
		
	}

}
