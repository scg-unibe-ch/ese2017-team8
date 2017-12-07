

import static org.junit.Assert.*;

import java.time.LocalDate;

import main.common.data.models.Delivery;
import org.junit.Before;
import org.junit.Test;

public class DeliveryTest 
{
	private Delivery delivery;
	
	@Before
	public void constructor() {
		delivery = new Delivery(null, LocalDate.now(), (long)1, (long)2,Delivery.Status.scheduled,new Long(1), 0);
		delivery.setId((long)(3));
	}

	@Test
	public void toStringTest()
	{
		assertEquals(delivery.toString(), "Delivery - Id: 3, ParcelId: 2");
	}

}
