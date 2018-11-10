package com.symbiote.vizlore.devices.examples.l2;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class Scanner4Office2 {
    public static void main(String[] args) {
        // Printing output
        L2ClientWithHomeToken.printResourceInformation(
                "beacon-6c2a3641-858f-4c03-87cd-7c022c1851f6",
                Constants.FEDERATION_ID);
    }
}
