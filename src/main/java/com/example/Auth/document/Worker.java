package com.example.Auth.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mongodb.lang.Nullable;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

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
    private String phoneNumber;
    @NonNull
    private String address;
    @NonNull
    private Double latitude;
    @NonNull
    private Double longitude;
    @Nullable
    private String picture;
    @Nullable
    private String description;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    @Nullable
    private String certificate;
    @NonNull
    private String profession;
    public Worker(String email, @NonNull String password, @NonNull String name, @NonNull Double latitude, @NonNull Double longitude, @NonNull String address, @NonNull String phoneNumber, @NonNull String profession) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.profession = profession;
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
