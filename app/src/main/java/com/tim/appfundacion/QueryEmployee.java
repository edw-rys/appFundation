package com.tim.appfundacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.tim.appfundacion.Entities.Employee;
import com.tim.appfundacion.Model.EmployeeHttpModel;
import com.tim.appfundacion.Others.Adapter;

import java.util.ArrayList;

public class QueryEmployee extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Employee> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_employee);
        items = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Employee> employees= new EmployeeHttpModel(this).getEmployees();


    }
    public void cargarDatos(ArrayList<Employee> employees){
        for (Employee employee:employees){
            items.add(employee);
        }

        adapter = new Adapter(this,items);
        recyclerView.setAdapter(adapter);
    }
}
