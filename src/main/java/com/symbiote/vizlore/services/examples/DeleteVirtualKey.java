package com.symbiote.vizlore.services.examples;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class DeleteVirtualKey {
    public static void main(String[] args) {

        String body = "[\n" +
                "  {\n" +
                "      \"key_id\" : \"5693417237512192\"\n" +
                "  }\n" +
                "]";
        System.out.println(body);
        String result = L2ClientWithHomeToken.invokeService("DeleteVirtualKeyService",
                body, Constants.FEDERATION_ID);
        System.out.println(result);
    }
}
