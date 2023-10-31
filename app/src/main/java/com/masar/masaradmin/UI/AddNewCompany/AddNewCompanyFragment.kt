package com.masar.masaradmin.UI.AddNewCompany

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.masar.masaradmin.Data.DataComany
import com.masar.masaradmin.R
import com.masar.masaradmin.UI.ProgressDialog
import com.masar.masaradmin.databinding.FragmentAddNewCompanyBinding
import com.masar.masaradmin.databinding.FragmentHomePageBinding


class AddNewCompanyFragment : Fragment() {
    lateinit var binding : FragmentAddNewCompanyBinding
    var city = ""
    val values = arrayOf(
        "العاصمة",
        "الزرقاء",
        "السلط",
        "مادبا",
        "اربد",
        "عجلون",
        "جرش",
        "المفرق",
        "الكرك",
        "الطفيلة",
        "معان",
        "العقبة",
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewCompanyBinding.inflate(layoutInflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //City Spinner
        var adapterCity = ArrayAdapter(requireContext(), R.layout.item_drom_down_city, values)
        binding.autoCompeleteCity.setAdapter(adapterCity)
        binding.autoCompeleteCity.setOnItemClickListener { adapterView, view, i, l ->
            city = binding.autoCompeleteCity.text.toString()
        }

       binding.buttonSubmit.setOnClickListener {
           addComany()
       }
        binding.imageBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    fun addComany(){
        var name_Company = binding.nameCompany.text.toString()
        var NationalNumberPlace = binding.NationalNumberPlace.text.toString()
        var addressComany = binding.addressComany.text.toString()
        var NumberCommerceIndestury = binding.NumberCommerceIndestury.text.toString()
        var nameManagerComany = binding.nameManagerComany.text.toString()
        var NationalIDManager = binding.NationalIDManager.text.toString()
        var CompanylicenseNumbeInLandTransportAuthority = binding.CompanylicenseNumbeInLandTransportAuthority.text.toString()
        var etUserEmail = binding.etUserEmail.text.toString()
        var password = binding.password.text.toString()

        if (name_Company.isEmpty()){
            binding.nameCompany.setError("المكان فارغ")
            return
        }
        if (NationalNumberPlace.isEmpty()){
            binding.NationalNumberPlace.setError("المكان فارغ")
            return
        }
        if (addressComany.isEmpty()){
            binding.addressComany.setError("المكان فارغ")
            return
        }
        if (NumberCommerceIndestury.isEmpty()){
            binding.NumberCommerceIndestury.setError("المكان فارغ")
            return
        }
        if (nameManagerComany.isEmpty()){
            binding.nameManagerComany.setError("المكان فارغ")
            return
        }
        if (NationalIDManager.isEmpty()){
            binding.NationalIDManager.setError("المكان فارغ")
            return
        }
//        if (CompanylicenseNumbeInLandTransportAuthority.isEmpty()){
//            binding.CompanylicenseNumbeInLandTransportAuthority.setError("المكان فارغ")
//            return
//        }
        if (etUserEmail.isEmpty()){
            binding.etUserEmail.setError("المكان فارغ")
            return
        }
        if (password.isEmpty()){
            binding.password.setError("المكان فارغ")
            return
        }
        if (city.length<=0 || city.isNullOrEmpty()){
            binding.autoCompeleteCity.setError("اختر المحافظة")
            return
        }

        else{
            var progressDialog =  ProgressDialog(requireContext())
            progressDialog.show()
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(etUserEmail,password).addOnSuccessListener {
               var token = Firebase.auth.uid.toString()
                var dataCompany=DataComany(token,name_Company,NationalNumberPlace,addressComany,
                    NumberCommerceIndestury,nameManagerComany,NationalIDManager,
                    CompanylicenseNumbeInLandTransportAuthority,etUserEmail,password,city,true)
                FirebaseFirestore.getInstance().collection("Company").document(token).set(dataCompany).addOnSuccessListener {
                    progressDialog.dismiss()
                    Snackbar.make(
                        binding.root,
                        "تم اضافة الشركة بنجاح",
                        Snackbar.LENGTH_LONG
                    )
                        .show()
                    findNavController().popBackStack()
                }
            }
        }
    }



}