package com.example.semana07.service;

import com.example.semana07.entity.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServicioCliente {

    @GET("/Cliente")
    public Call<Cliente> listaTodos();

    @POST("/crear")
    public Call<Cliente> registraCliente(@Body Cliente obj);

    @POST("/actualizar")
    public Call<Cliente> actualizaCliente(@Body Cliente obj);

    @GET("/porCliente}")
    public Call<List<Cliente>> listaPorCiente(@Path("Cliente")String cliente);

}
