package com.tim.appfundacion.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tim.appfundacion.Entities.Department;
import com.tim.appfundacion.Http.HttpClient;
import com.tim.appfundacion.Http.OnHttpRequestComplete;
import com.tim.appfundacion.Http.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DepartmentHtppModel implements OnHttpRequestComplete {
    private ArrayList<Department> departamentos = new ArrayList<>();
    @Override
    public void onComplete(Response status) {
        if (status.isSuccess()){
            Gson gson = new GsonBuilder().create();
            JSONObject json = null;
            try {
                json = new JSONObject(status.getResult());
                JSONArray jsonArr = json.getJSONArray("datos");

                for (int i = 0;i < jsonArr.length(); i++){
                    String strNac = jsonArr.getString(i);
                    departamentos.add(gson.fromJson(strNac, Department.class));
                    //System.out.println("Obj: "+jsonArr.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<Department> getDepartamentos(){
        HttpClient client  = new HttpClient(this);
        client.excecute("http://192.168.1.9:81/backend_app_samoyed/department");
        return departamentos;
    }
}
