package com.symbiote.vizlore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import static eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory.getFactory;

public class SymbioteUtils {
    public static AbstractSymbIoTeClientFactory getClientFactory(String platformId, String username, String password, String clientId) {
        // FILL ME
        String coreAAMAddress = "https://symbiote-open.man.poznan.pl";
        String keystorePath = "testKeystore.jks";
        String keystorePassword = "testKeystore";
        AbstractSymbIoTeClientFactory.Type type = AbstractSymbIoTeClientFactory.Type.FEIGN;
        // Printing output
        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);

        // Get the configuration
        AbstractSymbIoTeClientFactory.Config config = new AbstractSymbIoTeClientFactory.Config(coreAAMAddress, keystorePath, keystorePassword, type);

        // Get the factory
        AbstractSymbIoTeClientFactory factory;
        try {
            factory = getFactory(config);
            // ATTENTION: This MUST be an interactive procedure to avoid persisting credentials (password)
            // Here, you can add credentials FOR MORE THAN 1 platforms
            Set<AbstractSymbIoTeClientFactory.HomePlatformCredentials> platformCredentials = new HashSet<>();

            AbstractSymbIoTeClientFactory.HomePlatformCredentials exampleHomePlatformCredentials = new AbstractSymbIoTeClientFactory.HomePlatformCredentials(
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
        return factory;
    }


    public static AbstractSymbIoTeClientFactory getGuestClientFactory() {
        String coreAddress = "https://symbiote-open.man.poznan.pl";
        String keystorePath = "testKeystore.jks";
        String keystorePassword = "testKeystore";
        AbstractSymbIoTeClientFactory.Type type = AbstractSymbIoTeClientFactory.Type.FEIGN;

        // Get the configuration
        AbstractSymbIoTeClientFactory.Config config = new AbstractSymbIoTeClientFactory.Config(coreAddress, keystorePath, keystorePassword, type);

        // Get the factory
        AbstractSymbIoTeClientFactory factory;
        try {
            factory = getFactory(config);
        } catch (SecurityHandlerException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return factory;

    }

}
