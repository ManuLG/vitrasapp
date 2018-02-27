package com.mlorenzo.vitrasapp.stopDetail;

/**
 *  Created by mlorenzo on 27/02/2018.
 */

public class EstimateInformation {

    String line = "";
    String destination = "";
    String remaining_time = "";

    public EstimateInformation(String line, String destination, String remaining_time) {
        this.line = line;
        this.destination = destination;
        this.remaining_time = remaining_time;
    }
}
