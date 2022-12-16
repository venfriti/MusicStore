package com.example.musicstore.listing

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.musicstore.R
import com.example.musicstore.databinding.AlbumViewBinding
import com.example.musicstore.databinding.FragmentListingBinding
import com.example.musicstore.models.Album


class ListingFragment : Fragment(), LifecycleObserver {

    private lateinit var binding: FragmentListingBinding
    private lateinit var viewModel: ListingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listing, container, false)

        binding.listButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_listingFragment_to_detailFragment)
        )

        viewModel = ViewModelProvider(this)[ListingViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner

        lifecycle.addObserver(viewModel)

        val nameObserver = Observer<List<Album>>{item ->
            albumListView(item)
        }

        viewModel.albumList.observe(viewLifecycleOwner, nameObserver)

        return binding.root
    }

    private fun albumListView(item: List<Album>?) {
        Toast.makeText(context, "text working", Toast.LENGTH_SHORT).show()
        item?.forEach{
            val viewBinding = AlbumViewBinding.inflate(LayoutInflater.from(requireContext()), binding.albumLayout, false)
            with(viewBinding){
                albumName.text = it.name
                artistName.text = it.artist
                yearName.text = it.year
                descriptionName.text = it.description
            }
            binding.albumLayout?.addView(viewBinding.root)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return NavigationUI.onNavDestinationSelected(menuItem, requireView().findNavController())
            }


        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}
