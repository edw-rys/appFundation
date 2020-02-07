package com.tim.appfundacion.Others;

import android.text.Editable;

import java.util.ArrayList;

public class ValidateFormEmployee {
    public static ArrayList<String> dataInfo=new ArrayList<>();
    public static boolean validateDataAcademic(Object lvlAcademic, String title) {
        dataInfo=new ArrayList<>();
        boolean status = true;
        if (lvlAcademic.equals("")){
            dataInfo.add("Nivel no seleccionado");
            status = false;
        }
        if (title.equals("") || !Validation.isText(title)){
            dataInfo.add("Digite el nombre del título");
            status = false;
        }
        System.out.println(dataInfo.toString());
        return status;
    }

    public static boolean validateDataWork(String dateAdmision, Object department, Object cargo, String salary) {
        dataInfo=new ArrayList<>();
        boolean status = true;
        if (dateAdmision.equals("dd/mm/yyy")){
            dataInfo.add("Error de fecha");
            status = false;
        }
        if (cargo.equals("")){
            dataInfo.add("cargo no seleccionado");
            status = false;
        }
        if (department.equals("")){
            dataInfo.add("Departamento no seleccionado");
            status = false;
        }
        if (!Validation.isDecimal(salary)){
            dataInfo.add("Valor de salario mal ingresado");
            status = false;
        }
        System.out.println(dataInfo.toString());
        return status;
    }
    public static boolean validatePersonalData(Object nacionality, String birthdate, String dni, String lastName, String name, String telf, boolean isMale, boolean isFemale) {
        boolean status = true;
        dataInfo=new ArrayList<>();
        if (nacionality==null || nacionality.equals("")){
            dataInfo.add("Nacionalidad no seleccionada");
            status = false;
        }
        if (birthdate.equals("dd/mm/yyy")){
            dataInfo.add("Error de fecha");
            status = false;
        }
        if (!Validation.isDNIEC(dni)){
            dataInfo.add("Ci no permitida");
            status = false;
        }
        if (!Validation.validateNames(lastName)){
            dataInfo.add("Apellidos no permitidos");
            status = false;
        }
        if (!Validation.validateNames(name)){
            dataInfo.add("Nombres no permitidos");
            status = false;
        }
        if(!Validation.isPhoneNumber(telf)){
            dataInfo.add("Número de teléfono no permitido");
            status = false;
        }
        if (!(isMale || isFemale)){
            dataInfo.add("Seleccione su género");
            status = false;
        }
        System.out.println(dataInfo.toString());
        return status;
    }
}
