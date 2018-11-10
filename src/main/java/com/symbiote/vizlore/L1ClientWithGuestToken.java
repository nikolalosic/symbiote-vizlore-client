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
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory.*;

public class L1ClientWithGuestToken {

    public static String invokeService(String resourceName, String body, String platformId) {
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
            System.out.println("Could not find any resources");
        } else {
            String resourceId = queryResponse.getResources().get(0).getId();
            ResourceUrlsResponse resourceUrlsResponse = cramClient.getResourceUrlAsGuest(resourceId, true);
            String resourceUrl = resourceUrlsResponse.getBody().get(resourceId);

            System.out.println("Trying to access resource " + queryResponse.getResources().get(0).getId() + "(" + queryResponse.getResources().get(0).getName() + ")");
            String res = rapClient.invokeServiceAsGuest(resourceUrl, body, true);
            return res;
        }

        System.out.println("END");
        return null;
    }


    public static List<Observation> getTopObservations(String resourceName, Integer number, String platformId) {
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
            System.out.println("Could not find any resources");
        } else {
            String resourceId = queryResponse.getResources().get(0).getId();
            ResourceUrlsResponse resourceUrlsResponse = cramClient.getResourceUrlAsGuest(resourceId, true);
            String resourceUrl = resourceUrlsResponse.getBody().get(resourceId);
            System.out.println("Trying to access resource " + queryResponse.getResources().get(0).getId());
            List<Observation> res = rapClient.getTopObservationsAsGuest(resourceUrl, number, true);
            return res;
        }

        System.out.println("END");
        return null;
    }

    public static Observation getLatestObservations(String resourceName, String platformId) {
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
            System.out.println("Could not find any resources");
        } else {
            String resourceId = queryResponse.getResources().get(0).getId();
            ResourceUrlsResponse resourceUrlsResponse = cramClient.getResourceUrlAsGuest(resourceId, true);
            String resourceUrl = resourceUrlsResponse.getBody().get(resourceId);

            System.out.println("Trying to access resource " + queryResponse.getResources().get(0).getId());
            Observation res = rapClient.getLatestObservationAsGuest(resourceUrl, true);
            return res;
        }

        System.out.println("END");
        return null;
    }

    public static void printResourceInformation(String resourceName, String platformId) {
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
            System.out.println("Could not find any resources");
        } else {
            try {
                System.out.println(om.writeValueAsString(queryResponse.getResources().get(0)));
            } catch (Exception ex) {
                return;
            }
        }

        System.out.println("END");
    }
}
