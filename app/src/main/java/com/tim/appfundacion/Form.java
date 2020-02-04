package com.tim.appfundacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.tim.appfundacion.Entities.Cargo;
import com.tim.appfundacion.Entities.Department;
import com.tim.appfundacion.Entities.Employee;
import com.tim.appfundacion.Entities.Nacionality;
import com.tim.appfundacion.Model.CargoHtppModel;
import com.tim.appfundacion.Model.DepartmentHtppModel;
import com.tim.appfundacion.Model.EmployeeHttpModel;
import com.tim.appfundacion.Model.NacionalityHttp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Form extends AppCompatActivity {
    private ArrayList<Nacionality> nacionalidades;
    private ArrayList<Cargo> cargos;
    private ArrayList<Department> departamentos;
    TextView txt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        System.out.println("here");
        txt =(TextView) findViewById(R.id.txt);
        ArrayList<Nacionality> nacionalidades = new NacionalityHttp(this).getNacionalidades();
        new CargoHtppModel(this).getCargos();
        new DepartmentHtppModel(this).getDepartamentos();
        //System.out.println("here");

        System.out.println("here");


        //boolean x = new EmployeeHttpModel().saveEmployee(employees.get(0));
        //System.out.println("band: "+x);
    }
    public void addNationality(Nacionality nac){
        //txt =(TextView) findViewById(R.id.txt);
        System.out.println(nac.toString());
        txt.setText(txt.getText()+"\n"+nac.toString());
        System.out.println("pass");
    }

    public void addCargo(Cargo cargo) {
        txt.setText(txt.getText()+"\n"+cargo.toString());
    }

    public void addDepartment(Department department) {
        txt.setText(txt.getText()+"\n"+department.toString());
    }
}
