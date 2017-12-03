import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;

import main.*;

public class DriverMainViewControllerTest 
{
	private DriverMainViewController driver;
	private Delivery delivery;
//	private BindingResult r;
	
	@Before 
	public void constructor()
	{
		driver = new DriverMainViewController();
		delivery = mock(Delivery.class);
		
	}
	@Test
	public void deliverySubmitTest() 
	{
//		driver.deliverySubmit(delivery, , model) ??
		
		
		
//		when(delivery.getParcelId()).thenReturn(10L);
		verify(driver.deliveryRepo.findByParcelId(delivery.getParcelId()));
	}
	
	@Test
	public void showAllDeliveryDriverTest()
	{
		Authentication authentication = mock (Authentication.class);
		//...
		driver.showAllDeliveryDriver();
	}
	
	@Test
	public void possibleParcelStatusDriverTest()
	{
		List<Delivery.Status> driverStatus = new ArrayList<>();
		driverStatus.add(Delivery.Status.attempted);
		driverStatus.add(Delivery.Status.delivered);
		assertEquals(driverStatus, driver.possibleParcelStatusDriver());
	}
	
	@Test
	public void returnLoggedInNameTest()
	{
		driver.returnLoggedInName();
		verify(SecurityContextHolder.getContext().getAuthentication());
	}

	
}
