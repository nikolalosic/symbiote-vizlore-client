package com.symbiote.vizlore.services.examples.l1;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L1ClientWithGuestToken;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class OpenDoorLogs {
    public static void main(String[] args) {
        // Printing output
        String body = "[\n" +
                "  {\n" +
                "      \"start_date\" : \"07/10/2018\"\n" +
                "  },\n" +
                "  {\n" +
                "      \"end_date\" : \"11/22/2018\"\n" +
                "  }\n" +
                "]";
        System.out.println(body);
        String result = L1ClientWithGuestToken.invokeService(
                "OpenDoorLogs",
                body,
                Constants.PLATFORM_ID_VIZLORE);
        try {
            System.out.println(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
