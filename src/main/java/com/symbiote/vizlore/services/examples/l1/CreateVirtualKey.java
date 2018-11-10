package com.symbiote.vizlore.services.examples.l1;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L1ClientWithGuestToken;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class CreateVirtualKey {
    public static void main(String[] args) {

        String body = "[\n" +
                "  {\n" +
                "      \"valid_from\" : \"10/10/2018 11:23:27\"\n" +
                "  },\n" +
                "  {\n" +
                "      \"valid_until\" : \"11/11/2018 11:23:00\"\n" +
                "  },\n" +
                "  {\n" +
                "      \"guest\" : \"Test Guest\"\n" +
                "  },\n" +
                "  {\n" +
                "      \"guest_email\" : \"example@example.com\"\n" +
                "  },\n" +
                "  {\n" +
                "      \"authorized_groups\" : [\"__General__\"]\n" +
                "  }\n" +
                "]";
        System.out.println(body);
        String result = L1ClientWithGuestToken.invokeService("CreateVirtualKeyService",
                body, Constants.PLATFORM_ID_VIZLORE);
        System.out.println(result);
    }
}
