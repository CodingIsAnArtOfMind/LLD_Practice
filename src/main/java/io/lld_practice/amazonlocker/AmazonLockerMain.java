package io.lld_practice.amazonlocker;

import io.lld_practice.amazonlocker.entity.AccessToken;
import io.lld_practice.amazonlocker.entity.Compartment;
import io.lld_practice.amazonlocker.entity.Locker;
import io.lld_practice.amazonlocker.entity.Size;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AmazonLockerMain {
    public static void main(String[] args) {
        Compartment[] compartments = new Compartment[] {
                new Compartment(Size.SMALL),
                new Compartment(Size.MEDIUM),
                new Compartment(Size.LARGE)
        };

        Map<String, AccessToken> seedTokens = new HashMap<>();
        // Seed an expired token to exercise staff cleanup.
        Compartment expiredCompartment = new Compartment(Size.SMALL);
        expiredCompartment.markOccupied();
        String expiredCode = "EXPIRED-123";
        seedTokens.put(expiredCode, new AccessToken(expiredCode, LocalDateTime.now().minusDays(1), expiredCompartment));

        Locker locker = new Locker(compartments, seedTokens);

        System.out.println("Scenario 1: successful deposit + pickup");
        String code = locker.placePackage(Size.MEDIUM);
        System.out.println("Code issued: " + code);
        locker.openCompartment(code);
        System.out.println("Pickup success for code: " + code);

        System.out.println();
        System.out.println("Scenario 2: invalid code");
        try {
            locker.openCompartment("BAD-CODE");
        } catch (RuntimeException ex) {
            System.out.println("Expected error: " + ex.getMessage());
        }

        System.out.println();
        System.out.println("Scenario 3: reused code");
        try {
            locker.openCompartment(code);
        } catch (RuntimeException ex) {
            System.out.println("Expected error: " + ex.getMessage());
        }

        System.out.println();
        System.out.println("Scenario 4: staff clears expired compartments");
        locker.openExpiredCompartment();
        try {
            locker.openCompartment(expiredCode);
        } catch (RuntimeException ex) {
            System.out.println("Expected error after cleanup: " + ex.getMessage());
        }
    }
}
