package com.example.thecocktailsapp.view

import BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thecocktailsapp.databinding.FragmentCommonViewBinding

class FilterFragment : BaseFragment() {


    private val binding by lazy {
        FragmentCommonViewBinding.inflate(layoutInflater)
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