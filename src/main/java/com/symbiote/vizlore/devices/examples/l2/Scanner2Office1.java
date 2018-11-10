package com.symbiote.vizlore.devices.examples.l2;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class Scanner2Office1 {
    public static void main(String[] args) {
        // Printing output
        L2ClientWithHomeToken.printResourceInformation(
                "beacon-ecfa8177-9553-48bd-81b3-593ff2299eda",
                Constants.FEDERATION_ID);
    }
}
