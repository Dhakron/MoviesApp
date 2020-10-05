package net.azarquiel.exmovie2020_alex.Views

import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.movies_row.view.*
import net.azarquiel.exmovie2020_alex.Model.Movies

import net.azarquiel.exmovie2020_alex.R


class DetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onStart() {
        super.onStart()
        val movie= arguments?.get("movies") as Movies
        parentFragment?.activity?.fab?.isVisible=false
        Picasso.get().load(Uri.parse("https://image.tmdb.org/t/p/w500"+movie.poster_path)).into(ivPortada)
        tvDateD.text=movie.release_date
        tvDesc.text=movie.overview
        tvTitleD.text=movie.title
        tvVotes.text=movie.vote_count.toString()
        ftComments.setOnClickListener{
            Log.e("////////////",movie.id.toString())
            val bundle= bundleOf("id" to movie.id,"img" to movie.poster_path)
            findNavController().navigate(R.id.action_detailFragment_to_commentsFragment,bundle)
        }
    }


}
