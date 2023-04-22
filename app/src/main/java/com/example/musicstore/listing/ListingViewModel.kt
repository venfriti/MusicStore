package com.example.musicstore.listing

import androidx.lifecycle.LifecycleObserver
import  androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicstore.models.Album



class ListingViewModel : ViewModel(), LifecycleObserver {

    private var albumCompiled = mutableListOf<Album>()

    private var _albumList = MutableLiveData<List<Album>>()
    val albumList : LiveData<List<Album>>
    get() = _albumList



    fun onSave(name: String, artist: String, year: String, description: String) {
        val newAlbum = Album(name, artist, year, description)
        newAlbum.let { item ->
            albumCompiled.add(item)
            _albumList.value = albumCompiled
        }
    }

}