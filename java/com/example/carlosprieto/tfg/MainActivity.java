package com.example.carlosprieto.tfg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn;
    TextView tv;
    static TempHum tempHum = new TempHum();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.boton);
        btn.setOnClickListener(this);
        tv = (TextView) findViewById(R.id.temp);
        // se inicia un socket al cargar la actividad
        new Thread(new ClienteSocket()).start();
        tv.setText("");

    }
    @Override
    public void onClick(View view){

        try {
            // se inicia un nuevo socket al hacer click en el botón

            new Thread(new ClienteSocket()).start();
            double tmp = tempHum.getTemperatura();
            double hum = tempHum.getHumedad();
            String t = Double.toString(tmp);
            String h = Double.toString(hum);
            tv.setText("Temperatura: " + t + "\nHumedad: " + h);


        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
