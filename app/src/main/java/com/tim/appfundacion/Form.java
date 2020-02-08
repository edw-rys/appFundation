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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tim.appfundacion.Entities.Cargo;
import com.tim.appfundacion.Entities.DataWork;
import com.tim.appfundacion.Entities.Department;
import com.tim.appfundacion.Entities.Employee;
import com.tim.appfundacion.Entities.Nacionality;
import com.tim.appfundacion.Model.CargoHtppModel;
import com.tim.appfundacion.Model.DepartmentHtppModel;
import com.tim.appfundacion.Model.EmployeeHttpModel;
import com.tim.appfundacion.Model.NacionalityHttp;
import com.tim.appfundacion.Others.DataError;
import com.tim.appfundacion.Others.ValidateFormEmployee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    private ArrayAdapter<String> tiposPagos ;
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
    private Spinner spTipoPago;
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

    // Datos empleado
    private Employee empleado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // views
        setViewsObj_form_1();
        // datos
        empleado = new Employee();
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
        spTipoPago = (Spinner) findViewById(R.id.spTipo_pago);
        // botones
        btnSig_v2 = (Button) findViewById(R.id.btnNextV2);
        btnPrev_v2 = (Button) findViewById(R.id.btnPreviousV2);

        //Set on click listener
        txtDateAdmisionShow.setOnClickListener(this);
        btnSig_v2.setOnClickListener(this);
        btnPrev_v2.setOnClickListener(this);

        // cargar datos
        addTipoPago();
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

    public void addTipoPago(){
        String [] datosTP = {
                "Mensual","Anual","Semestral","Quincenal","Trimestral"
        };
        tiposPagos = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,datosTP );
        spTipoPago.setAdapter(tiposPagos);
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
                },1990,Calendar.MONTH, 1
            );
            datePickerDialog.show();
        } else if(v == txtDateAdmisionShow){
            final Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    txtDateAdmisionShow.setText(dayOfMonth+ "/"+month+"/"+year);
                }
            },2020,Calendar.MONTH, Calendar.DAY_OF_MONTH
            );
            datePickerDialog.show();
        }
        if (v == btnSig_v1){
            // validacion de datos
            if(validateDataV1()){
                setContentView(R.layout.form_data_work);
                setDataEmployee(1);
                setViewsObj_form_2();
            }else{
                setMessagesError(ValidateFormEmployee.dataInfo);
            }

        }else if (v==btnCancel_v1){
            startActivity(new Intent(this, ViewMain.class));
        }else if (v == btnSig_v2){
            if(validateDataV2()) {
                setContentView(R.layout.form_data_academic);
                setDataEmployee(2);
                setViewsObj_form_3();
            }else {
                setMessagesError(ValidateFormEmployee.dataInfo);
            }
        } else if (v== btnPrev_v2){
            setContentView(R.layout.activity_form);
            setViewsObj_form_1();
        }else if (v==btnSave){
            if(validateDataV3()) {
                setDataEmployee(3);
                // Enviar datos por http
                boolean status=new EmployeeHttpModel(this).saveEmployee(empleado);
                System.out.println("ESTADO DE GUARDADO= "+status);
            }else {
                setMessagesError(ValidateFormEmployee.dataInfo);
            }
        }else if (v==btnPrev_v3){
            setContentView(R.layout.form_data_work);
            setViewsObj_form_2();

        }
    }

    private boolean validateDataV1(){
        clearMessageErr(1);
        return ValidateFormEmployee.validatePersonalData(
                spNacionality.getSelectedItem(),
                txtBirthDateShow.getText().toString(),
                inputDni.getText().toString(),
                inputLastName.getText().toString(),
                inputName.getText().toString(),
                inputTelf.getText().toString(),
                rdMale.isChecked(),
                rdFemale.isChecked()
        );
    }
    private boolean validateDataV2(){
        clearMessageErr(2);
        return ValidateFormEmployee.validateDataWork(
                txtDateAdmisionShow.getText().toString(),
                spDepartment.getSelectedItem(),
                spCargo.getSelectedItem(),
                inputSalary.getText().toString(),
                spTipoPago.getSelectedItem()
        );
    }
    private boolean validateDataV3(){
        clearMessageErr(3);
        return ValidateFormEmployee.validateDataAcademic(
                spLvlAcademic.getSelectedItem(),
                inputTitleAcademic.getText().toString()
        );
    }
    private void clearMessageErr(int numberView){
        switch (numberView){
            case 1:
                TextView txterr = (TextView) findViewById(R.id.txtErrGender);
                txterr.setText("");
                txterr = (TextView) findViewById(R.id.txtErrNac);
                txterr.setText("");
                txterr = (TextView) findViewById(R.id.txtErrBirthDate);
                txterr.setText("");
                break;
            case 2:
                TextView txterrV2 = (TextView) findViewById(R.id.txtErrDepartment);
                txterrV2.setText("");
                txterrV2 = (TextView) findViewById(R.id.txtErrCargo);
                txterrV2.setText("");
                txterrV2 = (TextView) findViewById(R.id.txtErrDateAdm);
                txterrV2.setText("");
                txterrV2 = (TextView) findViewById(R.id.txtErrTipoPago);
                txterrV2.setText("");
                break;
            case 3:
                TextView txterrV3 = (TextView) findViewById(R.id.txtErrLvlAcdm);
                txterrV3.setText("");
                break;
        }
    }
    // set messsages
    private void setMessagesError(ArrayList<DataError> listErrors){
        for(DataError error: listErrors){
            if (error.campo.equals(DataError.dni)){
                inputDni.setError(error.message);
            }
            if (error.campo.equals(DataError.lastName)){
                inputLastName.setError(error.message);
            }
            if (error.campo.equals(DataError.name)){
                inputName.setError(error.message);
            }
            if (error.campo.equals(DataError.birthdate)){
                TextView txterr = (TextView) findViewById(R.id.txtErrBirthDate);
                txterr.setText(error.message);
            }
            if (error.campo.equals(DataError.telf)){
                inputTelf.setError(error.message);
            }
            if (error.campo.equals(DataError.gender)){
                TextView txterr = (TextView) findViewById(R.id.txtErrGender);
                txterr.setText(error.message);
            }
            if (error.campo.equals(DataError.nacionality)){
                TextView txterr = (TextView) findViewById(R.id.txtErrNac);
                txterr.setText(error.message);
            }
            if (error.campo.equals(DataError.salary)){
                inputSalary.setError(error.message);
            }
            if (error.campo.equals(DataError.department)){
                TextView txterr = (TextView) findViewById(R.id.txtErrDepartment);
                txterr.setText(error.message);
            }
            if (error.campo.equals(DataError.cargo)){
                TextView txterr = (TextView) findViewById(R.id.txtErrCargo);
                txterr.setText(error.message);
            }
            if (error.campo.equals(DataError.dateAdmision)){
                TextView txterr = (TextView) findViewById(R.id.txtErrDateAdm);
                txterr.setText(error.message);
            }
            if (error.campo.equals(DataError.tipoPago)){
                TextView txterr = (TextView) findViewById(R.id.txtErrTipoPago);
                txterr.setText(error.message);
            }

            if (error.campo.equals(DataError.titleAcademic)){
                inputTitleAcademic.setError(error.message);
            }
            if (error.campo.equals(DataError.levelAcademic)){
                TextView txterr = (TextView) findViewById(R.id.txtErrLvlAcdm);
                txterr.setText(error.message);
            }
        }
    }
    private void setDataEmployee(int numberView){
        switch (numberView){
            case 1:
                // guardar datos personales
                empleado.setDNI(inputDni.getText().toString());
                empleado.setNacionality((Nacionality) spNacionality.getSelectedItem());
                empleado.setTelf(inputTelf.getText().toString());
                empleado.setName(inputName.getText().toString());
                empleado.setLast_name(inputLastName.getText().toString());
                String [] datosFech = txtBirthDateShow.getText().toString().split("/");
                Date birthDate = new Date(
                        Integer.parseInt(datosFech[2]),
                        Integer.parseInt(datosFech[1]),
                        Integer.parseInt(datosFech[0])
                );
                empleado.setBirthDate(birthDate);
                empleado.setGender(
                        rdMale.isChecked()?"masculino":"femenino"
                );
                break;
            case 2:
                // guardar datos del trabajo
                DataWork dataWork=new DataWork();
                String [] datosFechAdm = txtBirthDateShow.getText().toString().split("/");
                Date adm = new Date(
                        Integer.parseInt(datosFechAdm[2]),
                        Integer.parseInt(datosFechAdm[1]),
                        Integer.parseInt(datosFechAdm[0])
                );
                dataWork.setDate_of_admission(adm);
                dataWork.setTipo_de_pago(spTipoPago.getSelectedItem().toString());
                dataWork.setDepartment((Department) spDepartment.getSelectedItem());
                dataWork.setCargo((Cargo) spCargo.getSelectedItem());
                dataWork.setSalary(Double.parseDouble(inputSalary.getText().toString()));
                empleado.setDataWork(dataWork);
                break;
            case 3:
                // guardar datos académicos
                empleado.setLevel_academic(spLvlAcademic.getSelectedItem().toString());
                empleado.setTitle_academic(inputTitleAcademic.getText().toString());
                System.out.println(empleado);
                break;
        }
    }
}
