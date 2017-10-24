

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import main.*;

public class LogoutControllerTest 
{
	LogoutController lc;
	HttpServletRequest sRequest;
	HttpServletResponse sResponse;
	
	@Before
	public void constructor()
	{
		lc = new LogoutController();
		sRequest = mock(HttpServletRequest.class);
		sResponse = mock(HttpServletResponse.class);
	}
	@Test
	public void logoutTest() 
	{
		assertEquals(lc.logout(sRequest,sResponse), "redirect:/home?logout");
	}

}
