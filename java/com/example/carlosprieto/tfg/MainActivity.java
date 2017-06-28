package com.example.carlosprieto.tfg;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.carlosprieto.tfg.ClienteSocket.conectado;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn;
    TextView tv;
    TextView tempOld;
    static TempHum tempHum = new TempHum();
    Double tmpOld;
    Double humOld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Bloqueamos la vista landscape y lo dejamos sólo en portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.boton);
        // ponemos el botón a la ecucha del evento click o pulsado
        btn.setOnClickListener(this);
        tv = (TextView) findViewById(R.id.temp);
        tempOld = (TextView) findViewById(R.id.temp_previo);
        // se inicia un socket al cargar la actividad
        new Thread(new ClienteSocket()).start();
        // se limpia de resultados el textView donde aparecerá la temperatura al iniciar la activity
        tv.setText("");


    }
    @Override
    public void onClick(View view){

        try {
            // se inicia un nuevo socket al hacer click en el botón
            new Thread(new ClienteSocket()).start();
            // almacenamos en variables double y String las salidas del método de la clase TempHum
            Double tmp = tempHum.getTemperatura();
            Double hum = tempHum.getHumedad();
            String fallo = tempHum.getFallo();
            // en el campo de T y H previa, si la actividad es nueva no muestra nada por no haber
            // asignación de valores previos
            if ((tmpOld != null) && (humOld != null)) {
                tempOld.setText("T: " + String.format("%.2f", tmpOld) + " ºC\nH: "
                        + String.format("%.2f", humOld) + " %");
            }
            tmpOld = tmp;
            humOld = hum;

            // comprueba si hay socket conectado para mostrar una info en pantalla u otra
            if(conectado){
                if(fallo.isEmpty()){
                    tv.setText("Temperature:\n" + String.format("%.2f", tmp) + " ºC"
                            + "\n\nHumidity:\n " + String.format("%.2f", hum) + " %");

                } else { tv.setText("Ups! it seems that...\n" + fallo); }

            } else {
                tv.setText("Ooooh...\nthe system is not connected!");
            }


        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
