package com.symbiote.vizlore.devices.examples.l2;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class Scanner3Office1 {
    public static void main(String[] args) {
        // Printing output
        L2ClientWithHomeToken.printResourceInformation(
                "beacon-925e80f9-358e-4fbf-822b-32d16b487e88",
                Constants.FEDERATION_ID);
    }
}
