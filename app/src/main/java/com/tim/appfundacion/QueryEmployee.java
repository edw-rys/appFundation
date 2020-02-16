package com.tim.appfundacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.tim.appfundacion.Entities.Employee;
import com.tim.appfundacion.Model.EmployeeHttpModel;
import com.tim.appfundacion.Others.Adapter;

import java.util.ArrayList;

public class QueryEmployee extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Employee> items;
    // progress bar
    ProgressDialog progressDialog;
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
    // start progress bar
    public void startProgressSave(){
        progressDialog = new ProgressDialog(QueryEmployee.this);
        // show dialog
        progressDialog.show();
        // Set content View
        progressDialog.setContentView(R.layout.progress_dialog);
        // set transparent Background
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
    }
    public void stopProgressDialog(){
        System.out.println("stop");
        progressDialog.dismiss();
    }
    public void showProgressError(String title,String message){
        progressDialog = new ProgressDialog(QueryEmployee.this);
        // show dialog
        progressDialog.show();
        // Set content View
        progressDialog.setContentView(R.layout.dialog_error);
        TextView txtMssg = (TextView) progressDialog.findViewById(R.id.message_err_save);
        TextView txtTitle = (TextView) progressDialog.findViewById(R.id.title_err_mssg);
        ImageView img = (ImageView) progressDialog.findViewById(R.id.imgErr_not);
        img.setImageResource(R.drawable.lazy);
        txtMssg.setText(message);
        txtTitle.setText(title);
        // set transparent Background
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
    }
}
