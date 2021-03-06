

import static org.junit.Assert.*;

import main.common.data.models.Parcel;
import org.junit.Before;
import org.junit.Test;

public class ParcelTest 
{
	private Parcel parcel;
	@Before
	public void constructor()
	{
		parcel = new Parcel(13.3, 4.0, 16.0, 44.3, false, true, "Do not keep above 20° C", "Menznau","6122","Heiseweg 2", "Hasel Brugger","");
		parcel.setId(new Long(11));
	}
	
	@Test
	public void toStringTest()
	{
		assertEquals(parcel.toString(),"Parcel – Id: 11");
	}
}
