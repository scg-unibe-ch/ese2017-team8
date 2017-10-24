package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * WebSecurityConfig is used to authorize user request.
 * It secures which user is able to login with which password.
 *
 * @author Team8
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthSuccessHandler successHandler;


	/**
	 * Configures which role can access which part of the website.
	 *
	 * @param http Url to be checked.
	 * @throws Exception Throws one if the url and role aren't matching.
 	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/home").permitAll()
				.antMatchers("/logistics/**").hasRole("LOGISTICIAN")
				.antMatchers("/driver/**").hasRole("DRIVER")
				.and()
				.formLogin().loginPage("/home").successHandler(successHandler)
				.and()
				.logout().logoutUrl("/logout").permitAll();
	}

	/**
	 * Configures the users role and password.
	 * At the moment all users are hardcoded this might change in the future.
	 *
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("logistician").password("pass").roles("LOGISTICIAN");
		auth.inMemoryAuthentication().withUser("Maria Magdalena").password("pass").roles("DRIVER");
		auth.inMemoryAuthentication().withUser("Donald Duck").password("pass").roles("DRIVER");
		auth.inMemoryAuthentication().withUser("Hans Nötig").password("pass").roles("DRIVER");
	}
}
