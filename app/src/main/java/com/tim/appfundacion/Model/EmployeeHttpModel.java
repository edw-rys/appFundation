package com.tim.appfundacion.Model;



import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tim.appfundacion.Entities.Cargo;
import com.tim.appfundacion.Entities.DataWork;
import com.tim.appfundacion.Entities.Department;
import com.tim.appfundacion.Entities.Employee;
import com.tim.appfundacion.Entities.Nacionality;
import com.tim.appfundacion.QueryEmployee;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class EmployeeHttpModel{
    private ArrayList<Employee> empleados = new ArrayList<>();
    private QueryEmployee queryEmployee;

    public EmployeeHttpModel(QueryEmployee queryEmployee) {
        this.queryEmployee = queryEmployee;
    }

    public void generateData(Response response) {
        if (response.isSuccessful()){
                query(response);
            queryEmployee.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    queryEmployee.cargarDatos(empleados);
                }
            });
        }
    }
    public ArrayList<Employee> getEmployees(){

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(UrlHttp.URL_EMPLOYEE)
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
        return empleados;
    }
    public boolean saveEmployee(Employee employee){
        String status="error";
        try {
            // objetos tipo http
            HttpClient client  = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(UrlHttp.URL_EMPLOYEE+"save");

            //entidades
            httpPost.setEntity(new UrlEncodedFormEntity(prepareData(employee)));
            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String respuestaTxt = EntityUtils.toString(entity);

            JsonObject dataAux = (JsonObject) new JsonParser().parse(respuestaTxt);
            status = dataAux.get("status").getAsString();
            System.out.println("Response: "+status);
        }catch (Exception ex){
            System.out.println(ex);
        }

        return status.equals("success")?true:false;
    }

    private List<NameValuePair> prepareData(Employee employee){
        List<NameValuePair> listData =new ArrayList<>();
        if (employee==null)
            return listData;
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
        listData.add(new BasicNameValuePair("name",employee.getName()));
        listData.add(new BasicNameValuePair("last_name",employee.getLast_name()));
        listData.add(new BasicNameValuePair("dni",employee.getDNI()));
        listData.add(new BasicNameValuePair("telf",employee.getTelf()));
        listData.add(new BasicNameValuePair("birthDate",format.format(employee.getBirthDate())));
        listData.add(new BasicNameValuePair("gender",employee.getGender()));
        listData.add(new BasicNameValuePair("id_nacionality",employee.getNacionality().getId_nacionality()+""));
        listData.add(new BasicNameValuePair("tipo_de_pago",employee.getDataWork().getTipo_de_pago()));
        listData.add(new BasicNameValuePair("salary",employee.getDataWork().getSalary()+""));
        listData.add(new BasicNameValuePair("level_academic",employee.getLevel_academic()));
        listData.add(new BasicNameValuePair("title_academic",employee.getTitle_academic()));
        listData.add(new BasicNameValuePair("id_cargo",employee.getDataWork().getCargo().getId()+""));
        String dateAdmision = format.format(employee.getDataWork().getDate_of_admission());
        listData.add(new BasicNameValuePair("date_of_admission",dateAdmision));
        listData.add(new BasicNameValuePair("id_department",employee.getDataWork().getDepartment().getId()+""));
        return listData;
    }

    public void query(Response status){
        try {
            JsonParser parser = new JsonParser();
            //JSONArray jsonArr = json.getJSONArray("datos");
            //System.out.println(status.getResult());
            //System.out.println(jsonArr.toString());
            JsonArray gsonArr = parser.parse(status.body().string()).getAsJsonArray();

            for (JsonElement obj : gsonArr) {
                Employee employee= new Employee();
                //System.out.println("Obj: "+ obj);
                JsonObject gsonObj = obj.getAsJsonObject();
                // Primitives elements of object
                // fecha
                employee.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(gsonObj.get("birthDate").getAsString()));
                employee.setDNI(gsonObj.get("dni").getAsString());
                employee.setId_employee(gsonObj.get("id_employee").getAsInt());
                employee.setLast_name(gsonObj.get("last_name").getAsString());
                employee.setName(gsonObj.get("name").getAsString());
                employee.setTelf(gsonObj.get("telf").getAsString());
                employee.setGender(gsonObj.get("gender").getAsString());
                employee.setLevel_academic(gsonObj.get("level_academic").getAsString());
                employee.setTitle_academic(gsonObj.get("title_academic").getAsString());

                // DATA WORK
                DataWork datosTrabajo = new DataWork();
                JsonObject dataAux = gsonObj.get("cargo").getAsJsonObject();
                Cargo cargo = new Cargo(
                        dataAux.get("id_cargo").getAsInt(),
                        dataAux.get("nombre_cargo").getAsString());
                datosTrabajo.setCargo(cargo);

                // setear datos del departamento
                dataAux = gsonObj.get("department").getAsJsonObject();
                Department department = new Department(
                        dataAux.get("id_department").getAsInt(),
                        dataAux.get("name_department").getAsString()
                );
                datosTrabajo.setSalary(gsonObj.get("salary").getAsDouble());
                datosTrabajo.setTipo_de_pago(gsonObj.get("tipo_de_pago").getAsString());
                datosTrabajo.setDate_of_admission(new SimpleDateFormat("yyyy-MM-dd").parse(gsonObj.get("date_of_admission").getAsString()));
                datosTrabajo.setDepartment(department);
                employee.setDataWork(datosTrabajo);

                // setear datos del departamento
                dataAux = gsonObj.get("nacionality").getAsJsonObject();
                Nacionality nacionality = new Nacionality(
                        dataAux.get("id_nacionality").getAsInt(),
                        dataAux.get("name_nacionality").getAsString()
                );
                employee.setNacionality(nacionality);
                System.out.println(employee.toString());
                empleados.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(Response status){}
}
