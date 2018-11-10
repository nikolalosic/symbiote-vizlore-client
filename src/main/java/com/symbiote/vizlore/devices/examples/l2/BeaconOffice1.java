package com.symbiote.vizlore.devices.examples.l2;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class BeaconOffice1 {
    public static void main(String[] args) {
        // Printing output
        L2ClientWithHomeToken.printResourceInformation(
                "beacon-da40b5a1-eb63-4ffd-b450-67062d01085b",
                Constants.FEDERATION_ID);
    }
}
