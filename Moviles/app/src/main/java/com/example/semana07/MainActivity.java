package com.example.semana07;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.semana07.adapter.ClienteAdapter;
import com.example.semana07.adapter.LibroAdapter;
import com.example.semana07.entity.Cliente;
import com.example.semana07.entity.Libro;
import com.example.semana07.service.ServiceLibro;
import com.example.semana07.service.ServicioCliente;
import com.example.semana07.util.ConnectionRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivity  extends AppCompatActivity {

    EditText txtDni;
    Button btnConsultar , btnRegistrar;

    ListView lstConsultaCliente;
    ArrayList<Cliente> data = new ArrayList<Cliente>();
    LibroAdapter adaptador2;

    ClienteAdapter adaptador;

    ServiceLibro servicio2;

    ServicioCliente servicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtDni = findViewById(R.id.txtDni);

        lstConsultaCliente = findViewById(R.id.lstConsultaClientes);
        adaptador = new ClienteAdapter(this, R.layout.activity_cliente_item_crud, data);
        lstConsultaCliente.setAdapter((ListAdapter) adaptador);

        servicio = ConnectionRest.getConnection().create(ServicioCliente.class);

        btnConsultar = findViewById(R.id.btnLista);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filtro = txtDni.getText().toString();
                consulta(filtro);
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //mensajeAlert("Registrar");
               Intent intent = new Intent(MainActivity.this, ClienteFormularioCrudActivity.class);
               intent.putExtra("var_metodo", "REGISTRAR");
               startActivity(intent);
            }
        });

        lstConsultaCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //mensajeAlert("Actualiza >> " + position);

                Cliente obj =  data.get(position);

                Intent intent = new Intent(MainActivity.this, ClienteFormularioCrudActivity.class);
                intent.putExtra("var_metodo", "ACTUALIZAR");
                intent.putExtra("var_objeto", String.valueOf(obj));
                startActivity(intent);
            }
        });

    }


    public  void consulta(String filtro){

        Call<List<Cliente>> call = servicio.listaPorCiente(filtro);
        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {

                if(response.isSuccessful()){
                    List<Cliente> lstSalida = response.body();
                    data.clear();
                    data.addAll(lstSalida);
                    adaptador.notify();
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {

                mensajeAlert("ERROR -> Error en la respuesta" + t.getMessage());
            }
        });
    }

    public  void mensajeAlert(String msg){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(msg);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }
}