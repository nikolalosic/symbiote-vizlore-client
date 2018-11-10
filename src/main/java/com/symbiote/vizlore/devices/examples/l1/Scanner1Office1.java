package com.symbiote.vizlore.devices.examples.l1;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L1ClientWithGuestToken;

public class Scanner1Office1 {
    public static void main(String[] args) {
        // Printing output
        L1ClientWithGuestToken.printResourceInformation(
                "beacon-c76faac7-6882-4614-8451-73f081282435",
                Constants.PLATFORM_ID_VIZLORE);
    }
}
