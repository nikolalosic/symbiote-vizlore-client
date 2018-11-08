package com.symbiote.vizlore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory;
import eu.h2020.symbiote.client.interfaces.CRAMClient;
import eu.h2020.symbiote.client.interfaces.PRClient;
import eu.h2020.symbiote.client.interfaces.RAPClient;
import eu.h2020.symbiote.client.interfaces.SearchClient;
import eu.h2020.symbiote.cloud.model.internal.FederationSearchResult;
import eu.h2020.symbiote.cloud.model.internal.PlatformRegistryQuery;
import eu.h2020.symbiote.core.ci.QueryResourceResult;
import eu.h2020.symbiote.core.ci.QueryResponse;
import eu.h2020.symbiote.core.internal.CoreQueryRequest;
import eu.h2020.symbiote.core.internal.cram.ResourceUrlsResponse;
import eu.h2020.symbiote.model.cim.Observation;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;

import java.security.NoSuchAlgorithmException;
import java.util.*;

import static eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory.*;

public class L1ClientWithGuestToken {

    public static void main(String[] args) {

        /*
        Get the factory and the component clients
         */

        // FILL ME
        //String coreAddress = "https://symbiote-open.man.poznan.pl";
        String coreAddress = "https://symbiote-open.man.poznan.pl";
        String keystorePath = "testKeystore.jks";
        String keystorePassword = "testKeystore";
        String platformIdToBeUsedInSearch = "educampus";
        Type type = Type.FEIGN;

        // Get the configuration
        Config config = new Config(coreAddress, keystorePath, keystorePassword, type);

        // Get the factory
        AbstractSymbIoTeClientFactory factory;
        try {
            factory = getFactory(config);
        } catch (SecurityHandlerException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return;
        }

        // Get the necessary component clients
        SearchClient searchClient = factory.getSearchClient();
        CRAMClient cramClient = factory.getCramClient();
        RAPClient rapClient = factory.getRapClient();


        /*
        Search for resources in Core
         */

        // Create the request
        // Here, we specify just one search criteria, which is the platform id. You can add more criteria, such as
        // platform name, location, etc. You can check what the CoreQueryRequest.Builder supports
        CoreQueryRequest coreQueryRequest = new CoreQueryRequest.Builder()
                .platformId(platformIdToBeUsedInSearch)
                .build();

        // Send the request and validate the Search response
        QueryResponse queryResponse = searchClient.searchAsGuest(coreQueryRequest, true);


        /*
        Ask CRAM for the specific resource url
         */

        // Here, we request the url of only the first resource contained in the Search response. We also validate the
        // CRAM response
        for (QueryResourceResult queryResourceResult : queryResponse.getResources()) {
            System.out.println(queryResourceResult.getName());
        }
        String resourceId = queryResponse.getResources().get(2).getId();
        ResourceUrlsResponse resourceUrlsResponse = cramClient.getResourceUrlAsGuest(resourceId, true);
        String resourceUrl = resourceUrlsResponse.getBody().get(resourceId);


        /*
        Get observations from RAP
         */

        // Here, we request the latest 10 observations from RAP
        List<Observation> observations = rapClient.getTopObservationsAsGuest(resourceUrl, 10, true);
        System.out.println(observations);
    }


