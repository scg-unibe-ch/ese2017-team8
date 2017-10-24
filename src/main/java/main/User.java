package main;

import org.hibernate.annotations.Entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * This class is the entity of an user.
 * It contains the login information and the role of the user.
 * Those fields can be accessed via invoking the getter and setter methods.
 *
 * @author Team8
 * @version 1.0
 */

@Entity
public class User implements UserDetails {

    public enum Role {
        Driver, Logictican;
    }

    private String username;
    private String password;
    private Role role;

    public User(String username, String password, Role role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return "Testsache";
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
