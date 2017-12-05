package main;

import org.springframework.security.core.GrantedAuthority;

public enum AuthorityLogistician implements GrantedAuthority {
 INSTANCE;
	

	@Override
	public String getAuthority() {
		return "ROLE_LOGISTICIAN";
	}



}
