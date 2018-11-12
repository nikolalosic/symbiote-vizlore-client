package com.symbiote.vizlore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory;
import eu.h2020.symbiote.client.interfaces.PRClient;
import eu.h2020.symbiote.client.interfaces.RAPClient;
import eu.h2020.symbiote.cloud.model.internal.FederatedResource;
import eu.h2020.symbiote.cloud.model.internal.FederationSearchResult;
import eu.h2020.symbiote.cloud.model.internal.PlatformRegistryQuery;
import eu.h2020.symbiote.model.cim.Observation;

import java.util.*;

public class L2ClientWithHomeToken {

    public static String invokeService(String resourceName, String body, String federationId) {
        System.out.println("Started invoking service " + resourceName + " in federation " + federationId);

        AbstractSymbIoTeClientFactory factory = SymbioteUtils.getClientFactory(Constants.PLATFORM_ID, Constants.USERNAME, Constants.PASSWORD, Constants.CLIENT_ID);

        // The set of platforms from which we are going to request credentials for our requests
        Set<String> platformIds = new HashSet<>(Collections.singletonList(Constants.PLATFORM_ID));

        // Get the necessary component clients
        PRClient searchClient = factory.getPRClient(Constants.PLATFORM_ID);
        RAPClient rapClient = factory.getRapClient();

        // Create the request
        PlatformRegistryQuery registryQuery = new PlatformRegistryQuery.Builder()
                .federationIds(Collections.singletonList(federationId))
                .names(Arrays.asList(resourceName))
                .build();

        System.out.println("Searching the Platform Registry of platform: " + Constants.PLATFORM_ID);
        FederationSearchResult result = searchClient.search(registryQuery, false, platformIds);

        if (result.getResources().size() == 0) {
            System.out.println("Could not find resource " + resourceName + " in federation " + federationId);
        } else {
            System.out.println("Trying to access resource " + result.getResources().get(0).getAggregationId() + " in federation " + federationId);
            String res = rapClient.invokeService(result.getResources().get(0).getFederatedResourceInfoMap().get(federationId).getoDataUrl(), body, true, platformIds);
            System.out.println("Finished invoking service " + resourceName + " in federation " + federationId);
            return res;
        }

        System.out.println("Error invoking service " + resourceName + " in federation " + federationId);
        return null;
    }

    public static List<Observation> getTopObservations(String resourceName, Integer number, String federationId) {
        System.out.println("Started getting top observations of resource" + resourceName + " in federation " + federationId);

        AbstractSymbIoTeClientFactory factory = SymbioteUtils.getClientFactory(Constants.PLATFORM_ID, Constants.USERNAME, Constants.PASSWORD, Constants.CLIENT_ID);

        // The set of platforms from which we are going to request credentials for our requests
        Set<String> platformIds = new HashSet<>(Collections.singletonList(Constants.PLATFORM_ID));

        // Get the necessary component clients
        PRClient searchClient = factory.getPRClient(Constants.PLATFORM_ID);
        RAPClient rapClient = factory.getRapClient();

        // Create the request
        PlatformRegistryQuery registryQuery = new PlatformRegistryQuery.Builder()
                .federationIds(Collections.singletonList(federationId))
                .names(Arrays.asList(resourceName))
                .build();

        System.out.println("Searching the Platform Registry of platform: " + Constants.PLATFORM_ID);
        FederationSearchResult result = searchClient.search(registryQuery, false, platformIds);


        if (result.getResources().size() == 0) {
            System.out.println("Could not find resource " + resourceName + " in federation " + federationId);
        } else {
            System.out.println("Trying to access resource " + result.getResources().get(0).getAggregationId() + " in federation " + federationId);
            List<Observation> observation = rapClient.getTopObservations(result.getResources().get(0).getFederatedResourceInfoMap().get(federationId).getoDataUrl(), number, true, platformIds);
            System.out.println("Finished getting latest observations of resource" + resourceName + " in federation " + federationId);
            return observation;
        }

        System.out.println("Error getting top observations of resource" + resourceName + " in federation " + federationId);
        return null;
    }

