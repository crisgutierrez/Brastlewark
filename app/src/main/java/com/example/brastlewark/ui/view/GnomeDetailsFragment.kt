package com.example.brastlewark.ui.view

import android.app.ActionBar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.brastlewark.R


class GnomeDetailsFragment : Fragment() {

    private val fragmentArgs: GnomeDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
        // calling the action bar
//        val actionBar: ActionBar? = requireActivity().actionBar

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gnome_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gnome = fragmentArgs.gnome
        Log.e("cristian", "GnomeDetailsFragment gnome: $gnome")
        // showing the back button in action bar
//        requireActivity().actionBar?.setDisplayHomeAsUpEnabled(true);
    }

}