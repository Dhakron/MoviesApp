package net.azarquiel.exmovie2020_alex.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.exmovie2020_alex.Api.MainRepository
import net.azarquiel.exmovie2020_alex.Model.Comentario
import net.azarquiel.exmovie2020_alex.Model.Comentarios
import net.azarquiel.exmovie2020_alex.Model.Movies
import org.w3c.dom.Comment

class CommentsViewModel {
    private var repository: MainRepository = MainRepository()
    fun getComents(idMovie:Int): LiveData<List<Comentarios>> {
        val data = MutableLiveData<List<Comentarios>>()
        GlobalScope.launch(Dispatchers.Main) {
            data.value = repository.getComentarios(idMovie)
        }
        return data
    }
    fun saveComents(idMovie:Int,nick:Int,date:String,comentario:String): LiveData<Comentarios> {
        val data = MutableLiveData<Comentarios>()
        GlobalScope.launch(Dispatchers.Main) {
            data.value = repository.saveComentario(idMovie,nick,date,comentario)
        }
        return data
    }
}