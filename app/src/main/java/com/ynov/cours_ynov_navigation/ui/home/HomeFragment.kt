package com.ynov.cours_ynov_navigation.ui.home

import android.graphics.Path
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ynov.cours_ynov.models.ApiResponse
import com.ynov.cours_ynov.models.Product
import com.ynov.cours_ynov_navigation.R
import com.ynov.cours_ynov_navigation.network.ApiError
import com.ynov.cours_ynov_navigation.network.ApiHelpers
import com.ynov.cours_ynov_navigation.network.ApiRequestCallback
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {


    var productList : List<Product> = listOf()
    var adapter: HomeAdapter = HomeAdapter(onClickListener = {product ->
        onProductClickListener(product)
    })

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

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

    }

    private fun onProductClickListener(product: Product) {
        val action = HomeFragmentDirections.actionNavHomeToProductDetailFragment(product)
        requireView().findNavController().navigate(action)
    }

}