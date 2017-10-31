

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import main.*;
import main.Delivery.Status;

public class DeliveryTest 
{
	private Delivery delivery;
	
	@Before
	public void constructor() {
		Delivery delivery = new Delivery(null, LocalDate.now(), (long)1, (long)2,Delivery.Status.scheduled,new Long(1));
		delivery.setId((long)(3));
	}

	@Test
	public void toStringTest()
	{
		assertEquals(delivery.toString(), "Delivery - Id: 3");
	}

}
