package com.example.Auth.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Document
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Worker implements UserDetails {
    @Id
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String name;
    @NonNull
    private Float avgRaiting;
    @NonNull
    private Double latitude;
    @NonNull
    private Double longitude;
    private String picture;

    public Worker(String email, @NonNull String password, @NonNull String name, @NonNull Double latitude, @NonNull Double longitude) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        avgRaiting =  0f;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getId() {
        return email;
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
