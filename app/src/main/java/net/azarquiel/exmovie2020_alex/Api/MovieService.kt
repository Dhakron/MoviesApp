package net.azarquiel.exmovie2020_alex.Api

import kotlinx.coroutines.Deferred
import net.azarquiel.exmovie2020_alex.Model.Respuesta
import retrofit2.Response
import retrofit2.http.*

interface MovieService {
    @GET("movie")
    fun getMovies(): Deferred<Response<Respuesta>>
    @GET("usuario")
    fun getDataUsuarioPorNickPass(
        @Query("nick") nick: String,
        @Query("pass") pass: String): Deferred<Response<Respuesta>>
    @FormUrlEncoded
    @POST("usuario")
    fun saveUser(@Field("nick") nick: String,
                 @Field("pass") pass: String): Deferred<Response<Respuesta>>
    @GET("movie/{idmovie}/comentario")
    fun getComentarios(@Path("idmovie") idmovie:Int): Deferred<Response<Respuesta>>
    @FormUrlEncoded
    @POST("movie/{idmovie}/comentario")
    fun saveComentario(@Path("idmovie") idmovie: Int,
                   @Field("movie") id: Int,
                   @Field("usuario") usuario: Int,
                   @Field("fecha") fecha: String,
                   @Field("comentario") comentario: String
    ): Deferred<Response<Respuesta>>
}