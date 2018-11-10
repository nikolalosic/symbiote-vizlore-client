package com.symbiote.vizlore.devices.examples.l1;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L1ClientWithGuestToken;

public class Scanner4Office2 {
    public static void main(String[] args) {
        // Printing output
        L1ClientWithGuestToken.printResourceInformation(
                "beacon-6c2a3641-858f-4c03-87cd-7c022c1851f6",
                Constants.PLATFORM_ID_VIZLORE);
    }
}
