package com.tim.appfundacion.Model;



import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tim.appfundacion.Entities.Cargo;
import com.tim.appfundacion.Entities.DataWork;
import com.tim.appfundacion.Entities.Department;
import com.tim.appfundacion.Entities.Employee;
import com.tim.appfundacion.Entities.Nacionality;
import com.tim.appfundacion.Form;
import com.tim.appfundacion.QueryEmployee;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class EmployeeHttpModel{
    private ArrayList<Employee> empleados = new ArrayList<>();
    private QueryEmployee queryEmployee;
    private Form formEmployee;
    public EmployeeHttpModel(QueryEmployee queryEmployee) {
        this.queryEmployee = queryEmployee;
    }
    public EmployeeHttpModel(Form formEmployee) {
        this.formEmployee = formEmployee;
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

        // obteniendo datos
        queryEmployee.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                queryEmployee.startProgressSave();
            }
        });
        // peticiones http
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(UrlHttp.URL_EMPLOYEE)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                queryEmployee.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // detener barra de carga
                        queryEmployee.stopProgressDialog();
                        // mostrar mensaje de error
                        queryEmployee.showProgressError("Error de conexión","");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // detener barra de carga
                queryEmployee.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        queryEmployee.stopProgressDialog();
                    }
                });
                generateData(response);
            }
        });
        return empleados;
    }
    private JSONObject prepareJsonData(){
        JSONObject json =new JSONObject();
        try {
            json.put("json","value");
            json.put("json2","value2");
        }catch (JSONException ex) {

        }
        return json;
    }
    public boolean saveEmployee(Employee employee){
        String status="error";
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody formBody = prepareData(employee);

        Request request = new Request.Builder()
                .url(UrlHttp.URL_EMPLOYEE+"save")
                .post(formBody)
                //.addHeader("Authorization", "header value") //Notice this request has header if you don't need to send a header just erase this part
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    String respuesta=response.body().string();
                    System.out.println(respuesta);

                    JsonObject dataAux =new JsonParser().parse(respuesta).getAsJsonObject();
                    String status = dataAux.get("status").getAsString();
                    showDialogMessage(status.equals("success"), dataAux.get("message").getAsString() );
                }catch (Exception ex){
                    System.out.println(ex);
                }

            }
        });

        return status.equals("success")?true:false;
    }

    private RequestBody prepareData(Employee employee){
        if (employee==null)
            return new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("json","null")
                    .build();
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
        String dateAdmision = format.format(employee.getDataWork().getDate_of_admission());
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name",employee.getName())
                .addFormDataPart("last_name",employee.getLast_name())
                .addFormDataPart("dni",employee.getDNI())
                .addFormDataPart("telf",employee.getTelf())
                .addFormDataPart("birthDate",format.format(employee.getBirthDate()))
                .addFormDataPart("gender",employee.getGender())
                .addFormDataPart("id_nacionality",employee.getNacionality().getId_nacionality()+"")
                .addFormDataPart("tipo_de_pago",employee.getDataWork().getTipo_de_pago())
                .addFormDataPart("salary",employee.getDataWork().getSalary()+"")
                .addFormDataPart("level_academic",employee.getLevel_academic())
                .addFormDataPart("title_academic",employee.getTitle_academic())
                .addFormDataPart("id_cargo",employee.getDataWork().getCargo().getId()+"")
                .addFormDataPart("date_of_admission",dateAdmision)
                .addFormDataPart("id_department",employee.getDataWork().getDepartment().getId()+"")

                .build();
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

    public void showDialogMessage(boolean status, final String message){
        formEmployee.stopProgressDialog();
        System.out.println(status);
        if (status){
            // empleado guardado
            /**/
            formEmployee.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    formEmployee.showProgressSuccess();
                    Thread background = new Thread(new Runnable() {

                        public void run() {

                            try {
                                Thread.sleep(2000);
                                formEmployee.stopProgressDialog();
                                formEmployee.startMainView();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    background.start();
                    }
            });

        }else{
            //error al guardar
            formEmployee.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    formEmployee.showProgressError(message);
                    Thread background = new Thread(new Runnable() {

                        public void run() {

                            try {
                                Thread.sleep(2000);
                                formEmployee.stopProgressDialog();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    background.start();
                }
            });
        }
        System.out.println("fg");
    }
}
