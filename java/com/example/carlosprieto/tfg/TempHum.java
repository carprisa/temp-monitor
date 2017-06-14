package com.example.carlosprieto.tfg;

/**
 * Created by carlosprieto on 14/6/17.
 */

public class TempHum {
    private int temperatura;
    private int humedad;

    public TempHum(){};

    public TempHum(int temperatura, int humedad){
        this.temperatura = temperatura;
        this.humedad = humedad;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public int getHumedad() {
        return humedad;
    }

    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }
}
