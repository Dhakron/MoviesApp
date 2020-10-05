package net.azarquiel.exmovie2020_alex

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.findNavController

import kotlinx.android.synthetic.main.activity_main.*
import net.azarquiel.exmovie2020_alex.Views.LoginFragmentDialog
import net.azarquiel.exmovie2020_alex.Views.RegisterFragmentDialog

class MainActivity : AppCompatActivity() {
    private lateinit var userLoged: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            userLoged = getSharedPreferences("user", Context.MODE_PRIVATE)
            var user = userLoged.getString("nick","")
            if(user!=""){
                Toast.makeText(this,"El usuario ${user} esta conectado",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"No hay ningun usuario conectado",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.logIn -> LoginFragmentDialog().show(supportFragmentManager, "Login")
            R.id.register -> RegisterFragmentDialog().show(supportFragmentManager, "Login")

        }
        return true
    }
}
