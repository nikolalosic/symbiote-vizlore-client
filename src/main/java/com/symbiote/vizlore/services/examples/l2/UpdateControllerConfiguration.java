package com.symbiote.vizlore.services.examples.l2;

import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L2ClientWithHomeToken;

public class UpdateControllerConfiguration {
    public static void main(String[] args) {

        String body = "[\n" +
                "  {\n" +
                "      \"device_id\" : \"da40b5a1-eb63-4ffd-b450-67062d01085b\"\n" +
                "  },\n" +
                "  {\n" +
                "      \"ibeacon_uuid\" : \"92E9E7664153422F95073074A007546A\"\n" +
                "  },\n" +
                "  {\n" +
                "      \"ibeacon_minor\" : \"007A\"\n" +
                "  },\n" +
                "  {\n" +
                "      \"ibeacon_major\" : \"0000\"\n" +
                "  },\n" +
                "  {\n" +
                "      \"ibeacon_tx_power\" : \"C8\"\n" +
                "  },\n" +
                "  {\n" +
                "      \"relay_activation_time\" : 4 \n" +
                "  },\n" +
                "  {\n" +
                "      \"sip_domain\" : \"13.89.53.89\"\n" +
                "  }\n" +
                "]";
        System.out.println(body);
        String result = L2ClientWithHomeToken.invokeService("UpdateControllerConfigurationService",
                body, Constants.FEDERATION_ID);
        System.out.println(result);
    }

}
