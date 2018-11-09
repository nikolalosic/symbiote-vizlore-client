package com.symbiote.vizlore.services.examples.l1;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L1ClientWithGuestToken;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class LogoutUser {
    public static void main(String[] args) {

        String body = "[\n" +
                "  {\n" +
                "      \"application_id\" : \"5724160613416960\"\n" +
                "  }\n" +
                "]";
        System.out.println(body);
        String result = L1ClientWithGuestToken.invokeService("LogoutUserService",
                body, Constants.PLATFORM_ID_VIZLORE);
        System.out.println(result);
    }
}
