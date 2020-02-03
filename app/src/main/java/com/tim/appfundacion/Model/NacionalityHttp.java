package com.tim.appfundacion.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tim.appfundacion.Entities.Nacionality;
import com.tim.appfundacion.Http.HttpClient;
import com.tim.appfundacion.Http.OnHttpRequestComplete;
import com.tim.appfundacion.Http.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NacionalityHttp implements OnHttpRequestComplete {
    private ArrayList<Nacionality> nacionalidades = new ArrayList<>();
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
                    nacionalidades.add(gson.fromJson(strNac,Nacionality.class));
                    //System.out.println("Obj: "+jsonArr.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<Nacionality> getNacionalidades(){
        HttpClient client  = new HttpClient(this);
        client.excecute("http://192.168.1.9:81/backend_app_samoyed/nacionality");
        return nacionalidades;
    }
}
