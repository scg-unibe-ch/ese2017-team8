package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.GrantedAuthority;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Application is the main class, which starts the whole application by default.
 * This happens when the url, at the moment over our localhost, is entered.
 * The user is then directed to the home site, which is the start site by default.
 *
 * @author Team8
 * @version 1.0
 */

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

}