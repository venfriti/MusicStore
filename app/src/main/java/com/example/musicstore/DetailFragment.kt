package com.example.musicstore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.musicstore.databinding.FragmentDetailBinding
import com.example.musicstore.listing.ListingViewModel
import com.example.musicstore.models.Album


class DetailFragment : Fragment(), LifecycleObserver {

    private lateinit var binding : FragmentDetailBinding
    private val viewModel: ListingViewModel by activityViewModels()
    private val album : Album = Album()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        binding.saveButton.setOnClickListener {
            saveShoeDetails()
        }

        binding.cancelButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_detailFragment_to_listingFragment)
        )


        binding.albumViewModel = album



        return binding.root
    }

    private fun saveShoeDetails() {
        val bind = binding.albumViewModel
        val name = bind?.name.toString()
        val artist = bind?.artist.toString()
        val year = bind?.year.toString()
        val description = bind?.description.toString()

        if (name.isEmpty() || artist.isEmpty() || year.isEmpty() || description.isEmpty()){
            Toast.makeText(context, "Fill in all the fields", Toast.LENGTH_SHORT).show()
        }
        else {
            viewModel.onSave("Name: $name", "Artist: $artist", "Year: $year", "Description: $description")
            findNavController().navigateUp()
            Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
        }

    }

}