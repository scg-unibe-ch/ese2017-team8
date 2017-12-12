package main;

import main.common.data.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AuthSuccessHandler successHandler;

	/**
	 * Configures which role can access which part of the website.
	 *
	 * @param http Url to be checked.
	 * @throws Exception Throws one if the url and role aren't matching.
 	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/home", "/stylesheets/**").permitAll()
				.antMatchers("/", "/home", "/media/**").permitAll()
				.antMatchers("/error/**").permitAll()
				.antMatchers("/logistics/**").hasRole("LOGISTICIAN")
				.antMatchers("/neworder/**").hasRole("LOGISTICIAN")
				.antMatchers("/changeorder/**").hasRole("LOGISTICIAN")
				.antMatchers("/parcelstats/**").hasRole("LOGISTICIAN")
				.antMatchers("/driverstatsoverview/**").hasRole("LOGISTICIAN")
				.antMatchers("/driverstats/**").hasRole("LOGISTICIAN")
				.antMatchers("/archive/**").hasRole("LOGISTICIAN")
				.antMatchers("/canceled/**").hasRole("LOGISTICIAN")
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
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}
}
