package net.azarquiel.exmovie2020_alex.Adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_row.view.*
import net.azarquiel.exmovie2020_alex.Model.Movies

class MoviesAdapter(val context: Context,
                  val layout: Int,
                  val listener: MovieAdapterInterface
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var dataList: List<Movies> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item,listener)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setData(data: List<Movies>) {
        this.dataList = data
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Movies, listener: MovieAdapterInterface){
            Picasso.get().load(Uri.parse("https://image.tmdb.org/t/p/w500"+dataItem.poster_path)).into(itemView.ivImg)
            itemView.tvTitle.text = dataItem.title
            itemView.tvDate.text= dataItem.release_date
            itemView.setOnClickListener{
                listener.click(dataItem)
            }
        }
    }
    interface MovieAdapterInterface {
        fun click(movie: Movies):Boolean{
            return true
        }
    }
}