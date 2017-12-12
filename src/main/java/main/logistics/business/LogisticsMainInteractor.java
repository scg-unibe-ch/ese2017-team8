package main.logistics.business;

import main.AuthorityDriver;
import main.UserDetailsImpl;
import main.common.business.getcurrentuser.GetCurrentUserUseCases;
import main.common.business.logging.parcel.LogParcelEventUseCases;
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
import main.logistics.presentation.viewmodels.ParcelStatListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

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

	@Autowired
	private LogParcelEventUseCases logParcelEventWorker;

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

	public List<Parcel> getArchivedParcels() {
		List<Parcel> allParcels = parcelRepo.findAll();
		List<Parcel> filteredParcels = new ArrayList<Parcel>();

		for (Parcel parcel : allParcels) {
			Delivery del = deliveryRepo.findByParcelId(parcel.getId());

			if (del != null && del.getStatus() == Delivery.Status.archived) {
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

	/**
	 * is necessary to show names instead of ids
	 *
	 * @param parcelId
	 * @return viewModel for parcelstats.html
	 */
	public List<ParcelStatListModel> getParcelStatListById(Long parcelId){
		List<ParcelStat> parcelStatList = parcelStatRepo.findByParcelId(parcelId);
		List <ParcelStatListModel> viewModel = new ArrayList<ParcelStatListModel>();

		for (ParcelStat p: parcelStatList){
			ParcelStatListModel parcelStatListModel = new ParcelStatListModel();
			parcelStatListModel.setParcelStat(p);

			User driverForDelivery = userRepo.findById(p.getDriverId());
			User userForDelivery = userRepo.findById(p.getUserId());
			parcelStatListModel.setUser(userForDelivery);
			parcelStatListModel.setDriver(driverForDelivery);

			viewModel.add(parcelStatListModel);
		}

		Collections.reverse(viewModel);
		return viewModel;
	}

	public void didSubmitDelivery(User driver, Long parcelId) {
		assert(driver.getAuthorities().contains(AuthorityDriver.instance));

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		assert(authentication != null);
		UserDetailsImpl customUser = (UserDetailsImpl) authentication.getPrincipal();
		Long currentUserId = customUser.getId();

		logParcelEventWorker.logParcelEvent(parcelId, Delivery.Status.scheduled, currentUserId, driver.getId());
		createDeliveryWorker.createScheduledDelivery(driver, parcelId);
	}

	public void didReactivateParcel(Long parcelId, Long deliveryId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		assert(authentication != null);
		UserDetailsImpl customUser = (UserDetailsImpl)authentication.getPrincipal();
		Long currentUserId = customUser.getId();

		deliveryRepo.delete(deliveryId);

		logParcelEventWorker.logParcelEvent(parcelId, Delivery.Status.unscheduled, currentUserId, null);
	}
}
