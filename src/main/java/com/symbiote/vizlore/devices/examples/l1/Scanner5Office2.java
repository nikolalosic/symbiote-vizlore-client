package com.symbiote.vizlore.devices.examples.l1;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L1ClientWithGuestToken;

public class Scanner5Office2 {
    public static void main(String[] args) {
        // Printing output
        L1ClientWithGuestToken.printResourceInformation(
                "beacon-7fdf9282-7894-4a4e-970a-bc81c7eea7a7",
                Constants.PLATFORM_ID_VIZLORE);
    }
}
