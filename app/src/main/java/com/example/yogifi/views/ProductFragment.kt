package com.example.yogifi.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yogifi.data.model.product.ProductItem
import com.example.yogifi.data.repository.ProductRepository
import com.example.yogifi.data.webservice.RetrofitClient
import com.example.yogifi.data.webservice.Webservice
import com.example.yogifi.databinding.FragmentProductBinding
import com.example.yogifi.utils.Resource
import com.example.yogifi.viewmodel.product.ProductViewModel
import com.example.yogifi.viewmodel.product.ProductViewModelFactory
import com.example.yogifi.views.adapter.ProductListAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log


/**
 * A simple [Fragment] subclass.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding
    private lateinit var productListAdapter: ProductListAdapter
    private var productList:List<ProductItem>? = null

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webservice = RetrofitClient.getInstance().create(Webservice::class.java)
        val productRepository = ProductRepository(webservice)

        productViewModel = ViewModelProvider(this, ProductViewModelFactory(productRepository)).get(
            ProductViewModel::class.java)

        productListAdapter = ProductListAdapter(requireContext())
        getProductList()
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productListAdapter
        }
    }

    private fun getProductList(){
        productViewModel.getProductList()
        productViewModel.productList.observe(viewLifecycleOwner , Observer{response->
            when(response){
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    response.data?.let {
                        productListAdapter.setData(it)
                    }
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context , response.message , Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}