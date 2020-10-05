package net.azarquiel.exmovie2020_alex.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.exmovie2020_alex.Api.MainRepository
import net.azarquiel.exmovie2020_alex.Model.Movies

class MoviesViewModel {
    private var repository: MainRepository = MainRepository()
    fun getMovies(): LiveData<List<Movies>> {
        val data = MutableLiveData<List<Movies>>()
        GlobalScope.launch(Dispatchers.Main) {
            data.value = repository.getMovies()
        }
        return data
    }
}