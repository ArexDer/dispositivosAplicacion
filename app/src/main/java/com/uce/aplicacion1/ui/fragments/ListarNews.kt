package com.uce.aplicacion1.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uce.aplicacion1.R
import com.uce.aplicacion1.databinding.FragmentListarNewsBinding


class ListarNews : Fragment() {

private lateinit var binding: FragmentListarNewsBinding



//A este : FragmentListarNewsBinding Tenemos qeu rellenarle con el inflete.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //bind es ATACHAR
        binding= FragmentListarNewsBinding.bind(inflater.inflate(
            R.layout.fragment_listar_news,container,false))
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}