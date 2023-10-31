package com.masar.masaradmin.UI.AllComany

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.masar.masaradmin.Data.BookInfo_Data
import com.masar.masaradmin.R
import com.masar.masaradmin.databinding.FragmentShowComanyBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ShowComanyFragment : Fragment() {

    lateinit var binding : FragmentShowComanyBinding
    var idComany = ""
    var stateBundle = true
    var nameCompany = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {    bundle ->
            idComany=bundle.getString("idCompany")!!
            stateBundle=bundle.getBoolean("state")!!
            nameCompany=bundle.getString("nameCompany")!!

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowComanyBinding.inflate(layoutInflater,container,false)
        getDataCompany()
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var state = arrayOf("تعمل حاليا", "تم حظرها")
        var stateCompany = ArrayAdapter(requireContext(), R.layout.item_drom_down_city, state)
        binding.stateCompany.setAdapter(stateCompany)
        binding.nameComany.setText("${nameCompany}")
        binding.stateCompany.setOnItemClickListener { adapterView, view, i, l ->
            var stateText = binding.stateCompany.text.toString()
            if (stateText.equals("تعمل حاليا")){
                FirebaseFirestore.getInstance().collection("Company").
                document(idComany).update("state",true)
                Snackbar.make(
                    binding.root,
                    "تم تحديث البيانات",
                    Snackbar.LENGTH_LONG
                )
                    .show()
                setStateText(true)

            }
            else if (stateText.equals("تم حظرها")){
                FirebaseFirestore.getInstance().collection("Company").document(idComany).update("state",false)
                Snackbar.make(
                    binding.root,
                    "تم تحديث البيانات",
                    Snackbar.LENGTH_LONG
                )
                    .show()
                setStateText(false)

            }
        }
        binding.imageBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun getDataCompany() {

        CoroutineScope(Dispatchers.IO).launch {
            var done = 0
            var reject = 0
            var pending = 0

            try {
               var state =  FirebaseFirestore.getInstance().collection("Company").
                document(idComany).get().await().getBoolean("state")

                setStateText(state == true)
                // Fetch the list of books for the specified company
                val listBookCompany = FirebaseFirestore.getInstance()
                    .collection("Book")
                    .whereEqualTo("idCompany", idComany) // Assuming 'idCompany' is a valid variable
                    .get()
                    .await()
                    .toObjects(BookInfo_Data::class.java) // Assuming 'BookInfoData' is your data class

                // Calculate statistics based on book states
                for (book in listBookCompany) {
                    when (book.stateBook) {
                        1 -> pending++
                        0 -> reject++
                        3 -> done++
                    }
                }

                // Update the UI on the main thread
                withContext(Dispatchers.Main) {
                    FirebaseFirestore.getInstance().collection("Company").document(idComany).get().addOnSuccessListener {
                       var email = it.getString("email").toString()
                       var password = it.getString("password").toString()
                        binding.etUserEmail.setText("$email")
                        binding.password.setText("$password")
                    }
                    binding.done.text = done.toString()
                    binding.reject.text = reject.toString()
                    binding.pending.text = pending.toString()

                }
            } catch (e: Exception) {
                // Handle exceptions (e.g., network issues, Firestore errors)
                e.printStackTrace()
                // You can add appropriate error handling here
            }
        }
    }

    fun setStateText(state : Boolean){
        if (state == true){
            binding.state.setText("تعمل حاليا")
        }
        else if (state == false){
            binding.state.setText("تم حظر الشركة")
        }
    }
}