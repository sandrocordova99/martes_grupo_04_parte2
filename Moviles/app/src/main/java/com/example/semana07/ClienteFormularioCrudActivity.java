package com.example.semana07;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.semana07.entity.Categoria;
import com.example.semana07.entity.Cliente;
import com.example.semana07.entity.Pais;
import com.example.semana07.service.ServiceCategoria;
import com.example.semana07.service.ServicePais;
import com.example.semana07.service.ServicioCliente;
import com.example.semana07.util.ConnectionRest;
import com.example.semana07.util.FunctionUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteFormularioCrudActivity extends AppCompatActivity {

    //Servicio
    ServicioCliente servicioCliente;
    EditText txtDni, txtEmail, txtNombreCliente, txtPassword, txtUserName;
    Button btnEnviar, btnRegresar;
    TextView idTitlePage;
    String metodo;
    Cliente objActual;
    RadioButton rbtActivo, rbtInactivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cliente_formulario_crud);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        txtDni = findViewById(R.id.txtDni );
        txtEmail = findViewById(R.id.txtEmail);
        txtNombreCliente = findViewById(R.id.txtNombreCliente);

        metodo = (String)getIntent().getExtras().get("var_metodo");

        btnEnviar = findViewById(R.id.btnRegLibEnviar);
        btnRegresar = findViewById(R.id.btnRegLibRegresar);

        if (metodo.equals("REGISTRAR")){
            idTitlePage.setText("Registra Cliente");
            btnEnviar.setText("Registrar");
        }else if (metodo.equals("ACTUALIZAR")){
            idTitlePage.setText("Actualiza Cliente");
            btnEnviar.setText("Actualizar");

            objActual = (Cliente) getIntent().getExtras().get("var_objeto");
            txtDni.setText(objActual.getDni());
            txtEmail.setText(objActual.getEmail());
            txtNombreCliente.setText(objActual.getNombre_cliente());

        }



        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClienteFormularioCrudActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = txtDni.getText().toString();
                String email = txtEmail.getText().toString();
                String nombrecliente = txtNombreCliente.getText().toString();


                Cliente objCliente = new Cliente();
                objCliente.setDni(dni);
                objCliente.setEmail(email);
                objCliente.setNombre_cliente(nombrecliente);
                if (metodo.equals("REGISTRAR")){
                    registra(objCliente);
                }else{
                    actualiza(objCliente);
                }

            }
        });

    }


    void registra(Cliente obj){
        Call<Cliente> call = servicioCliente.registraCliente(obj);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()){
                    Cliente objSalida = response.body();
                    mensajeAlert(" Registro de Cliente exitoso:  "
                            + " \n >>>> DNI >> " + objSalida.getDni()
                            + " \n >>> NOMBRE >>> " +  objSalida.getNombre_cliente());
                }
            }
            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {

            }
        });
    }

    void actualiza(Cliente obj){
        Call<Cliente> call = servicioCliente.actualizaCliente(obj);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()){
                    Cliente objSalida = response.body();
                    mensajeAlert(" Actualización de de Cliente exitosa:  "
                            + " \n >>>> DNI >> " + objSalida.getDni()
                            + " \n >>> NOMBRE >>> " +  objSalida.getNombre_cliente());
                }
            }
            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {

            }
        });
    }


    void mensajeToast(String mensaje){
        Toast toast1 =  Toast.makeText(getApplicationContext(),mensaje, Toast.LENGTH_LONG);
        toast1.show();
    }
    public void mensajeAlert(String msg){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(msg);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }


}