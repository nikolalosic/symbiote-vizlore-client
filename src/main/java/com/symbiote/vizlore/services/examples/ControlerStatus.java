package com.symbiote.vizlore.services.examples;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class ControlerStatus {
    public static void main(String[] args) {
        // Printing output
        String body = "[{}]";
        String result = L2ClientWithHomeToken.invokeService(
                "ControllerStatus",
                body,
                Constants.FEDERATION_ID);
        try {
            System.out.println(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
