package net.azarquiel.exmovie2020_alex.Adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comentario_row.view.*
import kotlinx.android.synthetic.main.movies_row.view.*
import net.azarquiel.exmovie2020_alex.Model.Comentario
import net.azarquiel.exmovie2020_alex.Model.Comentarios
import net.azarquiel.exmovie2020_alex.Model.Movies

class ComentariosAdapter(val context: Context,
                         val layout: Int
) : RecyclerView.Adapter<ComentariosAdapter.ViewHolder>() {

    private var dataList: List<Comentarios> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setData(data: List<Comentarios>) {
        this.dataList = data
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Comentarios){
            Picasso.get().load(Uri.parse("https://image.tmdb.org/t/p/w500"+dataItem.img)).into(itemView.ivComentario)
            itemView.tvComentario.text=dataItem.comentario
            itemView.tvDateCOmment.text=dataItem.fecha
            itemView.tvNickUser.text=dataItem.nick
        }
    }

}