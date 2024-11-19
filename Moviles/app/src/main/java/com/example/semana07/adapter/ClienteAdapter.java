package com.example.semana07.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.semana07.R;
import com.example.semana07.entity.Cliente;
import com.example.semana07.entity.Libro;

import java.util.List;

public class ClienteAdapter {
    private Context context;
    private List<Cliente> lista;

    // Un adaptador es un objeto que comunica a un ListView
    // los datos necesarios para crear las filas de la lista.
    public ClienteAdapter(@NonNull Context context, int resource, @NonNull List<Cliente> lista) {
        super();
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // toma el objeto seleccionado y lo muestra en los textbox
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.activity_cliente_item_crud, parent, false);

        Cliente obj = lista.get(position);

    //    TextView txtId = row.findViewById(R.id.itemIdCliente);
    //    txtId.setText("ID :"+String.valueOf(obj.getId())); //int

        TextView txtTitulo = row.findViewById(R.id.itemDni);
        txtTitulo.setText("Dni :"+obj.getDni()); //string

        TextView txtAnio = row.findViewById(R.id.itemEmail);
        txtAnio.setText("Email:"+obj.getEmail());

        TextView txtSerie = row.findViewById(R.id.itemNombre_Cliente);
        txtSerie.setText("Nombre Cliente :"+obj.getNombre_cliente());

     //   TextView txtCategoria = row.findViewById(R.id.itemPassword);
     //   txtCategoria.setText("Password :"+obj.getPassword());

    //    TextView txtPais = row.findViewById(R.id.itemUserName);
    //    txtPais.setText("País :"+obj.getUser_name());

        return row;
    }

}
