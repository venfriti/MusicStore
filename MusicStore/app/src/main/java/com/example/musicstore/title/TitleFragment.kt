package com.example.musicstore.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.musicstore.R
import com.example.musicstore.databinding.FragmentTitleBinding

class TitleFragment : Fragment(), LifecycleObserver {

    private lateinit var binding: FragmentTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false)

        binding.loginButton.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_welcomeFragment)
        )

        binding.registerButton.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_welcomeFragment)
        )

        return binding.root
    }
}