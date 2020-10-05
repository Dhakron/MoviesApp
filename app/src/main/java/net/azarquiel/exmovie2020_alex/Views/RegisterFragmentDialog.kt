package net.azarquiel.exmovie2020_alex.Views

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_login_dialog.*
import kotlinx.android.synthetic.main.fragment_register_dialog.*

import net.azarquiel.exmovie2020_alex.R
import net.azarquiel.exmovie2020_alex.ViewModel.LoginRegisterViewModel

class RegisterFragmentDialog : DialogFragment() {

    private var viewModel=LoginRegisterViewModel()
    private lateinit var userLoged: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        var nick=view?.findViewById(R.id.etNickR) as EditText
        var pass=view?.findViewById(R.id.etPassR) as EditText
        btnRegister.setOnClickListener{
            viewModel.register(nick.text.toString(), pass.text.toString())
                .observe(this, Observer {
                    if(nick.text.toString()!=""&&pass.text.toString()!=""){
                    if(it==null){
                        Toast.makeText(context,"El usuario o la contraseña son incorrectos",Toast.LENGTH_SHORT).show()
                        etPass.setText("")
                    }else if(it.nick!=""){
                        Toast.makeText(context,"El usuario ${it.nick} se ha registrado y conectado",Toast.LENGTH_SHORT).show()
                        userLoged= context!!.getSharedPreferences("user", Context.MODE_PRIVATE)
                        val edit = userLoged.edit()
                        edit.putString("nick","${it.nick}")
                        edit.putInt("id",it.id!!)
                        edit.apply()
                        getDialog()?.cancel()
                    }}else{
                        Toast.makeText(context,"Inserte el usuario y la contraseña",Toast.LENGTH_SHORT).show()
                    }

                })
        }
        btnCancelR.setOnClickListener{
            dialog?.cancel()
        }
    }
}
