package com.symbiote.vizlore.devices.examples.l1;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L1ClientWithGuestToken;

public class Scanner2Office1 {
    public static void main(String[] args) {
        // Printing output
        L1ClientWithGuestToken.printResourceInformation(
                "beacon-ecfa8177-9553-48bd-81b3-593ff2299eda",
                Constants.PLATFORM_ID_VIZLORE);
    }
}
