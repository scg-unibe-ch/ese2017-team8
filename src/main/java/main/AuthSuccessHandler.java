package main;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	/**
	 * Authenticates the user and gets the target url for either use.
	 * This is done by checking the input, if its either one of the hardcoded users down below.
	 * The url will adapt a simple /task. Where task is either logistician or driver.
	 * If the authentication is failing the user will be directed to an error site.
	 *
	 * @param request
	 * @param response
	 * @return String which the url adds
	 */

	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().toString();

		String targetUrl = "/error";
		if (role.contains("LOGISTICIAN")) {
			targetUrl = "/logistics";
		} else if (role.contains("DRIVER")) {
			targetUrl = "/driver";
		}
		return targetUrl;
	}
}
