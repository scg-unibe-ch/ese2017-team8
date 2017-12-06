import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import main.*;
import main.Delivery.Status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
@AutoConfigureMockMvc
public class DriverMainViewControllerTest 
{
	@Autowired
	private MockMvc driver;
	
	
    @MockBean
	private DeliveryRepo deliveryRepo;
    @MockBean
	public ParcelRepo parcelRepo;

    @MockBean
	public UserRepo userRepo;
	
	private Delivery delivery;
	
	private Delivery submitTestDelivery;
	private User user;
//	private BindingResult r;
	
	@Before 
	public void constructor()
	{
//		driver = new DriverMainViewController();
		delivery = new Delivery(LocalDate.now(), LocalDate.now(), 1l, 1l, null, null, 1);
		submitTestDelivery = new Delivery(LocalDate.now(), LocalDate.now(), 1l, 1l, Status.attempted, null, 2);
		user = new User("Christiane T", "$2a$11$PwMXw8qBRz06bCEFxvUNQeDgKERPB3ZQjBfGoXNZ4nsv2X4cFMrUK", Arrays.asList(AuthorityDriver.instance));
		user.setId(1l);
		given(deliveryRepo.findByParcelId(delivery.getParcelId())).willReturn(delivery);
		given(userRepo.findByUsername("Christiane T")).willReturn(user);
		
		given(deliveryRepo.save(submitTestDelivery));
	}
	@Test
	public void deliverySubmitTest() throws Exception 
	{
//		driver.deliverySubmit(delivery, , model)
//		driver.perform(post("/driver").param("assignDelivery", submitTestDelivery)).andExpect(status().isOk());
		
		
		
//		when(delivery.getParcelId()).thenReturn(10L);
		verify(deliveryRepo.findByParcelId(delivery.getParcelId()));
	}
	
	@Test
	@WithMockUser(username="Christiane T", password="$2a$11$PwMXw8qBRz06bCEFxvUNQeDgKERPB3ZQjBfGoXNZ4nsv2X4cFMrUK", roles="DRIVER")
	public void showAllDeliveryDriverTest() throws Exception
	{
		driver.perform(get("/driver")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("deliveriesForDriver"))
		.andExpect(model().attribute("deliveriesForDriver", hasSize(0)));
		//...
		// driver.showAllDeliveryDriver();
	}
	
	@Test
	public void possibleParcelStatusDriverTest()
	{
		List<Delivery.Status> driverStatus = new ArrayList<>();
		driverStatus.add(Delivery.Status.attempted);
		driverStatus.add(Delivery.Status.delivered);
		//assertEquals(driverStatus, driver.possibleParcelStatusDriver());
	}
	
	@Test
	public void returnLoggedInNameTest()
	{
		//driver.returnLoggedInName();
		verify(SecurityContextHolder.getContext().getAuthentication());
	}

	
}
