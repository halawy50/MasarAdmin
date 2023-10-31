package com.masar.masaradmin.UI

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.masar.masaradmin.databinding.DialogProgressBinding

class ProgressDialog(context: Context) : AppCompatDialog(context) {
    private lateinit var binding: DialogProgressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DialogProgressBinding.inflate(layoutInflater)
        var view=binding.root
        setContentView(view)

    }
}