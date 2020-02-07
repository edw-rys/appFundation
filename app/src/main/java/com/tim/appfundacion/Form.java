package com.tim.appfundacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tim.appfundacion.Entities.Cargo;
import com.tim.appfundacion.Entities.Department;
import com.tim.appfundacion.Entities.Employee;
import com.tim.appfundacion.Entities.Nacionality;
import com.tim.appfundacion.Model.CargoHtppModel;
import com.tim.appfundacion.Model.DepartmentHtppModel;
import com.tim.appfundacion.Model.EmployeeHttpModel;
import com.tim.appfundacion.Model.NacionalityHttp;
import com.tim.appfundacion.Others.ValidateFormEmployee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Form extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Nacionality> nacionalidades;
    private ArrayList<Cargo> cargos;
    private ArrayList<Department> departamentos;
    TextView txt ;
    // data spinner
    private ArrayAdapter<Nacionality> comboNacionality;
    private ArrayAdapter<Cargo> comboCargos;
    private ArrayAdapter<Department> comboDepartmens;
    private ArrayAdapter<String> levelsAcademics ;
    // Views
        // first section
    private Spinner spNacionality;
    private TextView txtBirthDateShow;
    private EditText inputDni;
    private EditText inputLastName;
    private EditText inputName;
    private EditText inputTelf;
    private RadioButton rdMale;
    private RadioButton rdFemale;
        // second section
    private TextView txtDateAdmisionShow;
    private Spinner spDepartment;
    private Spinner spCargo;
    private EditText inputSalary;
        // Third section
    private Spinner spLvlAcademic;
    private EditText inputTitleAcademic;
        // buttons
    private Button btnSig_v1;
    private Button btnCancel_v1;
    private Button btnSig_v2;
    private Button btnSave;
    private Button btnPrev_v2;
    private Button btnPrev_v3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // views
        setViewsObj_form_1();
        // datos


        System.out.println("here");
        //txt =(TextView) findViewById(R.id.txt);


        //boolean x = new EmployeeHttpModel().saveEmployee(employees.get(0));
        //System.out.println("band: "+x);
    }

    /**
     * CAMBIAR VENTANAS
     */
    private void setViewsObj_form_1(){
        spNacionality = (Spinner) findViewById(R.id.spNacionality);
        txtBirthDateShow = (TextView) findViewById(R.id.txtBirthDateShow);
        inputDni = (EditText) findViewById(R.id.inputDni);
        inputLastName = (EditText) findViewById(R.id.inputLastName);
        inputName = (EditText) findViewById(R.id.inputName);
        inputTelf = (EditText) findViewById(R.id.inputTelf);
        rdMale = (RadioButton) findViewById(R.id.rdMale);
        rdFemale = (RadioButton) findViewById(R.id.rdFemale);


        btnSig_v1 = (Button) findViewById(R.id.btnNextV1);
        btnCancel_v1 = (Button) findViewById(R.id.btnCancelV1);



        //Set on click listener
        txtBirthDateShow.setOnClickListener(this);
        btnSig_v1.setOnClickListener(this);
        btnCancel_v1.setOnClickListener(this);

        // cargar datos
        new NacionalityHttp(this).getNacionalidades();
    }
    private void setViewsObj_form_2(){
        // campos
        txtDateAdmisionShow =(TextView) findViewById(R.id.txtDateAdmisionShow);;
        spDepartment =(Spinner) findViewById(R.id.spDepartment);
        spCargo =(Spinner) findViewById(R.id.spCargo);
        inputSalary = (EditText) findViewById(R.id.inputSalary);

        // botones
        btnSig_v2 = (Button) findViewById(R.id.btnNextV2);
        btnPrev_v2 = (Button) findViewById(R.id.btnPreviousV2);

        //Set on click listener
        txtDateAdmisionShow.setOnClickListener(this);
        btnSig_v2.setOnClickListener(this);
        btnPrev_v2.setOnClickListener(this);

        // cargar datos
        new CargoHtppModel(this).getCargos();
        new DepartmentHtppModel(this).getDepartamentos();
    }
    private void setViewsObj_form_3(){
        // campos
        inputTitleAcademic =(EditText) findViewById(R.id.inputTitleAcademic);;
        spLvlAcademic = (Spinner) findViewById(R.id.spLevelAcademic);
        // botones
        btnSave = (Button) findViewById(R.id.btnSave);
        btnPrev_v3 = (Button) findViewById(R.id.btnPreviousV3);
        //Set on click listener
        btnSave.setOnClickListener(this);
        btnPrev_v3.setOnClickListener(this);

        addLvlAcademic();
    }
    public void addLvlAcademic(){
        String [] datosLvlAcademic = {
                "Diplomado","Técnico","Bachillerato","Licenciatura","Maestría","Especialidad","Doctorado"
        };
        levelsAcademics = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,datosLvlAcademic );
        spLvlAcademic.setAdapter(levelsAcademics);
    }
    public void addNationality(ArrayList<Nacionality> nacionalidades){
        comboNacionality = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,nacionalidades);
        spNacionality.setAdapter(comboNacionality);
    }

    public void addCargo(ArrayList<Cargo> cargos) {
        comboCargos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,cargos);
        spCargo.setAdapter(comboCargos);
    }

    public void addDepartment(ArrayList<Department> departments) {
        comboDepartmens = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,departments);
        spDepartment.setAdapter(comboDepartmens);
    }

    @Override
    public void onClick(View v) {
        if (v == txtBirthDateShow){
            final Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    txtBirthDateShow.setText(dayOfMonth+ "/"+month+"/"+year);
                }
                },Calendar.YEAR,Calendar.MONTH, Calendar.DAY_OF_MONTH
            );
            datePickerDialog.show();
        } else if(v == txtDateAdmisionShow){
            final Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    txtDateAdmisionShow.setText(dayOfMonth+ "/"+month+"/"+year);
                }
            },Calendar.YEAR,Calendar.MONTH, Calendar.DAY_OF_MONTH
            );
            datePickerDialog.show();
        }
        if (v == btnSig_v1){
            // validacion de datos
            if(validateDataV1()){
                setContentView(R.layout.form_data_work);
                setViewsObj_form_2();
            }else{

            }

        }else if (v==btnCancel_v1){
            startActivity(new Intent(this, ViewMain.class));
        }else if (v == btnSig_v2){
            if(validateDataV2()) {
                setContentView(R.layout.form_data_academic);
                setViewsObj_form_3();
            }else {

            }
        } else if (v== btnPrev_v2){
            setContentView(R.layout.activity_form);
            setViewsObj_form_1();
        }else if (v==btnSave){
            if(validateDataV3()) {}
        }else if (v==btnPrev_v3){
            setContentView(R.layout.form_data_work);
            setViewsObj_form_2();

        }
    }

    private boolean validateDataV1(){
        return ValidateFormEmployee.validatePersonalData(
                spNacionality.getSelectedItem(),
                txtBirthDateShow.getText().toString(),
                inputDni.getText().toString(),
                inputLastName.getText().toString(),
                inputName.getText().toString(),
                inputTelf.getText().toString(),
                rdMale.isChecked(),
                rdMale.isChecked()
        );
    }
    private boolean validateDataV2(){
        return ValidateFormEmployee.validateDataWork(
                txtDateAdmisionShow.getText().toString(),
                spDepartment.getSelectedItem(),
                spCargo.getSelectedItem(),
                inputSalary.getText().toString()
        );
    }
    private boolean validateDataV3(){
        return ValidateFormEmployee.validateDataAcademic(
                spLvlAcademic.getSelectedItem(),
                inputTitleAcademic.getText().toString()
        );
    }
}
