package com.symbiote.vizlore.devices.examples.l2;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class Scanner5Office2 {
    public static void main(String[] args) {
        // Printing output
        L2ClientWithHomeToken.printResourceInformation(
                "beacon-7fdf9282-7894-4a4e-970a-bc81c7eea7a7",
                Constants.FEDERATION_ID);
    }
}
