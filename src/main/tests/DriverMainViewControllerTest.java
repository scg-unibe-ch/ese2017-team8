import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.AuthorityDriver;
import main.common.data.repositories.DeliveryRepo;
import main.common.data.repositories.ParcelRepo;
import main.common.data.repositories.UserRepo;
import main.common.business.finishtour.FinishTourUseCases;
import main.common.data.models.Delivery;
import main.common.data.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import main.*;
import main.common.data.models.Delivery.Status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
@AutoConfigureMockMvc
public class DriverMainViewControllerTest 
{
	@Autowired
	private MockMvc driver;
	
	
    @MockBean
	private DeliveryRepo deliveryRepo;
    
//    @MockBean
//   	private FinishTourUseCases finishTourWorker;
    
    @MockBean
	public ParcelRepo parcelRepo;

    @MockBean
	public UserRepo userRepo;
	
	private Delivery delivery;
	
	private Delivery submitTestDelivery;
	private User user;
	
	@Before 
	public void constructor()
	{
//		driver = new DriverMainViewController();
		delivery = new Delivery(LocalDate.now(), LocalDate.now(), 1l, 1l, Status.cancelled, null, 1);
		submitTestDelivery = new Delivery(LocalDate.now(), LocalDate.now(), 1l, 1l, Status.attempted, null, 2);
		delivery.setId(1l);
		submitTestDelivery.setId(1l);
		user = new User("Christiane T", "$2a$11$PwMXw8qBRz06bCEFxvUNQeDgKERPB3ZQjBfGoXNZ4nsv2X4cFMrUK", Arrays.asList(AuthorityDriver.instance));
		user.setId(1l);
		given(deliveryRepo.findByParcelId(delivery.getParcelId())).willReturn(delivery);
		given(userRepo.findByUsername("Christiane T")).willReturn(user);
		
	//	given(deliveryRepo.save(submitTestDelivery));
	}
	@Test
	@WithMockUser(username="Christiane T", password="$2a$11$PwMXw8qBRz06bCEFxvUNQeDgKERPB3ZQjBfGoXNZ4nsv2X4cFMrUK", roles="DRIVER")
	public void deliverySubmitTest() throws Exception 
	{
		String c = submitTestDelivery.getStatus().toString();
		String c2 = String.valueOf(submitTestDelivery.getStatus());

		driver.perform(
				post("/driver").with(csrf())
				.param("parcelId", String.valueOf(submitTestDelivery.getId()))
				.param("sequence",  String.valueOf(submitTestDelivery.getSequence()))
				.param("status", String.valueOf(submitTestDelivery.getStatus()))
				)
		
		
		.andExpect(status().is(200));
		
//		verify(deliveryRepo, times(1)).save(submitTestDelivery);
		
//		when(delivery.getParcelId()).thenReturn(10L);
		//verify(deliveryRepo.findByParcelId(delivery.getParcelId()));
	}
	
	@Test
	@WithMockUser(username="Christiane T", password="$2a$11$PwMXw8qBRz06bCEFxvUNQeDgKERPB3ZQjBfGoXNZ4nsv2X4cFMrUK", roles="DRIVER")
	public void showAllDeliveryDriverTest() throws Exception
	{
		driver.perform(get("/driver")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("deliveriesForDriver"))
		.andExpect(model().attribute("deliveriesForDriver", hasSize(0)));
	}
	
	@Test
	public void possibleParcelStatusDriverTest()
	{
		List<Delivery.Status> driverStatus = new ArrayList<>();
		driverStatus.add(Delivery.Status.attempted);
		driverStatus.add(Delivery.Status.delivered);
		//assertEquals(driverStatus, driver.possibleParcelStatusDriver());
	}
	
//	@Test
//	public void returnLoggedInNameTest()
//	{
//		//driver.returnLoggedInName();
//		verify(SecurityContextHolder.getContext().getAuthentication());
//	}

	
}
