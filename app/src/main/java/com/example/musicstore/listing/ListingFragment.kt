package com.example.musicstore.listing

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.musicstore.R
import com.example.musicstore.databinding.AlbumViewBinding
import com.example.musicstore.databinding.FragmentListingBinding
import com.example.musicstore.models.Album


class ListingFragment : Fragment(), LifecycleObserver{

    private lateinit var binding: FragmentListingBinding
    private val viewModel: ListingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listing, container, false)

        binding.listButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_listingFragment_to_detailFragment)
        )

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    private fun albumListView(item: List<Album>?) {
        item?.forEach{
            val viewBinding = AlbumViewBinding.inflate(LayoutInflater.from(requireContext()), binding.albumLayout, false)
            with(viewBinding){
                albumName.text = it.name
                artistName.text = it.artist
                yearName.text = it.year
                descriptionName.text = it.description
            }
            binding.albumLayout.addView(viewBinding.root)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val nameObserver = Observer<List<Album>>{item ->
            albumListView(item)
        }

        viewModel.albumList.observe(viewLifecycleOwner, nameObserver)


        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.titleFragment){
                    findNavController().navigateUp()
                    return true
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}
