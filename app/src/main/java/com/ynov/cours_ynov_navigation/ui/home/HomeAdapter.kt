package com.ynov.cours_ynov_navigation.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.ynov.cours_ynov.models.Product
import com.ynov.cours_ynov_navigation.R
import kotlinx.android.synthetic.main.product_recycler_view_item.view.*

class HomeAdapter(
    private val onClickListener: (Product) -> Unit
) : RecyclerView.Adapter<HomeAdapter.ProductViewHolder>() {

    private val products: MutableList<Product> = mutableListOf()

    class ProductViewHolder(val productItem: View) : RecyclerView.ViewHolder(productItem) {
        val productName = productItem.productName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val item: LinearLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.product_recycler_view_item,
            parent,
            false
        ) as LinearLayout

        return ProductViewHolder(productItem = item)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var product: Product = products[position]

        holder.productName.text = product.name

        holder.productItem.setOnClickListener{
            Log.d("ON_CLICK", product.name)
        }
    }

    fun updateList(product: List<Product>) {
        products.clear()
        products.addAll(product)
        notifyDataSetChanged()
    }

}
