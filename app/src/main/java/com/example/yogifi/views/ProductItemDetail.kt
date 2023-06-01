package com.example.yogifi.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.yogifi.R
import com.example.yogifi.data.model.product.ProductItem
import com.example.yogifi.databinding.FragmentProductItemDetailBinding

class ProductItemDetail : Fragment() {

    private lateinit var binding:FragmentProductItemDetailBinding
    private lateinit var args : ProductItemDetailArgs
    private var productItem: ProductItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductItemDetailBinding.inflate(layoutInflater , container,false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args = ProductItemDetailArgs.fromBundle(requireArguments())
        productItem = args.ProductItem

        Glide.with(this)
            .load(productItem?.productUrl)
            .into(binding.ivItem)

        binding.tvName.text = productItem?.name
        binding.tvBrand.text = productItem?.brand
        binding.tvPrice.text = "INR ${productItem?.price}"

        binding.tvDetails.text = productItem?.productDesc

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductItemDetail().apply {

            }
    }
}