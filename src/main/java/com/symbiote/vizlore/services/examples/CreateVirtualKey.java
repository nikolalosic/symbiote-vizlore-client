package com.symbiote.vizlore.services.examples;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class CreateVirtualKey {
    public static void main(String[] args) {

        String body = "[\n" +
                "  {\n" +
                "      \"valid_from\" : \"09/10/2018 11:23:27\"\n" +
                "  },\n" +
                "  {\n" +
                "      \"valid_until\" : \"10/10/2018 11:23:00\"\n" +
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
        String result = L2ClientWithHomeToken.invokeService("CreateVirtualKeyService",
                body, Constants.FEDERATION_ID);
        System.out.println(result);
    }
}
