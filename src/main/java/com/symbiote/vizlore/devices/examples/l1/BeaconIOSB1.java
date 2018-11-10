package com.symbiote.vizlore.devices.examples.l1;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L1ClientWithGuestToken;

public class BeaconIOSB1 {
    public static void main(String[] args) {
        // Printing output
        L1ClientWithGuestToken.printResourceInformation(
                "beacon-182ed017-33ce-4c7e-b788-21c8db303265",
                Constants.PLATFORM_ID_VIZLORE);
    }
}
