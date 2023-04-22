package com.example.musicstore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.musicstore.databinding.FragmentInstructionsBinding


class InstructionsFragment : Fragment() {

    private lateinit var binding : FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)

        binding.getStartedButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_instructionsFragment_to_listingFragment)
        )

        return binding.root
    }

}