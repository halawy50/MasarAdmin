package com.masar.masaradmin.UI.Login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.masar.masaradmin.R
import com.masar.masaradmin.UI.ProgressDialog
import com.masar.masaradmin.databinding.FragmentLoginDeliveryBinding


class Login_Delivery_Fragment : Fragment() {

    lateinit var binding: FragmentLoginDeliveryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginDeliveryBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        save()
        binding.buttonSignIn.setOnClickListener {
            signIn()
        }
    }
    fun signIn(){
        var email = binding.etUserEmail.text.toString()
        var password = binding.etPassword.text.toString()
        if (email.isEmpty()){
            binding.etUserEmail.setError("المكان فارغ")
            return
        }
        if (password.isEmpty()){
            binding.etPassword.setError("المكان فارغ")
            return
        }


        var progressDialog =  ProgressDialog(requireContext())
        progressDialog.show()
        FirebaseFirestore.getInstance().collection("Admin").
        document("EBbgKXuP9nrQaMNsbJBq").get().addOnSuccessListener {

            if (email.equals(it.getString("email"))&&password.equals(it.getString("password"))){
                val sharedPref = requireContext().getSharedPreferences("Customer", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("email",email)
                editor.putString("password",password)//Student
                editor.apply()
                findNavController().navigate(R.id.action_login_Delivery_Fragment_to_homePageFragment)
                progressDialog.dismiss()

            }else{
                Toast.makeText(requireContext(),"يوجد خطا في البريد الاليكتروني او كلمة السر",Toast.LENGTH_LONG).show()
                progressDialog.dismiss()
            }
        }
    }
    fun save(){
        var email =""
        var password = ""
        val sharedPref = requireContext().getSharedPreferences("Customer", Context.MODE_PRIVATE)
        email= sharedPref.getString("email","").toString()
        password = sharedPref.getString("password","").toString()
        binding.etUserEmail.setText("${email}")
        binding.etPassword.setText("${password}")
        if (email.isNotEmpty()&&password.isNotEmpty()){

            var progressDialog =  ProgressDialog(requireContext())
            progressDialog.show()
            FirebaseFirestore.getInstance().collection("Admin").
            document("TTgdPh6AzY6WFD7YaXFd").get().addOnSuccessListener {

                if (email.equals(it.getString("email"))&&password.equals(it.getString("password"))){
                    val sharedPref = requireContext().getSharedPreferences("Customer", Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.putString("email",email)
                    editor.putString("password",password)//Student
                    editor.apply()
                    findNavController().navigate(R.id.action_login_Delivery_Fragment_to_homePageFragment)
                    progressDialog.dismiss()

                }else{
                    Toast.makeText(requireContext(),"يوجد خطا في البريد الاليكتروني او كلمة السر",Toast.LENGTH_LONG).show()
                    progressDialog.dismiss()
                }
            }
        }else{
        }
    }
}