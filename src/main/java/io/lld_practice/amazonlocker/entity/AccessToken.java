package io.lld_practice.amazonlocker.entity;

import java.time.LocalDateTime;

public class AccessToken {
    private String accessToken;
    private LocalDateTime expiresAt;
    private Compartment compartment;

    public AccessToken(String accessToken, LocalDateTime expiresAt, Compartment compartment) {
        this.accessToken = accessToken;
        this.expiresAt = expiresAt;
        this.compartment = compartment;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
    public Compartment getCompartment() {
        return compartment;
    }

}
