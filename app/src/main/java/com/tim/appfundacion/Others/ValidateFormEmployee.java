package com.tim.appfundacion.Others;

import android.text.Editable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ValidateFormEmployee {
    public static ArrayList<DataError> dataInfo=new ArrayList<>();
    public static boolean validateDataAcademic(Object lvlAcademic, String title) {
        dataInfo=new ArrayList<>();
        boolean status = true;
        if (lvlAcademic.equals("")){
            dataInfo.add(
                    new DataError(DataError.levelAcademic,"Nivel no seleccionado",true)
            );
            status = false;
        }

        if (title.equals("") || !Validation.isText(title)){
            dataInfo.add(
                    new DataError(DataError.titleAcademic,"Nombre no es válido",true)
            );
            status = false;
        }
        System.out.println(dataInfo.toString());
        return status;
    }

    public static boolean validateDataWork(String dateAdmision, Object department, Object cargo, String salary,Object tiposPago) {
        dataInfo=new ArrayList<>();
        boolean status = true;
        if (!Validation.isDate(dateAdmision)){
            dataInfo.add(
                    new DataError(DataError.dateAdmision,"Fecha no es válida",true)
            );
            status = false;
        }
        if (cargo.equals("")){
            dataInfo.add(
                    new DataError(DataError.cargo,"cargo no seleccionado",true)
            );
            status = false;
        }
        if (department.equals("")){
            dataInfo.add(
                new DataError(DataError.department,"Departamento no seleccionado",true)
            );
            status = false;
        }
        if (!Validation.isDecimal(salary)){
            dataInfo.add(
                new DataError(DataError.salary,"Valor no es válido",true)
            );
            status = false;
        }
        System.out.println(tiposPago);
        if (tiposPago.equals("")){
            dataInfo.add(
                    new DataError(DataError.tipoPago,"Seleccione tipo de pago",true)
            );
            status = false;
        }
        System.out.println(dataInfo.toString());
        return status;
    }
    public static boolean validatePersonalData(Object nacionality, String birthdate, String dni, String lastName, String name, String telf, boolean isMale, boolean isFemale) {
        boolean status = true;
        dataInfo=new ArrayList<>();
        if (nacionality==null || nacionality.equals("")){
            dataInfo.add(
                new DataError(DataError.nacionality,"Nacionalidad no seleccionada",true)
            );
            status = false;
        }
        if (!Validation.isDate(birthdate)){
            dataInfo.add(
                new DataError(DataError.birthdate,"Fecha no es válida",true)
            );
            status = false;
        }else{
            Calendar calendar =Calendar.getInstance();
            String [] datosFechAdm = birthdate.split("/");
            Date date = new Date();

            if((date.getYear()+1900)-Integer.parseInt(datosFechAdm[2])<18){
                dataInfo.add(
                        new DataError(DataError.birthdate,"No se permite registrar a un menor de edad.",true)
                );
                status = false;
            }
        }
        if (!Validation.isDNIEC(dni)){
            dataInfo.add(
                new DataError(DataError.dni,"Cédula no es válida",true)
            );
            status = false;
        }
        if (!Validation.validateNames(lastName)){
            dataInfo.add(
                new DataError(DataError.lastName,"Apellidos no permitidos",true)
            );
            status = false;
        }
        if (!Validation.validateNames(name)){
            dataInfo.add(
                new DataError(DataError.name,"Nombres no permitidos",true)
            );
            status = false;
        }
        if(!Validation.isPhoneNumber(telf)){
            dataInfo.add(
                new DataError(DataError.telf,"Número de teléfono no permitido",true)
            );
            status = false;
        }
        if (!(isMale || isFemale)){
            dataInfo.add(
                new DataError(DataError.gender,"Seleccione su género",true)
            );
            status = false;
        }
        System.out.println(dataInfo.toString());
        return status;
    }
}
