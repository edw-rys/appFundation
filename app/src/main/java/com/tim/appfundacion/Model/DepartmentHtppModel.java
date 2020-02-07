package com.tim.appfundacion.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tim.appfundacion.Entities.Cargo;
import com.tim.appfundacion.Entities.Department;
import com.tim.appfundacion.Form;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DepartmentHtppModel {
    private ArrayList<Department> departamentos = new ArrayList<>();
    private Form form =null;

    public DepartmentHtppModel(Form form) {
        this.form = form;
    }

    private void generateData(Response response) throws IOException {
        if (response.isSuccessful()){
            Gson gson = new GsonBuilder().create();
            JSONObject json = null;
            try {
                json = new JSONObject(response.body().string());
                JSONArray jsonArr = json.getJSONArray("datos");
                for (int i = 0;i < jsonArr.length(); i++){
                    String strNac = jsonArr.getString(i);
                    Department department = gson.fromJson(strNac,Department.class);
                    departamentos.add(department);

                    //System.out.println("Obj: "+jsonArr.getString(i));
                }
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        form.addDepartment(departamentos);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    public ArrayList<Department> getDepartamentos(){
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(UrlHttp.URL_DEPARTMENT)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Callback");
                generateData(response);
            }
        });
        return departamentos;
    }
}
