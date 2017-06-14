package com.example.carlosprieto.tfg;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.MalformedJsonException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UTFDataFormatException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static com.example.carlosprieto.tfg.MainActivity.tempHum;


/**
 * Created by carlosprieto on 11/01/17.
 */

public class ClienteSocket implements Runnable {

    private Socket socket;


    @Override
    public void run() {

        try {
            InetAddress HOST = InetAddress.getByName("192.168.1.140");
            int PORT = 80;

            socket = new Socket(HOST, PORT);


            InputStream tmp = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(tmp));

            JsonReader reader = new JsonReader(in);

            reader.beginObject();

            while (reader.hasNext()){
                String name = reader.nextName();
                if (name.equals("t")){
                    tempHum.setTemperatura(reader.nextInt());
                } else if(name.equals("h")){
                    tempHum.setHumedad(reader.nextInt());
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            reader.close();



            socket.close();


        } catch (UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
