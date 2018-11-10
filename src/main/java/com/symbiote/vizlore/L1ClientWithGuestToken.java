package com.symbiote.vizlore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory;
import eu.h2020.symbiote.client.interfaces.CRAMClient;
import eu.h2020.symbiote.client.interfaces.RAPClient;
import eu.h2020.symbiote.client.interfaces.SearchClient;
import eu.h2020.symbiote.core.ci.QueryResponse;
import eu.h2020.symbiote.core.internal.CoreQueryRequest;
import eu.h2020.symbiote.core.internal.cram.ResourceUrlsResponse;
import eu.h2020.symbiote.model.cim.Observation;

import java.util.List;

public class L1ClientWithGuestToken {

    public static String invokeService(String resourceName, String body, String platformId) {
        System.out.println("Started invoking service " + resourceName + " in platform  " + platformId + " as guest");
        AbstractSymbIoTeClientFactory factory = SymbioteUtils.getGuestClientFactory();

        // Get the necessary component clients
        SearchClient searchClient = factory.getSearchClient();
        CRAMClient cramClient = factory.getCramClient();
        RAPClient rapClient = factory.getRapClient();

        // Create the request
        CoreQueryRequest coreQueryRequest = new CoreQueryRequest.Builder()
                .platformId(platformId)
                .name(resourceName)
                .build();

        // Send the request and validate the Search response
        QueryResponse queryResponse = searchClient.searchAsGuest(coreQueryRequest, true);
        System.out.println("Searching the Platform Registry of platform: " + platformId);

        if (queryResponse.getResources().size() == 0) {
            System.out.println("Could not find resource " + resourceName + " in platform " + platformId);
        } else {
            String resourceId = queryResponse.getResources().get(0).getId();
            ResourceUrlsResponse resourceUrlsResponse = cramClient.getResourceUrlAsGuest(resourceId, true);
            String resourceUrl = resourceUrlsResponse.getBody().get(resourceId);

            System.out.println("Trying to invoke service " + queryResponse.getResources().get(0).getId() + "(" +
                    queryResponse.getResources().get(0).getName() + ") in platform " + platformId + " as guest");
            String res = rapClient.invokeServiceAsGuest(resourceUrl, body, true);
            System.out.println("Finished invoking service " + queryResponse.getResources().get(0).getId() + "(" +
                    queryResponse.getResources().get(0).getName() + ") in platform " + platformId + " as guest");
            return res;
        }

        System.out.println("Error invoking service " + resourceName + " in platform  " + platformId + " as guest");
        return null;
    }


    public static List<Observation> getTopObservations(String resourceName, Integer number, String platformId) {
        System.out.println("Started getting top observations of resource " + resourceName + " in platform  " + platformId + " as guest");
        AbstractSymbIoTeClientFactory factory = SymbioteUtils.getGuestClientFactory();
        // Get the necessary component clients
        SearchClient searchClient = factory.getSearchClient();
        CRAMClient cramClient = factory.getCramClient();
        RAPClient rapClient = factory.getRapClient();

        // Create the request
        CoreQueryRequest coreQueryRequest = new CoreQueryRequest.Builder()
                .platformId(platformId)
                .name(resourceName)
                .build();

        // Send the request and validate the Search response
        QueryResponse queryResponse = searchClient.searchAsGuest(coreQueryRequest, true);
        System.out.println("Searching the Platform Registry of platform: " + platformId);

        if (queryResponse.getResources().size() == 0) {
            System.out.println("Could not find resource " + resourceName + " in platform " + platformId);
        } else {
            String resourceId = queryResponse.getResources().get(0).getId();
            ResourceUrlsResponse resourceUrlsResponse = cramClient.getResourceUrlAsGuest(resourceId, true);
            String resourceUrl = resourceUrlsResponse.getBody().get(resourceId);
            System.out.println("Trying to access resource " + queryResponse.getResources().get(0).getId() + "(" +
                    queryResponse.getResources().get(0).getName() + ") in platform " + platformId + " as guest");
            List<Observation> res = rapClient.getTopObservationsAsGuest(resourceUrl, number, true);
            System.out.println("Finished getting top observations of resource " + resourceName + " in platform  " + platformId + " as guest");
            return res;
        }

        System.out.println("Error getting top observations of resource " + resourceName + " in platform  " + platformId + " as guest");
        return null;
    }

    public static Observation getLatestObservations(String resourceName, String platformId) {
        System.out.println("Started getting latest observations of resource " + resourceName + " in platform  " + platformId + " as guest");
        AbstractSymbIoTeClientFactory factory = SymbioteUtils.getGuestClientFactory();

        // Get the necessary component clients
        SearchClient searchClient = factory.getSearchClient();
        CRAMClient cramClient = factory.getCramClient();
        RAPClient rapClient = factory.getRapClient();

        // Create the request
        CoreQueryRequest coreQueryRequest = new CoreQueryRequest.Builder()
                .platformId(platformId)
                .name(resourceName)
                .build();

        // Send the request and validate the Search response
        QueryResponse queryResponse = searchClient.searchAsGuest(coreQueryRequest, true);
        System.out.println("Searching the Platform Registry of platform: " + platformId);

        if (queryResponse.getResources().size() == 0) {
            System.out.println("Could not find resource " + resourceName + " in platform " + platformId);
        } else {
            String resourceId = queryResponse.getResources().get(0).getId();
            ResourceUrlsResponse resourceUrlsResponse = cramClient.getResourceUrlAsGuest(resourceId, true);
            String resourceUrl = resourceUrlsResponse.getBody().get(resourceId);

            System.out.println("Trying to access resource " + queryResponse.getResources().get(0).getId() + "(" +
                    queryResponse.getResources().get(0).getName() + ") in platform " + platformId + " as guest");
            Observation res = rapClient.getLatestObservationAsGuest(resourceUrl, true);
            System.out.println("Finished getting latest observations of resource " + resourceName + " in platform  " + platformId + " as guest");
            return res;
        }

        System.out.println("Error getting top observations of resource " + resourceName + " in platform  " + platformId + " as guest");
        return null;
    }

    public static void printResourceInformation(String resourceName, String platformId) {
        System.out.println("Started printing resource " + resourceName + " information in platform  " + platformId + " as guest");
        AbstractSymbIoTeClientFactory factory = SymbioteUtils.getGuestClientFactory();

        // Get the necessary component clients
        SearchClient searchClient = factory.getSearchClient();
        // Printing output
        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);

        // Create the request
        CoreQueryRequest coreQueryRequest = new CoreQueryRequest.Builder()
                .platformId(platformId)
                .name(resourceName)
                .build();


        // Send the request and validate the Search response
        QueryResponse queryResponse = searchClient.searchAsGuest(coreQueryRequest, true);
        System.out.println("Searching the Platform Registry of platform: " + platformId);

        if (queryResponse.getResources().size() == 0) {
            System.out.println("Could not find resource " + resourceName + " in platform " + platformId);
        } else {
            try {
                System.out.println(om.writeValueAsString(queryResponse.getResources().get(0)));
            } catch (Exception ex) {
                System.out.println("Error printing resource " + resourceName + " information in platform  " + platformId + " as guest");
                return;
            }
        }

        System.out.println("Finished printing resource " + resourceName + " information in platform  " + platformId + " as guest");
    }
}
