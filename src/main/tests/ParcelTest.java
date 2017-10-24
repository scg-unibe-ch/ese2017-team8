package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.*;

public class ParcelTest 
{
	private Parcel parcel;
	@Before
	public void constructor()
	{
		parcel = new Parcel(13.3, 4.0, 16.0, 44.3, false, true, "Do not keep above 20° C");
		parcel.setId(11);
	}
	
	@Test
	public void toStringTest()
	{
		assertEquals(parcel.toString(),"Parcel – Id: 11");
	}
}
