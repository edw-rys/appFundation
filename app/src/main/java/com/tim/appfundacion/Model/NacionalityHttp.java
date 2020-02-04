package com.tim.appfundacion.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tim.appfundacion.Entities.Nacionality;
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

public class NacionalityHttp{
    private ArrayList<Nacionality> nacionalidades = new ArrayList<>();
    Form form =null;

    public NacionalityHttp(Form form) {
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
                    Nacionality nacionality=null;
                    nacionality = gson.fromJson(strNac,Nacionality.class);
                    nacionalidades.add(nacionality);
                }
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (Nacionality nac:nacionalidades){
                            form.addNationality(nac);
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Nacionality> getNacionalidades(){
        try {
            OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(UrlHttp.URL_NACIONALITY)
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
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }

        return nacionalidades;
    }
}
