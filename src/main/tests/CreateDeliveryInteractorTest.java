import main.*;
import main.Delivery.Status;

import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = Application.class)
public class CreateDeliveryInteractorTest {

	@Autowired
	@InjectMocks
	CreateDeliveryInteractor createDeliveryInteractor;

	
	@Mock
	DeliveryRepo deliveryRepo;

	@Mock
	UserRepo userRepo;

	@Mock
	ParcelRepo parcelRepo;

	@Test
	public void testCreateDeliveryWithDate() {
		//GIVEN 
		Delivery delivery = new Delivery(LocalDate.now().plusDays(1), LocalDate.now(), 1L, 1L, Delivery.Status.scheduled, 1L, 1);
	List<Delivery> allDeliveries=Arrays.asList(delivery);
		when(deliveryRepo.save(any(Delivery.class))).thenReturn(delivery);
		when(deliveryRepo.findAll()).thenReturn(allDeliveries);
		when(deliveryRepo.findByDriverId(any())).thenReturn(allDeliveries);
		when(deliveryRepo.findOne(any(Long.class))).thenReturn(delivery);
		
		User testUser = new User("Hans Nötig", "$2a$11$PwMXw8qBRz06bCEFxvUNQeDgKERPB3ZQjBfGoXNZ4nsv2X4cFMrUK", Arrays.asList(AuthorityDriver.instance));
		userRepo.save(testUser);

		Parcel parcel = new Parcel();
		parcel.setComment("testobject");
		parcelRepo.save(parcel);

		//WHEN
		Long createdDeliveryId = createDeliveryInteractor.createScheduledDelivery(testUser, parcel.getId());
		
		//THEN
		Assert.assertTrue(deliveryRepo.findAll().size() > 0);
		Assert.assertEquals(1, deliveryRepo.findByDriverId(new Long(1)).size());

		Delivery createdDelivery = deliveryRepo.findOne(createdDeliveryId);
		LocalDate expectedDate = LocalDate.now().plusDays(1);

		Assert.assertEquals(expectedDate, createdDelivery.getScheduledDate());
			//Set better matching
		verify(deliveryRepo).save(any(Delivery.class));
	}
}
