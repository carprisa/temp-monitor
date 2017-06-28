package com.example.carlosprieto.tfg;

import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static com.example.carlosprieto.tfg.MainActivity.tempHum;



/**
 * Created by carlosprieto on 11/01/17.
 */

public class ClienteSocket implements Runnable {

    private Socket socket;
    static boolean conectado = false;


    @Override
    public void run() {

        try {

            // preparamos el socket
            InetAddress HOST = InetAddress.getByName("192.168.1.2");
            int PORT = 80;

            socket = new Socket(HOST, PORT);

            // comprobamos si se ha establecido la conexión para avisar al usuario
            if (socket.isConnected()) {
                conectado = true;
            }

            // se recibe el json del sistema de temperatura
            InputStream tmp = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(tmp));

            JsonReader reader = new JsonReader(in);

            // a partir de aquí implementamos un parser para el JSON recibido:
            // esta información se mostrará en pantalla

            reader.beginObject();
            // el JSON tiene 3 campos: "t":temperatura, "h":humedad y "f":errores
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("t")) {
                    tempHum.setTemperatura(reader.nextDouble());
                } else if (name.equals("h")) {
                    tempHum.setHumedad(reader.nextDouble());
                } else if (name.equals("f")) {
                    tempHum.setFallo(reader.nextString());
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
