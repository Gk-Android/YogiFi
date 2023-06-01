package com.example.yogifi.views.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yogifi.data.model.product.ProductItem
import com.example.yogifi.databinding.ItemProductBinding
import com.example.yogifi.views.ProductFragmentDirections

class ProductListAdapter(val context : Context): RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {
    private var productList:List<ProductItem>? = null

    inner class MyViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(productItem: ProductItem?, isExtraText: Boolean){
            binding.tvTitle.text = productItem?.name
            binding.tvBrand.text = productItem?.brand
            binding.tvPrice.text = "INR ${productItem?.price}"

            Glide.with(context)
                .load(productItem?.productUrl)
                .into(binding.ivProduct)

            if ( isExtraText ){
                binding.tvMessage.visibility = View.VISIBLE
            }

            binding.tvMessage.setOnClickListener {
                Toast.makeText(context , "Thank you for joining our rewards program",Toast.LENGTH_LONG).show()
            }

            binding.cvProduct.setOnClickListener {
                val action = ProductFragmentDirections.actionProductFragmentToProductItemDetail(productItem!!)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val binding = ItemProductBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        productList?.let {
            if (position == 2){
                viewHolder.bind(productList!![position] , true)
            } else {
                viewHolder.bind(productList!![position], false)
            }
        }

    }

    override fun getItemCount(): Int {
        return productList?.let{
            it.size
        }?:0
    }

    fun setData(productList : List<ProductItem>){
        this.productList = productList
        this.notifyDataSetChanged()
    }
}