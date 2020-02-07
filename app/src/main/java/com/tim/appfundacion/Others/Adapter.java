package com.tim.appfundacion.Others;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tim.appfundacion.Detalle_empleado;
import com.tim.appfundacion.Entities.Employee;
import com.tim.appfundacion.R;

import java.util.Date;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Employee> data;



    public Adapter(Context context, List<Employee> data){
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.card_view_employee,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        // bind the textview with data received
        Employee emp = data.get(i);
        String title =emp.getName()+" "+emp.getLast_name();
        viewHolder.textName.setText(title);
        viewHolder.picture.setImageResource(
                data.get(i).getGender().equals("femenino")?
                R.drawable.samoyed_female:
                        R.drawable.samoyed_fm
        );
        viewHolder.textDNI.setText("CI.: "+emp.getDNI());
        viewHolder.textCargo.setText(emp.getDataWork().getCargo().getName());
        // similarly you can set new image for each card and descriptions



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textName,textDNI,textCargo;
        ImageView picture;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), Detalle_empleado.class);
                    Employee employee =  data.get(getAdapterPosition());
                    i.putExtra("names",employee.getLast_name() + " "+ employee.getName());
                    i.putExtra("dni",employee.getDNI());
                    i.putExtra("nacionality", employee.getNacionality().getName_nacionality());
                    i.putExtra("cargo",employee.getDataWork().getCargo().getName());
                    i.putExtra("department",employee.getDataWork().getDepartment().getName());
                    Date date= employee.getBirthDate();
                    String strDate = date.getDate()+"/"+(date.getMonth()+1)+"/"+(date.getYear()+1900);
                    i.putExtra("birthDate",strDate);
                    i.putExtra("tipoPago",employee.getDataWork().getTipo_de_pago());
                    i.putExtra("salary",employee.getDataWork().getSalary()+"");
                    i.putExtra("titleAcademic",employee.getTitle_academic());
                    i.putExtra("levelAcademic",employee.getLevel_academic());
                    i.putExtra("gender",employee.getGender());
                    date= employee.getDataWork().getDate_of_admission();
                    strDate = date.getDate()+"/"+(date.getMonth()+1)+"/"+(date.getYear()+1900);
                    i.putExtra("dateAdm",strDate);

                    v.getContext().startActivity(i);
                }
            });
            textName = itemView.findViewById(R.id.textName);
            picture =itemView.findViewById(R.id.imageCard);
            textCargo = itemView.findViewById(R.id.textCargo);
            textDNI = itemView.findViewById(R.id.textCi);
        }
    }
}
