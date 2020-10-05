package net.azarquiel.exmovie2020_alex.Api

import net.azarquiel.exmovie2020_alex.Model.Comentario
import net.azarquiel.exmovie2020_alex.Model.Comentarios
import net.azarquiel.exmovie2020_alex.Model.Movies
import net.azarquiel.exmovie2020_alex.Model.User

class MainRepository {
    val service = WebAccess.movieService
    suspend fun getMovies(): List<Movies> {
        val webResponse = service.getMovies().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.results
        }
        return emptyList()
    }
    suspend fun getDataUsuarioPorNickPass(nick:String,pass:String): User {
        val webResponse = service.getDataUsuarioPorNickPass(nick,pass).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return User()
    }
    suspend fun saveUser(nick: String,pass: String): User {
        val webResponse = service.saveUser(nick,pass).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return User()
    }
    suspend fun getComentarios(id:Int): List<Comentarios> {
        val webResponse = service.getComentarios(id).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.comentarios
        }
        return emptyList()
    }
    suspend fun saveComentario(idMovie:Int,nick:Int,date:String,comentario:String) : Comentarios {
        val webResponse = service.saveComentario(idMovie,idMovie,nick,date,comentario).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.comentario
        }
        return Comentarios()
    }
}