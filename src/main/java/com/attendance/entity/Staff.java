package com.attendance.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by FantasticPan on 2018/3/23.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "staff")
public class Staff implements UserDetails {

    private static final long serialVersionUID = -9031877318452031140L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String staffName;
    private String staffDate;
    @Column(columnDefinition="VARCHAR(255) default '666'")
    private String password;

    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<Role> roles;

    public Staff(String staffName, String staffDate, String password) {
        this.staffName = staffName;
        this.staffDate = staffDate;
        this.password = password;
    }

    public Staff() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<Role> roles = this.getRoles();
        for (Role role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.staffName;
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
}
