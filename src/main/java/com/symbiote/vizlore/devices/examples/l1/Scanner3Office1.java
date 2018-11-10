package com.symbiote.vizlore.devices.examples.l1;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L1ClientWithGuestToken;

public class Scanner3Office1 {
    public static void main(String[] args) {
        // Printing output
        L1ClientWithGuestToken.printResourceInformation(
                "beacon-925e80f9-358e-4fbf-822b-32d16b487e88",
                Constants.PLATFORM_ID_VIZLORE);
    }
}
