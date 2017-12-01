
/**
 * Customer unit test
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.*;

public class CustomerTest 
{
	private Customer customer;
	
	@Before
	public void constructor()
	{
		customer = new Customer("Ben","Musterman","Musterstrasse","430c","3012","+41 71 23 456", "mustermann@mustermail.com");
		customer.setId(0);
	}
	
	@Test
	public void toStringTest()
	{
		assertEquals(customer.toString(),"Customer[id=0, firstName='Ben', lastName='Musterman']");
	}
}
