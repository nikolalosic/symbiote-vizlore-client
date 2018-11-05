package com.symbiote.vizlore;

import eu.h2020.symbiote.model.cim.Observation;
import eu.h2020.symbiote.model.cim.ObservationValue;

import java.util.List;

public class Utils {

    public static void printObservationsValues(List<Observation> observations) {
        for (Observation observation : observations) {
            System.out.println("---------------------------------------------------------------------");
            for (ObservationValue observationValue : observation.getObsValues()) {
                System.out.println(observationValue.getObsProperty().getName() + "=" + observationValue.getValue());
            }
            System.out.println("---------------------------------------------------------------------");
        }
    }
}
