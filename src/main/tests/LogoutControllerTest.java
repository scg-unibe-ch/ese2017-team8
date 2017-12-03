

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import main.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
public class LogoutControllerTest 
{
	LogoutController lc;
	HttpServletRequest sRequest;
	HttpServletResponse sResponse;
	MockMvc mockMvc;
	
	@Before
	public void constructor()
	{
		lc = new LogoutController();
		sRequest = mock(HttpServletRequest.class);
		sResponse = mock(HttpServletResponse.class);
		mockMvc= MockMvcBuilders.standaloneSetup(lc).build();
	}
	@Test
	public void logoutTest() throws Exception 
	{
	 int status = mockMvc.perform(MockMvcRequestBuilders.get("/logout")).andReturn().getResponse().getStatus();
	 assertEquals(HttpStatus.FOUND.value(),status);
	 assertEquals(lc.logout(sRequest,sResponse), "redirect:/home?logout"); //trivial
	}

}
