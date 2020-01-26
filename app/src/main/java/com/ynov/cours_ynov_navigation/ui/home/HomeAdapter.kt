package com.ynov.cours_ynov_navigation.ui.home

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

        fun bind(product: Product, clickListener: (Product) -> Unit){
            productName.text = product.name
            itemView.setOnClickListener { clickListener(product) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val item: LinearLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.product_recycler_view_item,
            parent,
            false
        ) as LinearLayout

        return ProductViewHolder(productItem = item)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(products[position], onClickListener)

    fun updateList(product: List<Product>) {
        products.clear()
        products.addAll(product)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return products.size
    }


}