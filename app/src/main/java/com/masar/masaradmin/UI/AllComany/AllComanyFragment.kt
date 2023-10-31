package com.masar.masaradmin.UI.AllComany

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.masar.masaradmin.Data.DataComany
import com.masar.masaradmin.R
import com.masar.masaradmin.UI.AllComany.Adapter.ComanyAdapter
import com.masar.masaradmin.databinding.FragmentAllComanyBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AllComanyFragment : Fragment() {
    lateinit var binding : FragmentAllComanyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllComanyBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllCompany()
        binding.imageBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    fun getAllCompany() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Fetch the list of companies from Firestore
                val querySnapshot = FirebaseFirestore.getInstance().collection("Company").get().await()

                // Initialize an empty list to store companies
                val listCompany = mutableListOf<DataComany>()

                // Iterate through the documents and convert them to DataCompany objects
                for (document in querySnapshot.documents) {
                    val company = document.toObject(DataComany::class.java)
                    company?.let {
                        listCompany.add(it)
                    }
                }

                // Switch to the main thread to update the UI
                withContext(Dispatchers.Main) {
                    if (listCompany.isEmpty()){
                        binding.shimmerlist.isGone = true
                        binding.emptyCompany.isGone =false

                    }
                    else{
                        binding.shimmerlist.isGone = true
                        binding.emptyCompany.isGone =true


                        // Create an adapter with the list of companies
                        val allCompanyAdapter = ComanyAdapter(listCompany)
                        // Assuming you have a reference to your RecyclerView in the 'binding' object
                        binding.allCompanyReycler.adapter = allCompanyAdapter
                    }

                }
            } catch (e: Exception) {
                // Handle exceptions (e.g., network issues, Firestore errors)
                e.printStackTrace()
                // You can add appropriate error handling here
            }
        }
    }



}