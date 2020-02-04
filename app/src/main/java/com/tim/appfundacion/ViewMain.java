package com.tim.appfundacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_main);
    }

    public void openQueryEmployee(View view){
        startActivity(new Intent(this, QueryEmployee.class));
    }
    public void openFormEmployee(View view){
        startActivity(new Intent(this, Form.class));
    }
}
