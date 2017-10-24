

import static org.junit.Assert.*;
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
	public void constructor() 
	{
		Delivery delivery = new Delivery(null, new Date(), (long)1, (long)2,Delivery.Status.scheduled,"Judas");
		delivery.setId((long)(3));
	}

	@Test
	public void toStringTest()
	{
		assertEquals(delivery.toString(), "Delivery - Id: 3");
	}
}