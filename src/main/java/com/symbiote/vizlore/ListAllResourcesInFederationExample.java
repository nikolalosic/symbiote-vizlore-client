package com.symbiote.vizlore;

public class ListAllResourcesInFederationExample {
    public static void main(String[] args) {
        System.out.println("Listing all federation resources");
        L2ClientWithHomeToken.listAllResourcesInFederation(Constants.FEDERATION_ID);
        System.out.println("Listing all federation resources finished");
    }
}
