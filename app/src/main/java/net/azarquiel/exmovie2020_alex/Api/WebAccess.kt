package net.azarquiel.exmovie2020_alex.Api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebAccess {
    val movieService: MovieService by lazy {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("http://www.ies-azarquiel.es/paco/apimovie/")
            .build()
        return@lazy retrofit.create(MovieService::class.java)
    }

}