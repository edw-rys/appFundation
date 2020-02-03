package com.tim.appfundacion;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import com.tim.appfundacion.Entities.Nacionality;
import com.tim.appfundacion.Http.OnHttpRequestComplete;
import com.tim.appfundacion.Http.Response;
import com.tim.appfundacion.Model.NacionalityHttp;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnHttpRequestComplete {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, MainActivity.class));

        ArrayList<Nacionality> nacionalidades = new NacionalityHttp().getNacionalidades();
        for(Nacionality nac: nacionalidades){
            System.out.println(nac);
        }
    }
    private int getX(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
    private int getY(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    @Override
    public void onComplete(Response status) {

    }
}
