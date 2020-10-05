package net.azarquiel.exmovie2020_alex.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.exmovie2020_alex.Api.MainRepository
import net.azarquiel.exmovie2020_alex.Model.Movies
import net.azarquiel.exmovie2020_alex.Model.User

class LoginRegisterViewModel {
    private var repository: MainRepository = MainRepository()
    fun login(nick:String,pass:String): LiveData<User> {
        val data = MutableLiveData<User>()
        GlobalScope.launch(Dispatchers.Main) {
            data.value = repository.getDataUsuarioPorNickPass(nick,pass)
        }
        return data
    }
    fun register(nick:String,pass:String): LiveData<User> {
        val data = MutableLiveData<User>()
        GlobalScope.launch(Dispatchers.Main) {
            data.value = repository.saveUser(nick,pass)
        }
        return data
    }
}