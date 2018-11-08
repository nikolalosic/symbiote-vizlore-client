package com.symbiote.vizlore.services.examples.l1;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L1ClientWithGuestToken;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class ControlerStatus {
    public static void main(String[] args) {
        // Printing output
        String body = "[{}]";
        String result = L1ClientWithGuestToken.invokeService(
                "ControllerStatus",
                body,
                Constants.PLATFORM_ID_VIZLORE);
        try {
            System.out.println(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
