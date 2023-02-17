package com.example.thecocktailsapp.utils

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.thecocktailsapp.databinding.CommonRecyclerViewBinding
import com.example.thecocktailsapp.databinding.FragmentCommonViewBinding
import com.example.thecocktailsapp.model.domain.Drink
import com.example.thecocktailsapp.viewmodule.MyCocktailsAppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    protected val bindingRv: CommonRecyclerViewBinding by lazy {
        CommonRecyclerViewBinding.inflate(layoutInflater)
    }

    protected val cocktailViewModel: MyCocktailsAppViewModel by lazy {
        ViewModelProvider(requireActivity())[MyCocktailsAppViewModel::class.java]

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun showError(message: String, action: () -> Unit) {
        AlertDialog.Builder(requireActivity())
            .setTitle("Error Occurred")
            .setMessage(message)
            .setPositiveButton("RETRY") { dialog, _ ->
                action()
                dialog.dismiss()
            }
            .setNegativeButton("DISMISS") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}