    public static Observation getLatestObservations(String resourceName, String fedId) {
        System.out.println("Started getting latest observations of resource" + resourceName + " in federation " + fedId);
        AbstractSymbIoTeClientFactory factory = SymbioteUtils.getClientFactory(Constants.PLATFORM_ID, Constants.USERNAME, Constants.PASSWORD, Constants.CLIENT_ID);

        // The set of platforms from which we are going to request credentials for our requests
        Set<String> platformIds = new HashSet<>(Collections.singletonList(Constants.PLATFORM_ID));

        // Get the necessary component clients
        PRClient searchClient = factory.getPRClient(Constants.PLATFORM_ID);
        RAPClient rapClient = factory.getRapClient();

        // Create the request
        PlatformRegistryQuery registryQuery = new PlatformRegistryQuery.Builder()
                .federationIds(Collections.singletonList(fedId))
                .names(Arrays.asList(resourceName))
                .build();

        System.out.println("Searching the Platform Registry of platform: " + Constants.PLATFORM_ID);
        FederationSearchResult result = searchClient.search(registryQuery, false, platformIds);

        if (result.getResources().size() == 0) {
            System.out.println("Could not find resources " + resourceName + " in federation " + fedId);
        } else {
            System.out.println("Trying to access resource " + result.getResources().get(0).getAggregationId() + " in federation " + fedId);
            Observation observation = rapClient.getLatestObservation(result.getResources().get(0).getFederatedResourceInfoMap().get(fedId).getoDataUrl(), true, platformIds);
            System.out.println("Finished getting latest observations of resource" + resourceName + " in federation " + fedId);
            return observation;
        }
        System.out.println("Error getting latest observations of resource" + resourceName + " in federation " + fedId);
        return null;
    }


    public static void listAllResourcesInFederation(String fedId) {
        System.out.println("Started listing resources in federation " + fedId);
        AbstractSymbIoTeClientFactory factory = SymbioteUtils.getClientFactory(Constants.PLATFORM_ID, Constants.USERNAME, Constants.PASSWORD, Constants.CLIENT_ID);
        // The set of platforms from which we are going to request credentials for our requests
        Set<String> platformIds = new HashSet<>(Collections.singletonList(Constants.PLATFORM_ID));

        // Get the necessary component clients
        PRClient searchClient = factory.getPRClient(Constants.PLATFORM_ID);

        // Create the request
        PlatformRegistryQuery registryQuery = new PlatformRegistryQuery.Builder()
                .federationIds(Collections.singletonList(fedId))
                .build();

        System.out.println("Searching the Platform Registry of platform: " + platformIds);
        FederationSearchResult result = searchClient.search(registryQuery, false, platformIds);
        for (FederatedResource resource : result.getResources()) {
            System.out.println(resource.getCloudResource().getResource().getName() + " in federation " + fedId + "(" + resource.getPlatformId() + ") - " + resource.getCloudResource().getResource().getDescription());
        }
        System.out.println("Listing resources in federation " + fedId + " finished");
    }

    public static void printResourceInformation(String resourceName, String fedId) {
        System.out.println("Printing resource information started");
        AbstractSymbIoTeClientFactory factory = SymbioteUtils.getClientFactory(Constants.PLATFORM_ID, Constants.USERNAME, Constants.PASSWORD, Constants.CLIENT_ID);

        // The set of platforms from which we are going to request credentials for our requests
        Set<String> platformIds = new HashSet<>(Collections.singletonList(Constants.PLATFORM_ID));

        // Get the necessary component clients
        PRClient searchClient = factory.getPRClient(Constants.PLATFORM_ID);

        // Create the request
        PlatformRegistryQuery registryQuery = new PlatformRegistryQuery.Builder()
                .federationIds(Collections.singletonList(fedId))
                .names(Arrays.asList(resourceName))
                .build();

        // Printing output
        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);

        System.out.println("Searching the Platform Registry of platform: " + Constants.PLATFORM_ID);
        FederationSearchResult result = searchClient.search(registryQuery, false, platformIds);
        if (result.getResources().size() == 0) {
            System.out.println("Could not find resource " + resourceName + " in federation " + fedId);
        } else {
            System.out.println("Resource: \n " + result.getResources().get(0).getAggregationId() + " in federation " + fedId);
            try {
                System.out.println(om.writeValueAsString(result.getResources().get(0).getCloudResource()));
            } catch (Exception ex) {
                System.out.println("Error writing resource description of " + resourceName + " in federation " + fedId);
                return;
            }
        }
        System.out.println("Printing resource information finished");
    }
}
