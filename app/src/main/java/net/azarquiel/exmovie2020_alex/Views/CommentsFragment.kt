package net.azarquiel.exmovie2020_alex.Views

import android.content.Context
import android.content.SharedPreferences
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_comments.*
import net.azarquiel.exmovie2020_alex.Adapters.ComentariosAdapter
import net.azarquiel.exmovie2020_alex.Model.Comentario
import net.azarquiel.exmovie2020_alex.Model.Comentarios
import net.azarquiel.exmovie2020_alex.R
import net.azarquiel.exmovie2020_alex.ViewModel.CommentsViewModel
import java.util.*


class CommentsFragment : Fragment() {
    private lateinit var viewModel: CommentsViewModel
    private lateinit var adapter: ComentariosAdapter
    private lateinit var list: MutableList<Comentarios>
    private lateinit var userLoged: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_comments, container, false)
    }

    override fun onStart() {
        super.onStart()
        initRv()
        viewModel = CommentsViewModel()
        val id= arguments?.get("id") as Int
        val getdata= viewModel.getComents(id).observe(this, Observer {
            list = emptyList<Comentarios>().toMutableList()
            list = it.toMutableList()
            val img = arguments?.get("img") as String
            list.forEach {
                it.img = img
            }
            adapter.setData(list)
        })
        btnSend.setOnClickListener{
            if(edSend.text.toString()!="") {
                userLoged = context!!.getSharedPreferences("user", Context.MODE_PRIVATE)
                var userId = userLoged.getInt("id", 0)
                val pattern = "yyyy-MM-dd hh:mm:ss"
                val simpleDateFormat = SimpleDateFormat(pattern)
                val date: String = simpleDateFormat.format(Date())
                Log.e("///////", "${arguments?.get("id") as Int},${userId},${date},${edSend.text}")
                val comment= viewModel.saveComents(
                    arguments?.get("id") as Int,
                    userId!!,
                    date,
                    edSend.text.toString()
                ).observe(this, Observer {
                    it?.nick=userLoged.getString("nick","")
                    val img = arguments?.get("img") as String
                    list.add(it)
                    list.forEach {
                        it.img = img
                    }
                    adapter.setData(list)
                })
                edSend.setText("")
                Toast.makeText(context,"Has publicado el mensaje",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"Debes insertar un comentario",Toast.LENGTH_SHORT).show()
            }
        }
        setHasOptionsMenu(true)
    }

    private fun initRv() {
        adapter = ComentariosAdapter(context!!, R.layout.comentario_row)
        rvComentarios.adapter = adapter
        rvComentarios.layoutManager = LinearLayoutManager(context)
    }
}