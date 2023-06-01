package com.example.yogifi.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.yogifi.R
import com.example.yogifi.data.model.profile.ProfileResponse
import com.example.yogifi.data.repository.ProfileRepository
import com.example.yogifi.data.webservice.RetrofitClient
import com.example.yogifi.data.webservice.Webservice
import com.example.yogifi.databinding.FragmentProfileBinding
import com.example.yogifi.utils.Resource
import com.example.yogifi.viewmodel.profile.ProfileViewModel
import com.example.yogifi.viewmodel.profile.ProfileViewModelFactory


/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding
    private var profileData : ProfileResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webservice = RetrofitClient.getInstance().create(Webservice::class.java)
        val repository = ProfileRepository(webservice)
        viewModel = ViewModelProvider(this , ProfileViewModelFactory(repository)).get(ProfileViewModel::class.java)
        getProfileData()
    }

    private fun setupProfileUI() {
        binding.tvName.text = profileData?.username
        binding.tvMob.text = "9279316994";
        binding.tvPointEarnedValue.text = profileData?.pointsEarned
        binding.tvWalletBalanceValue.text = profileData?.walletBalance
        binding.tvAdd.text = profileData?.address
    }

    private fun getProfileData(){
        viewModel.getProfileData()
        viewModel.profileData.observe(viewLifecycleOwner , Observer {response->
            when(response){
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    response.data?.let {
                        profileData = it
                    }
                    setupProfileUI()
                }
                is Resource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Error ->{
                    binding.progressBar.visibility = View.GONE
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
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {

            }
    }
}