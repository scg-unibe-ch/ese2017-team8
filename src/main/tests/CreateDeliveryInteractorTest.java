import main.Application;
import main.CreateDeliveryInteractor;
import main.Delivery;
import main.DeliveryRepo;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CreateDeliveryInteractorTest {

	@Autowired
	CreateDeliveryInteractor createDeliveryInteractor;

	@Autowired
	DeliveryRepo deliveryRepo;

	@Test
	public void testCreateDeliveryWithDate() {
		Long createdDeliveryId = createDeliveryInteractor.createScheduledDelivery("Hans Teschtet", new Long(1));
		Assert.assertTrue(deliveryRepo.findAll().size() > 0);
		Assert.assertEquals(1, deliveryRepo.findByDriver("Hans Teschtet").size());

		Delivery createdDelivery = deliveryRepo.findOne(createdDeliveryId);
		LocalDate expectedDate = LocalDate.now().plusDays(1);

		Assert.assertEquals(expectedDate, createdDelivery.getScheduledDate());
	}
}
