package tests;

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
		delivery = new Delivery(new Date(2013, 3, 14), new Date(), (long)1, (long)2,Delivery.Status.scheduled);
		delivery.setId(3);
	}

	@Test
	public void toStringTest()
	{
		assertEquals(delivery.toString(), "Delivery - Id: 3");
	}
}