import main.*;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CreateDeliveryInteractorTest {

	@Autowired
	CreateDeliveryInteractor createDeliveryInteractor;

	@Autowired
	DeliveryRepo deliveryRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	ParcelRepo parcelRepo;

	@Test
	public void testCreateDeliveryWithDate() {
		User testUser = new User("Hans Nötig", "$2a$11$PwMXw8qBRz06bCEFxvUNQeDgKERPB3ZQjBfGoXNZ4nsv2X4cFMrUK", Arrays.asList(AuthorityDriver.instance));
		userRepo.save(testUser);

		Parcel parcel = new Parcel();
		parcel.setComment("testobject");
		parcelRepo.save(parcel);

		Long createdDeliveryId = createDeliveryInteractor.createScheduledDelivery(testUser, parcel.getId());
		Assert.assertTrue(deliveryRepo.findAll().size() > 0);
		Assert.assertEquals(1, deliveryRepo.findByDriverId(new Long(1)).size());

		Delivery createdDelivery = deliveryRepo.findOne(createdDeliveryId);
		LocalDate expectedDate = LocalDate.now().plusDays(1);

		Assert.assertEquals(expectedDate, createdDelivery.getScheduledDate());
	}
}
