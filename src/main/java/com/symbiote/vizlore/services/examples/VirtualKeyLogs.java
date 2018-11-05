package com.symbiote.vizlore.services.examples;


import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class VirtualKeyLogs {
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
        String result = L2ClientWithHomeToken.invokeService(
                "VirtualKeyLogs",
                body,
                Constants.FEDERATION_ID);
        try {
            System.out.println(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
