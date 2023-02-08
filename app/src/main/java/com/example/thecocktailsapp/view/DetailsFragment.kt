
package com.example.thecocktailsapp.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starwarsmvvm.utils.BaseFragment
import com.example.thecocktailsapp.databinding.FragmentDetailsBinding

class DetailsFragment : BaseFragment() {


    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //todo
        return binding.root
        }


        // Inflate the layout for this fragment
    }

