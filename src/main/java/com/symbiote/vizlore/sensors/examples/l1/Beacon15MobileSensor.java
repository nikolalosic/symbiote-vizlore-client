package com.symbiote.vizlore.sensors.examples.l1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.symbiote.vizlore.Constants;
import com.symbiote.vizlore.L1ClientWithGuestToken;
import com.symbiote.vizlore.L2ClientWithHomeToken;
import com.symbiote.vizlore.Utils;
import eu.h2020.symbiote.model.cim.Observation;

import java.util.List;

public class Beacon15MobileSensor {
    public static void main(String[] args) {
        // Printing output
        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);
        List<Observation> observations = L1ClientWithGuestToken.getTopObservations(
                "beacon-15-B9407F30-F5F8-466E-AFF9-25556B57FE6D",
                10,
                Constants.PLATFORM_ID_VIZLORE);
        try {
            System.out.println(om.writeValueAsString(observations));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Utils.printObservationsValues(observations);
    }
}
