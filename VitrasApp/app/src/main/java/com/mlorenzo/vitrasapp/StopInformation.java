package com.mlorenzo.vitrasapp;

/**
 *  Created by mlorenzo on 26/02/2018.
 */

public class StopInformation {

    protected String name = "Nombre de la parada";
    protected String number = "";
    protected String[] location = new String[2];

    public StopInformation() { }

    public StopInformation(String name) {
        this.name = name;
    }
}
