package main.logistics.business;

import main.UserDetailsImpl;
import main.common.business.getcurrentuser.GetCurrentUserUseCases;
import main.common.business.logging.parcel.LogParcelEventUseCases;
import main.common.business.logging.parcel.LogParcelEventWorker;
import main.common.data.models.Delivery;
import main.common.data.models.Parcel;
import main.common.data.models.ParcelStat;
import main.common.data.models.User;
import main.common.data.repositories.ParcelRepo;
import main.common.data.repositories.ParcelStatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LogisticsNewParcelInteractor implements LogisticsNewParcelUseCases {
	@Autowired
	ParcelRepo parcelRepo;

	@Autowired
	ParcelStatRepo parcelStatRepo;

	@Autowired
	GetCurrentUserUseCases getCurrentUserWorker;

	@Autowired
	LogParcelEventUseCases logParcelEventWorker;

	public void didSubmitParcel(Parcel parcel) {
		parcelRepo.save(parcel);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		assert(authentication != null);
		UserDetailsImpl customUser = (UserDetailsImpl)authentication.getPrincipal();
		Long currentUserId = customUser.getId();

		logParcelEventWorker.logParcelEvent(parcel.getId(), Delivery.Status.unscheduled, currentUserId, null);
	}
}
