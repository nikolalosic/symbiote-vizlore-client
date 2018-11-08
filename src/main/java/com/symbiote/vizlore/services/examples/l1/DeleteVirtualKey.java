package com.symbiote.vizlore.services.examples.l1;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L1ClientWithGuestToken;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class DeleteVirtualKey {
    public static void main(String[] args) {

        String body = "[\n" +
                "  {\n" +
                "      \"key_id\" : \"5693417237512192\"\n" +
                "  }\n" +
                "]";
        System.out.println(body);
        String result = L1ClientWithGuestToken.invokeService("DeleteVirtualKeyService",
                body, Constants.PLATFORM_ID_VIZLORE);
        System.out.println(result);
    }
}
