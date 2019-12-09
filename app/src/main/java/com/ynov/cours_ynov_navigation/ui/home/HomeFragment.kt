package com.ynov.cours_ynov_navigation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ynov.cours_ynov.models.ApiResponse
import com.ynov.cours_ynov.models.Product
import com.ynov.cours_ynov_navigation.R
import com.ynov.cours_ynov_navigation.network.ApiError
import kotlinx.android.synthetic.main.fragment_home.*
import com.ynov.cours_ynov_navigation.network.ApiHelpers
import com.ynov.cours_ynov_navigation.network.ApiRequestCallback

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    var productList : List<Product> = listOf()
    var adapter: HomeAdapter = HomeAdapter()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })


        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiHelper = ApiHelpers(context)

        recyclerView = productRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        apiHelper.getProducts(
            object : ApiRequestCallback<ApiResponse<List<Product>>>() {
                override fun onSuccess(result: ApiResponse<List<Product>>?) {
                    super.onSuccess(result)

                    activity?.runOnUiThread(
                        object : Runnable {
                            override fun run() {
                                productList = result!!.data
                                adapter.updateList(productList)
                                val number = adapter.itemCount
                                number.toString()
                            }
                        }
                    )

                }

                override fun onError(error: ApiError?) {
                    super.onError(error)
                }
            }
        )

        galleryButton.setOnClickListener {
            if(galleryButton.isClickable){
                nextPage()
            }
        }
    }

    private fun nextPage(){
        val action = HomeFragmentDirections.actionNavHomeToNavGallery()
        requireView().findNavController().navigate(action)
    }
}