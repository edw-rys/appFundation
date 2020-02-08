package com.tim.appfundacion.Others;

import androidx.annotation.NonNull;

public class DataError {
    public String campo;
    public String message="";
    public boolean haveError=false;

    public DataError(String campo, String message, boolean haveError) {
        this.campo = campo;
        this.message = message;
        this.haveError = haveError;
    }

    @NonNull
    @Override
    public String toString() {
        return "[campo: "+campo+"]";
    }

    public
    static
    String levelAcademic    = "level_academic",
        titleAcademic       = "title_academic",
        //
        dateAdmision        = "date_admision",
        cargo               = "cargo",
        department          = "department",
        salary              = "salary",
        tipoPago            = "tipo_pago",
        //
        nacionality         = "nacionality",
        birthdate           ="birthdate",
        dni                 = "dni",
        lastName            = "last_name",
        name                = "name",
        telf                = "telf",
        gender              = "gender"
    ;
}
