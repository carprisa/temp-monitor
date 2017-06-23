package com.example.carlosprieto.tfg;

/**
 * Created by carlosprieto on 14/6/17.
 */

public class TempHum {
    private double temperatura;
    private double humedad;

    public TempHum(){};

    public TempHum(int temperatura, int humedad){
        this.temperatura = temperatura;
        this.humedad = humedad;
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
}
