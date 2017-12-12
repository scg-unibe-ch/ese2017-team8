//
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//
//import antlr.Parser;
//import main.*;
//
//public class ParcelControllerTest
//{
//	private ParcelController controllers;
//	private Parcel parcel;
//	private BindingResult br;
//	private Model model;
//
//	@Before
//	public void constructor()
//	{
//		controllers =  new ParcelController();
//		parcel = mock(Parcel.class);
//		br = mock(BindingResult.class);
//		model= mock(Model.class);
//
//	}
//	//TODO
//	@Test
//	public void parcelSubmitTest()
//	{
//		assertEquals(controllers.parcelSubmit(parcel, br, model),"result");
//	}
//
//	@Test
//	public void parcelFormTest()
//	{
//		assertEquals(controllers.parcelForm(model), "logistics");
//	}
//}