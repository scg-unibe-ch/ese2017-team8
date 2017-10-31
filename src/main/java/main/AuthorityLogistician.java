package main;

import org.springframework.security.core.GrantedAuthority;

public class AuthorityLogistician implements GrantedAuthority {

	public static AuthorityLogistician instance = new AuthorityLogistician();

	public AuthorityLogistician() { }

	@Override
	public String getAuthority() {
		return "ROLE_LOGISTICIAN";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GrantedAuthority) {
			return this.getAuthority().equals(((GrantedAuthority)obj).getAuthority());
		}
		return false;
	}

}
