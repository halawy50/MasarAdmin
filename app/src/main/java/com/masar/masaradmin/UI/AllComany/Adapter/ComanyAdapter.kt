package com.masar.masaradmin.UI.AllComany.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.masar.masaradmin.Data.DataComany
import com.masar.masaradmin.R
import com.masar.masaradmin.databinding.ItemComanyBinding

class ComanyAdapter(var lsitCompany: List<DataComany>) : RecyclerView.Adapter<ComanyAdapter.CompanyHolder>() {
    inner class CompanyHolder(var binding: ItemComanyBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyHolder {
        return CompanyHolder(ItemComanyBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun getItemCount(): Int {
        return lsitCompany.size
    }
    override fun onBindViewHolder(holder: CompanyHolder, position: Int) {
        var company = lsitCompany[position]
        holder.binding.apply {
            nameCamony.text = "${company.name_Company}"
            if (company.state==true){
                stateCompany.text = "تعمل حاليا"
            }else{
                stateCompany.text = "تم حظرها"
            }
        }
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("idCompany" to company.idComapny,
                "state" to company.state,"nameCompany" to company.name_Company)

            it.findNavController().navigate(R.id.action_allComanyFragment_to_showComanyFragment,bundle)
        }
    }
}