package main;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthSuccessHandler is used to handle the user authentication.
 * At the moment this class has to verify hardcoded users.
 * Thus the code below is also hardcoded for the two user roles.
 * Note: This will be changed later on when the users aren't hardcoded anymore.
 *
 * @author Team8
 * @version 1.0
 */

@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		String targetUrl = "/error";

		if (authentication.getAuthorities().contains(AuthorityLogistician.INSTANCE)) {
			targetUrl = "/logistics";
		} else if (authentication.getAuthorities().contains(AuthorityDriver.instance)) {
			targetUrl = "/driver";
		}

		try {
			response.sendRedirect(targetUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
