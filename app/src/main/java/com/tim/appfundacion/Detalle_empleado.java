package com.tim.appfundacion;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Detalle_empleado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_empleado);
        Intent i = getIntent();

        // Botón de retroceder
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        // Set text
        TextView names = (TextView) findViewById(R.id.textDetailNames);
        names.setText(i.getStringExtra("names"));

        // Set text
        TextView dni = (TextView) findViewById(R.id.textDetailDni);
        dni.setText(i.getStringExtra("dni"));
        // Set text
        TextView nacionality = (TextView) findViewById(R.id.textDetailNacionality);
        nacionality.setText(i.getStringExtra("nacionality"));
        // Set text
        TextView cargo = (TextView) findViewById(R.id.textDetailCargo);
        cargo.setText(i.getStringExtra("cargo"));
        // Set text
        TextView gender = (TextView) findViewById(R.id.textDetailGender);
        gender.setText(i.getStringExtra("gender"));
        // Set text
        TextView department = (TextView) findViewById(R.id.textDetailDepartment);
        department.setText(i.getStringExtra("department"));
        // Set text
        TextView birthDate = (TextView) findViewById(R.id.textDetailBirthDate);
        birthDate.setText(i.getStringExtra("birthDate"));
        // Set text
        TextView tipoPago = (TextView) findViewById(R.id.textDetailTipoPago);
        tipoPago.setText(i.getStringExtra("tipoPago"));
        // Set text
        TextView salary = (TextView) findViewById(R.id.textDetailSalary);
        salary.setText(i.getStringExtra("salary")+"");
        // Set text
        TextView titleAcademic = (TextView) findViewById(R.id.textDetailTitleAcademic);
        titleAcademic.setText(i.getStringExtra("titleAcademic"));
        // Set text
        TextView levelAcademic = (TextView) findViewById(R.id.textDetailLevelAcademic);
        levelAcademic.setText(i.getStringExtra("levelAcademic"));
        // Set text
        TextView dateAdm = (TextView) findViewById(R.id.textDetailDateAdm);
        dateAdm.setText(i.getStringExtra("dateAdm"));

        ImageView imgIcon = (ImageView) findViewById(R.id.imgDetailIcon);
        imgIcon.setImageResource(
                i.getStringExtra("gender").equals("femenino")?
                        R.drawable.samoyed_female:
                        R.drawable.samoyed_fm
        );
    }
}
