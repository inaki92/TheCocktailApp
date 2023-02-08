package com.example.thecocktailsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starwarsmvvm.utils.BaseFragment
import com.example.thecocktailsapp.databinding.FragmentCocktailsBinding

class FilterFragment : BaseFragment() {


    private val binding by lazy {
        FragmentCocktailsBinding.inflate(layoutInflater)
    }

    // todo implement cocktailAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

    //     todo assign adapter

        return binding.root
    }
}