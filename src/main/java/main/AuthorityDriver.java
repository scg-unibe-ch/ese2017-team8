package main;

import org.springframework.security.core.GrantedAuthority;

public class AuthorityDriver implements GrantedAuthority {

	public static AuthorityDriver instance = new AuthorityDriver();

	public AuthorityDriver() { }

	@Override
	public String getAuthority() {
		return "ROLE_DRIVER";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GrantedAuthority) {
			return this.getAuthority().equals(((GrantedAuthority)obj).getAuthority());
		}
		return false;
	}
}
