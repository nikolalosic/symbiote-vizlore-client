package com.symbiote.vizlore.devices.examples.l2;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class BeaconSymbioteDemo101 {
    public static void main(String[] args) {
        // Printing output
        L2ClientWithHomeToken.printResourceInformation(
                "beacon-101-84595c6f-16ec-4931-a3f8-f513e159fc5b",
                Constants.FEDERATION_ID);
    }
}
