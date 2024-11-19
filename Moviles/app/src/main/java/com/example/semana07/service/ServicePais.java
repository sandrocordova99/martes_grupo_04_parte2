package com.example.semana07.service;

import com.example.semana07.entity.Pais;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicePais {

    @GET("api/Pais")
    public Call<List<Pais>> listaTodos();


}
