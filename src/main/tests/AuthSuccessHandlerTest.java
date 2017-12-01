import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

import main.AuthSuccessHandler;
import main.AuthorityLogistician;

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
		
//		//-- reflection
//		Method m = AuthSuccessHandler.class.getDeclaredMethod("determineTargetUrl", HttpServletRequest.class,HttpServletResponse.class);
//		m.setAccessible(true);
	}
	
	@Test
	public void shouldAuthenticateLogistic() throws IOException {
		//GIVEN
		org.springframework.security.core.Authentication authentication=mock(org.springframework.security.core.Authentication.class);
		Collection<? extends GrantedAuthority> authentications=new ArrayList<>(Arrays.asList(AuthorityLogistician.INSTANCE));
		List<AuthorityLogistician> auths= new ArrayList<>();
		auths.add(AuthorityLogistician.INSTANCE);
		doReturn(auths).when(authentication).getAuthorities();
		//when(authentication.getAuthorities()).thenReturn((Collection<? extends GrantedAuthority>) auths);
		
		//WHEN
		handler.onAuthenticationSuccess(request, response, authentication);
		
		//THEN
		verify(response).sendRedirect("/logistics");
	}
//	@Test
//	public void determineTargetUrlTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
//	{
////		String s =(String) m.invoke(handler, request, response);
//	} TODO: spock

}
