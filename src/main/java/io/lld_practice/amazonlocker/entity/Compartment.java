package io.lld_practice.amazonlocker.entity;

import java.util.HashMap;
import java.util.Map;

public class Compartment {
    private final Size size;
    private boolean isAvailable;

    public Compartment(Size size) {
        this.size = size;
        this.isAvailable = true;
    }

    public void markOccupied(){
        this.isAvailable = false;
    }

    public void markFree(){
        this.isAvailable = true;
    }

    public Size getSize(){
        return this.size;
    }
    public boolean isAvailable(){
        return this.isAvailable;
    }
}
