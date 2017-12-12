import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.junit.Before;
import org.junit.Test;

import main.AuthSuccessHandler;
import main.AuthorityLogistician;

public class AuthSuccessHandlerTest 
{
	private AuthSuccessHandler handler;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	@Before
	public void constructor() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
	{
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		handler = new AuthSuccessHandler();
	}
	
	@Test
	public void shouldAuthenticateLogistic() throws IOException {
		//GIVEN
		org.springframework.security.core.Authentication authentication=mock(org.springframework.security.core.Authentication.class);

		List<AuthorityLogistician> auths= new ArrayList<>();
		auths.add(AuthorityLogistician.INSTANCE);
		doReturn(auths).when(authentication).getAuthorities();
		
		//WHEN
		handler.onAuthenticationSuccess(request, response, authentication);
		
		//THEN
		verify(response).sendRedirect("/logistics");
	}

}
