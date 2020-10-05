package net.azarquiel.exmovie2020_alex.Model

import java.io.Serializable

data class Movies(
    var id:Int,
    var title:String,
    var release_date:String,
    var vote_count:Int,
    var overview:String,
    var poster_path:String
): Serializable

data class User(
    var id: Int?=-1,
    var nick:String?="",
    var pass:String?=""
): Serializable
data class Comentarios(
    var movie: Int?=null,
    var nick:String?=null,
    var fecha:String?=null,
    var comentario:String?=null,
    var img:String?=null,
    var usuario: Int?=null
): Serializable
data class Comentario(
    var movie: Int?=null,
    var usuario: Int?=null,
    var nick:String?=null,
    var fecha:String?=null,
    var comentario:String?=null,
    var img:String?=null
): Serializable

data class Respuesta(
    var results : List<Movies>,
    var usuario: User,
    var comentarios:List<Comentarios>,
    var comentario: Comentarios
): Serializable

