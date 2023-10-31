package com.masar.masaradmin.UI

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.masar.masaradmin.R
import com.masar.masaradmin.databinding.FragmentHomePageBinding

class HomePageFragment : Fragment() {
    lateinit var binding : FragmentHomePageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
//        requireActivity().window.statusBarColor = Color.parseColor("#CCCCCC")
        binding = FragmentHomePageBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addComany.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_addNewCompanyFragment)
        }
        binding.allComany.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_allComanyFragment)

        }
        binding.signOut.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_login_Delivery_Fragment)
            val sharedPref = requireContext().getSharedPreferences("Customer", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("email","")
            editor.putString("password","")//Student
            editor.apply()
        }


    }


}