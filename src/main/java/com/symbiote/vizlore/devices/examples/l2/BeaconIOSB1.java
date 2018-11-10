package com.symbiote.vizlore.devices.examples.l2;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class BeaconIOSB1 {
    public static void main(String[] args) {
        // Printing output
        L2ClientWithHomeToken.printResourceInformation(
                "beacon-182ed017-33ce-4c7e-b788-21c8db303265",
                Constants.FEDERATION_ID);
    }
}
