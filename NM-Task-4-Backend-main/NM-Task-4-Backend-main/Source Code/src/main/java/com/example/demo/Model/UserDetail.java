package com.example.demo.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder

public class UserDetail implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(unique = true)
    private  String userName;
    private String userEmail;
    private  Long userMobileNumber;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId", referencedColumnName = "addressId")
    private Address address;

    @JsonIgnore
    public int getUserId() {
        return userId;
    }
    @JsonIgnore
    public void setUserId(int userId) {
        this.userId = userId;
    }
    @JsonIgnore
    public String getUserName() {
        return userName;
    }
    @JsonIgnore
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @JsonIgnore
    public String getUserEmail() {
        return userEmail;
    }
    @JsonIgnore
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    @JsonIgnore
    public Long getUserMobileNumber() {
        return userMobileNumber;
    }
    @JsonIgnore
    public void setUserMobileNumber(Long userMobileNumber) {
        this.userMobileNumber = userMobileNumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userEmail;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



}
