package main.logistics.business;

import main.AuthorityDriver;
import main.common.business.getcurrentuser.GetCurrentUserUseCases;
import main.logistics.presentation.viewmodels.DriverListModel;
import main.common.data.models.ParcelStat;
import main.common.business.getcurrentuser.GetCurrentUserWorker;
import main.common.business.createdelivery.CreateDeliveryUseCases;
import main.common.data.repositories.DeliveryRepo;
import main.common.data.repositories.ParcelRepo;
import main.common.data.repositories.ParcelStatRepo;
import main.common.data.repositories.UserRepo;
import main.common.data.models.Delivery;
import main.common.data.models.Parcel;
import main.common.data.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LogisticsMainInteractor implements LogisticsMainUseCases {

	@Autowired
	private DeliveryRepo deliveryRepo;

	@Autowired
	private ParcelRepo parcelRepo;

	@Autowired
	private ParcelStatRepo parcelStatRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CreateDeliveryUseCases createDeliveryWorker;

	@Autowired
	private GetCurrentUserUseCases getCurrentUserUseCases;

	/**
	 * Gets all parcels that do not have an associated delivery with status "Archived".
	 *
	 * @return List of parcels.
	 */
	public List<Parcel> getActiveParcels() {
		List<Parcel> allParcels = parcelRepo.findAll();
		List<Parcel> filteredParcels = new ArrayList<Parcel>();

		for (Parcel parcel : allParcels) {
			Delivery del = deliveryRepo.findByParcelId(parcel.getId());

			if (del == null || del.getStatus() == null || !(del.getStatus().equals(Delivery.Status.archived))) {
				filteredParcels.add(parcel);
			}
		}
		return filteredParcels;
	}

	public List<DriverListModel> getDriversList() {
		List<User> driverList = userRepo.findAllByAuthoritiesContains(AuthorityDriver.instance);

		List<DriverListModel> viewModel = new ArrayList<DriverListModel>();

		for (User u: driverList) {
			DriverListModel driverListModel = new DriverListModel();
			driverListModel.setDriver(u);

			List<Delivery> deliveriesForDriver = deliveryRepo.findByDriverId(u.getId());
			List<Long> parcelIds = deliveriesForDriver.stream().map(Delivery::getParcelId).collect(Collectors.toList());
			driverListModel.setParcelIds(parcelIds);


			viewModel.add(driverListModel);
		}

		return viewModel;
	}

	public void didSubmitDelivery(User driver, Long parcelId) {
		assert(driver.getAuthorities().contains(AuthorityDriver.instance));

		User currentUser = getCurrentUserUseCases.getCurrentUser();
		assert(currentUser != null);
		String currentUserName = currentUser.getUsername();

		this.logParcelEvent(parcelId, currentUserName, driver.getUsername());
		this.createScheduledDelivery(driver, parcelId);
	}

	private Long createScheduledDelivery(User driver, Long parcelId) {
		return createDeliveryWorker.createScheduledDelivery(driver, parcelId);
	}

	private void logParcelEvent(Long parcelId, String currentUserName, String driverUserName) {

	}
}
