import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import main.AuthSuccessHandler;

public class AuthSuccessHandlerTest 
{
	private AuthSuccessHandler handler;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Method m;
	
	@Before
	public void constructor() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
	{
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		handler = new AuthSuccessHandler();
		
		//-- reflection
		Method m = AuthSuccessHandler.class.getDeclaredMethod("determineTargetUrl", HttpServletRequest.class,HttpServletResponse.class);
		m.setAccessible(true);
	}
	
	@Test
	public void determineTargetUrlTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
//		String s =(String) m.invoke(handler, request, response);
	}

}