    public static String invokeService(String resourceName, String body, String platformId) {

        String coreAddress = "https://symbiote-open.man.poznan.pl";
        String keystorePath = "testKeystore.jks";
        String keystorePassword = "testKeystore";
        String platformIdToBeUsedInSearch = platformId;
        Type type = Type.FEIGN;

        // Get the configuration
        Config config = new Config(coreAddress, keystorePath, keystorePassword, type);

        // Get the factory
        AbstractSymbIoTeClientFactory factory;
        try {
            factory = getFactory(config);
        } catch (SecurityHandlerException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        // The set of platforms from which we are going to request credentials for our requests
        Set<String> platformIds = new HashSet<>(Collections.singletonList(platformId));

        // Get the necessary component clients
        SearchClient searchClient = factory.getSearchClient();
        CRAMClient cramClient = factory.getCramClient();
        RAPClient rapClient = factory.getRapClient();

        /*
        Search for resources in Core
         */

        // Create the request
        // Here, we specify just one search criteria, which is the platform id. You can add more criteria, such as
        // platform name, location, etc. You can check what the CoreQueryRequest.Builder supports
        CoreQueryRequest coreQueryRequest = new CoreQueryRequest.Builder()
                .platformId(platformIdToBeUsedInSearch)
                .name(resourceName)
                .build();

        // Send the request and validate the Search response
        QueryResponse queryResponse = searchClient.searchAsGuest(coreQueryRequest, true);
        System.out.println("Searching the Platform Registry of platform: " + platformIdToBeUsedInSearch);

        if (queryResponse.getResources().size() == 0) {
            System.out.println("Could not find any resources");
        } else {
            String resourceId = queryResponse.getResources().get(0).getId();
            ResourceUrlsResponse resourceUrlsResponse = cramClient.getResourceUrlAsGuest(resourceId, true);
            String resourceUrl = resourceUrlsResponse.getBody().get(resourceId);

            System.out.println("Trying to access resource " + queryResponse.getResources().get(0).getId());
            String res = rapClient.invokeService(resourceUrl, body, true, platformIds);
            return res;
        }

        System.out.println("END");
        return null;
    }

    /*
    public static List<Observation> getTopObservations(String resourceName, Integer number) {

        // FILL ME
        String coreAAMAddress = "https://symbiote-open.man.poznan.pl";
        String keystorePath = "testKeystore.jks";
        String keystorePassword = "testKeystore";
        Type type = Type.FEIGN;
        String platformId = Constants.PLATFORM_ID;
        String federationId = fedId;
        String username = Constants.USERNAME; // Username of the registered user to the PAAM
        String password = Constants.PASSWORD; // Password of the registered user to the PAAM
        String clientId = Constants.CLIENT_ID;


        // Printing output
        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);

        // Get the configuration
        Config config = new Config(coreAAMAddress, keystorePath, keystorePassword, type);

        // Get the factory
        AbstractSymbIoTeClientFactory factory;
        try {
            factory = getFactory(config);

            // OPTIONAL section... needs to be run only once
            // - per new platform
            // and/or after revoking client certificate in an already initialized platform


            // ATTENTION: This MUST be an interactive procedure to avoid persisting credentials (password)
            // Here, you can add credentials FOR MORE THAN 1 platforms
            Set<HomePlatformCredentials> platformCredentials = new HashSet<>();

            HomePlatformCredentials exampleHomePlatformCredentials = new HomePlatformCredentials(
                    platformId,
                    username,
                    password,
                    clientId);
            platformCredentials.add(exampleHomePlatformCredentials);


            // Get Certificates for the specified platforms
            factory.initializeInHomePlatforms(platformCredentials);

            // end of optional section..
            // After running it the first time and creating the client keystore you should comment out this section.
        } catch (SecurityHandlerException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        // The set of platforms from which we are going to request credentials for our requests
        Set<String> platformIds = new HashSet<>(Collections.singletonList(platformId));

        // Get the necessary component clients
        PRClient searchClient = factory.getPRClient(platformId);
        RAPClient rapClient = factory.getRapClient();



       // Search for resources in the Platform Registry of the specified platform


        // Create the request
        // Here, we specify just one search criteria, which is the platform id. You can add more criteria, such as
        // platform name, location, etc. You can check what the PlatformRegistryQuery.Builder supports.
        // If you specify no criteria, all the L2 resources will be returned
        PlatformRegistryQuery registryQuery = new PlatformRegistryQuery.Builder()
                .federationIds(Collections.singletonList(federationId))
                .names(Arrays.asList(resourceName))
                .build();

        System.out.println("Searching the Platform Registry of platform: " + platformId);
        FederationSearchResult result = searchClient.search(registryQuery, false, platformIds);


        if (result.getResources().size() == 0) {
            System.out.println("Could not find any resources");
        } else {
            System.out.println("Trying to access resource " + result.getResources().get(0).getAggregationId() + " in federation " + federationId);
            List<Observation> observation = rapClient.getTopObservations(result.getResources().get(0).getFederatedResourceInfoMap().get(federationId).getoDataUrl(), number, true, platformIds);
            return observation;
        }

        System.out.println("END");
        return null;
    }

    public static Observation getLatestObservations(String resourceName) {

        // FILL ME
        String coreAAMAddress = "https://symbiote-open.man.poznan.pl";
        String keystorePath = "testKeystore.jks";
        String keystorePassword = "testKeystore";
        Type type = Type.FEIGN;
        String platformId = Constants.PLATFORM_ID;
        String federationId = fedId;
        String username = Constants.USERNAME; // Username of the registered user to the PAAM
        String password = Constants.PASSWORD; // Password of the registered user to the PAAM
        String clientId = Constants.CLIENT_ID;


        // Printing output
        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);

        // Get the configuration
        Config config = new Config(coreAAMAddress, keystorePath, keystorePassword, type);

        // Get the factory
        AbstractSymbIoTeClientFactory factory;
        try {
            factory = getFactory(config);

            // OPTIONAL section... needs to be run only once
            // - per new platform
            // and/or after revoking client certificate in an already initialized platform


            // ATTENTION: This MUST be an interactive procedure to avoid persisting credentials (password)
            // Here, you can add credentials FOR MORE THAN 1 platforms
            Set<HomePlatformCredentials> platformCredentials = new HashSet<>();

            HomePlatformCredentials exampleHomePlatformCredentials = new HomePlatformCredentials(
                    platformId,
                    username,
                    password,
                    clientId);
            platformCredentials.add(exampleHomePlatformCredentials);


            // Get Certificates for the specified platforms
            factory.initializeInHomePlatforms(platformCredentials);

            // end of optional section..
            // After running it the first time and creating the client keystore you should comment out this section.
        } catch (SecurityHandlerException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        // The set of platforms from which we are going to request credentials for our requests
        Set<String> platformIds = new HashSet<>(Collections.singletonList(platformId));

        // Get the necessary component clients
        PRClient searchClient = factory.getPRClient(platformId);
        RAPClient rapClient = factory.getRapClient();



        //Search for resources in the Platform Registry of the specified platform


        // Create the request
        // Here, we specify just one search criteria, which is the platform id. You can add more criteria, such as
        // platform name, location, etc. You can check what the PlatformRegistryQuery.Builder supports.
        // If you specify no criteria, all the L2 resources will be returned
        PlatformRegistryQuery registryQuery = new PlatformRegistryQuery.Builder()
                .federationIds(Collections.singletonList(federationId))
                .names(Arrays.asList(resourceName))
                .build();

        System.out.println("Searching the Platform Registry of platform: " + platformId);
        FederationSearchResult result = searchClient.search(registryQuery, false, platformIds);

        if (result.getResources().size() == 0) {
            System.out.println("Could not find any resources");
        } else {
            System.out.println("Trying to access resource " + result.getResources().get(0).getAggregationId() + " in federation " + federationId);
            Observation observation = rapClient.getLatestObservation(result.getResources().get(0).getFederatedResourceInfoMap().get(federationId).getoDataUrl(), true, platformIds);
            return observation;
        }
        System.out.println("END");
        return null;
    }*/


}
