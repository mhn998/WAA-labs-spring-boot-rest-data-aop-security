package com.example.waa_first_demo.domain.security;

import com.example.waa_first_demo.domain.Role;
import com.example.waa_first_demo.domain.User;
import com.example.waa_first_demo.domain.dao.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetailsCustom implements UserDetails {

    private Long id;

    private String email;
    @JsonIgnore
    private String password;
    private List<Role> roles;

    private boolean enabled;

    public UserDetailsCustom(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.roles = user.getRoles();
    }

    public UserDetailsCustom(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.enabled = userEntity.isEnabled();
        this.roles = userEntity.getRoles();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().
                map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
