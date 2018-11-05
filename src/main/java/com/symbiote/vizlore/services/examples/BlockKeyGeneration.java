package com.symbiote.vizlore.services.examples;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class BlockKeyGeneration {
    public static void main(String[] args) {

        String body = "[\n" +
                "  {\n" +
                "      \"key_id\" : \"5724160613416960\"\n" +
                "  }\n" +
                "]";
        System.out.println(body);
        String result = L2ClientWithHomeToken.invokeService("BlockVirtualKeyService",
                body, Constants.FEDERATION_ID);
        System.out.println(result);
    }
}
