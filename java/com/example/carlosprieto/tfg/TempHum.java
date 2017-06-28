package com.example.carlosprieto.tfg;

/**
 * Created by carlosprieto on 14/6/17.
 */

public class TempHum {
    private double temperatura;
    private double humedad;
    private String fallo;

    public TempHum(){};

    public TempHum(int temperatura, int humedad, String fallo){
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.fallo = fallo;
    }


    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getHumedad() {
        return humedad;
    }

    public void setHumedad(double humedad) {
        this.humedad = humedad;
    }

    public String getFallo() { return fallo; }

    public void setFallo(String fallo) { this.fallo = fallo; }
}
