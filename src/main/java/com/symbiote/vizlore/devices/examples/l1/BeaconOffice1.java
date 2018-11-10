package com.symbiote.vizlore.devices.examples.l1;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L1ClientWithGuestToken;

public class BeaconOffice1 {
    public static void main(String[] args) {
        // Printing output
        L1ClientWithGuestToken.printResourceInformation(
                "beacon-da40b5a1-eb63-4ffd-b450-67062d01085b",
                Constants.PLATFORM_ID_VIZLORE);
    }
}
