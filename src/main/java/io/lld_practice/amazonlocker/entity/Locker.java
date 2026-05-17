package io.lld_practice.amazonlocker.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Locker {
    Compartment[]  compartments;
    Map<String, AccessToken> accessTokens;
    public Locker(Compartment[] compartments, Map<String, AccessToken> accessTokens) {
        this.compartments = compartments;
        this.accessTokens = (accessTokens == null) ? new HashMap<>() : accessTokens;
    }
    public String placePackage(Size size){

       Compartment availCompartment = getAvailableCompartments(size);
       if (availCompartment == null) {
           throw new RuntimeException("no available compartment for size: " + size);
       }

       availCompartment.markOccupied();

       String accessToken = generateAccessToken();
       LocalDateTime expiryTime = generateExpiryTime();
       AccessToken token = new AccessToken(accessToken, expiryTime, availCompartment);

       accessTokens.put(accessToken, token);

       return accessToken;

    }

    public void openCompartment(String token){
     if(token == null){
         throw new RuntimeException("token is null");
     }
     if (token.isEmpty()){
         throw new RuntimeException("token is empty");
     }
        AccessToken currentToken=accessTokens.get(token);

     if(currentToken == null){
         throw new RuntimeException("token is not valid");
     }
     if(currentToken.isExpired()){
         throw new RuntimeException("token is expired");
     }

     currentToken.getCompartment().markFree();
     accessTokens.remove(token);
    }

    public void openExpiredCompartment(){
        accessTokens.entrySet().removeIf(entry -> {
            AccessToken token = entry.getValue();
            if (token.isExpired()) {
                token.getCompartment().markFree();
                return true;
            }
            return false;
        });
    }

    private LocalDateTime generateExpiryTime() {
        return LocalDateTime.now().plusDays(7);
    }

    private String generateAccessToken() {
        return UUID.randomUUID().toString();
    }

    private Compartment getAvailableCompartments(Size size) {
    for(Compartment compartment : compartments){
        if(compartment.getSize() == size && compartment.isAvailable())  {
            return compartment;
        }
    }
    return null;
    }
}
