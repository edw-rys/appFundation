package com.tim.appfundacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.tim.appfundacion.Entities.Employee;
import com.tim.appfundacion.Entities.Nacionality;
import com.tim.appfundacion.Model.EmployeeHttpModel;
import com.tim.appfundacion.Model.NacionalityHttp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ArrayList<Nacionality> nacionalidades = new NacionalityHttp().getNacionalidades();
        TextView txt = (TextView) findViewById(R.id.txt);

        ArrayList<Employee> employees= new EmployeeHttpModel().getEmployees();
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");

        txt.setText(employees.get(1).toString());
        boolean isSave = new EmployeeHttpModel().saveEmployee(employees.get(1));
        txt.setText(txt.getText()+"\nEstado: "+isSave);


    }
}
