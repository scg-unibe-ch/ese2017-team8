package main.common.business.getcurrentuser;


import main.common.data.models.User;
import main.common.data.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetCurrentUserWorker implements GetCurrentUserUseCases {
	@Autowired
	UserRepo userRepo;

	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		return userRepo.findByUsername(authentication.getName());
	}
}
