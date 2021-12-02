package com.dev.security.principles;

import com.dev.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserPrinciple implements UserDetails {


    private Long id;

    private String firstName;

    private String lastName;

    @JsonIgnore
    private String passwords;

    @JsonIgnore
    private Collection<? extends GrantedAuthority> authoritiesg;


    public UserPrinciple(User model, Collection<? extends GrantedAuthority> authorities) {
        this.setId(model.getUserid());
        this.setFirstName(model.getVorName());
        this.setAuthoritiesg(authorities);
        this.setPasswords(model.getPassword());
    }

    public static UserPrinciple create(User user) {
        List<GrantedAuthority> authorities = List.of( new SimpleGrantedAuthority(user.getRolle().getRoleName()));
        return new UserPrinciple(user,authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoritiesg;
    }

    @Override
    public String getPassword() {
        return passwords;
    }

    @Override
    public String getUsername() {
        return firstName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrinciple that = (UserPrinciple) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public Collection<? extends GrantedAuthority> getAuthoritiesg() {
        return authoritiesg;
    }

    public void setAuthoritiesg(Collection<? extends GrantedAuthority> authoritiesg) {
        this.authoritiesg = authoritiesg;
    }
}
