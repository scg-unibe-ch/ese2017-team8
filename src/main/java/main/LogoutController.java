package main;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class handles the logging out process.
 * It redirects the user to the home site after successfully logging out.
 *
 * @author Team8
 * @version 1.0
 */

@Controller
public class LogoutController {

	/**
	 * Handles the logout process.
	 * By clicking the logout button this method will be invoked.
	 * It checks if the user exists and logs him or her out.
	 * If not nothing happens, to avoid some null exceptions.
	 *
	 * @param request Url request of the browser.
	 * @param response Url response to the request.
	 * @return String where the user gets redirected.
	 */

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/home?logout";
	}
}

