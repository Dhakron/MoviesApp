package net.azarquiel.exmovie2020_alex.Views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies.*
import net.azarquiel.exmovie2020_alex.Adapters.MoviesAdapter
import net.azarquiel.exmovie2020_alex.Model.Movies
import net.azarquiel.exmovie2020_alex.R
import net.azarquiel.exmovie2020_alex.ViewModel.MoviesViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MoviesFragment : Fragment(),MoviesAdapter.MovieAdapterInterface,SearchView.OnQueryTextListener {
    private lateinit var viewModel: MoviesViewModel
    private lateinit var  adapter: MoviesAdapter
    private lateinit var originalList:List<Movies>
    private lateinit var searchView:SearchView
    private var kk = true
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }
    private lateinit var userLoged: SharedPreferences
    override fun onStart() {
        super.onStart()
        initRv()
        viewModel= MoviesViewModel()
        viewModel.getMovies().observe(this, Observer {
            if(kk){
                originalList=it
                kk=false
            }
            adapter.setData(it)
        })
        setHasOptionsMenu(true)
    }
    private fun initRv() {
        adapter= MoviesAdapter(context!!,R.layout.movies_row,this)
        rvMovies.adapter=adapter
        rvMovies.layoutManager= LinearLayoutManager(context)
    }

    override fun click(movies: Movies): Boolean {
        userLoged = context!!.getSharedPreferences("user", Context.MODE_PRIVATE)
        var user = userLoged.getString("nick","")
        if(user!=""){
            var bundle = bundleOf("movies" to movies)
            findNavController().navigate(R.id.action_MoviesFragment_to_detailFragment,bundle)
        }else{
            Toast.makeText(context,"Se necesita un usuario conectado para ver los detalles", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val searchItem = menu.findItem(R.id.search)
        searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Search...")
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }
    // ************* <Filtro> ************
    override fun onQueryTextChange(query: String): Boolean {
        adapter.setData(originalList.filter{ lugar -> lugar.title!=null && lugar.title.toUpperCase().contains(query.toUpperCase())})
        return false
    }

    override fun onResume() {
        super.onResume()
        parentFragment?.activity?.fab?.isVisible=true
    }
    override fun onQueryTextSubmit(text: String): Boolean {
        return false
    }
}
