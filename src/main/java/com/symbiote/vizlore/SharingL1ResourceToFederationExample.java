package com.symbiote.vizlore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory;
import eu.h2020.symbiote.client.interfaces.RHClient;
import eu.h2020.symbiote.cloud.model.internal.CloudResource;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;

import java.security.NoSuchAlgorithmException;
import java.util.*;

import static eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory.*;

public class SharingL1ResourceToFederationExample {


    public static void main(String[] args) {

        /*
        Get the factory and the component clients
         */

        // FILL ME
        String coreAAMAddress = "https://symbiote-open.man.poznan.pl";
        String keystorePath = "testKeystore.jks";
        String keystorePassword = "testKeystore";
        Type type = Type.FEIGN;
        String platformId = Constants.PLATFORM_ID_VIZLORE;
        String federationId = Constants.FEDERATION_ID;

        List<String> servicesToShare = Arrays.asList(
                "active_virtual_keys_service",
                "open_door_logs_service",
                "virtual_key_logs_service",
                "guest_call_logs_service",
                "available_authorized_groups_service",
                "active_applications_service",
                "controller_status_service",
                "list_doors_service",
                "create_virtual_key_service",
                "block_virtual_key_service",
                "unblock_virtual_key_service",
                "update_controller_configuration_service",
                "logout_user_service",
                "delete_virtual_key_service"
        );
        List<String> devicesToShare = Arrays.asList(
                "beacon-da40b5a1-eb63-4ffd-b450-67062d01085b",
                "beacon-182ed017-33ce-4c7e-b788-21c8db303265",
                // other
                "beacon-c76faac7-6882-4614-8451-73f081282435",
                "beacon-ecfa8177-9553-48bd-81b3-593ff2299eda",
                "beacon-925e80f9-358e-4fbf-822b-32d16b487e88",
                "beacon-6c2a3641-858f-4c03-87cd-7c022c1851f6",
                "beacon-7fdf9282-7894-4a4e-970a-bc81c7eea7a7"

        );

        List<String> mobileSensorsToShare = Arrays.asList(
                "beacon-15-B9407F30-F5F8-466E-AFF9-25556B57FE6D",
                "beacon-16-B9407F30-F5F8-466E-AFF9-25556B57FE6D",
                "beacon-17-B9407F30-F5F8-466E-AFF9-25556B57FE6D"
        );

        List<String> l1ResourcesToShare = new ArrayList<>();
        l1ResourcesToShare.addAll(servicesToShare);
        l1ResourcesToShare.addAll(devicesToShare);
        l1ResourcesToShare.addAll(mobileSensorsToShare);
        // Printing output
        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);

        // Get the configuration
        Config config = new Config(coreAAMAddress, keystorePath, keystorePassword, type);

        // Get the factory
        AbstractSymbIoTeClientFactory factory;
        try {
            factory = getFactory(config);

            // end of optional section..
            // After running it the first time and creating the client keystore you should comment out this section.
        } catch (SecurityHandlerException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return;
        }

        RHClient rhClient = factory.getRHClient(platformId);
        Map<String,List<String>> toUnshare = new HashMap<>();
        toUnshare.put(federationId, l1ResourcesToShare);
        rhClient.unshareL2Resources(toUnshare);

        Map<String, Map<String, Boolean>> toShare = new HashMap<>();

        // You can uncomment the following if you want to share all your registered resources to the federation
        //       Map<String, Boolean> resourceMap = rhClient.getResources().stream()
        //             .collect(Collectors.toMap(CloudResource::getInternalId, r -> false));

        Map<String, Boolean> resourceMap = new HashMap<>();
        for (String r : l1ResourcesToShare) {
            resourceMap.put(r, false);
        }
        toShare.put(federationId, resourceMap);

        Map<String, List<CloudResource>> result = rhClient.shareL2Resources(toShare);
        System.out.println(result);

        for (CloudResource cloudResource : result.get(federationId)) {
            System.out.println(cloudResource.getResource().getName());
        }
        System.out.println("END");
    }
}
