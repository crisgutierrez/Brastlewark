package com.example.brastlewark.ui.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.brastlewark.R
import com.example.brastlewark.ext.loadRemoteAsset
import com.example.brastlewark.model.Gnome
import com.example.brastlewark.util.Utils
import kotlinx.android.synthetic.main.fragment_gnome_details.*


class GnomeDetailsFragment : Fragment() {

    private val fragmentArgs: GnomeDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    // region LIFECYCLE ----------------------------------------------------------------------------
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gnome_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gnome = fragmentArgs.gnome
        if (gnome != null) {
            setLayout(gnome)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // endregion

    // region PRIVATE METHODS -----------------------------------------------------------------------
    private fun setLayout(gnome: Gnome) {
        (activity as MainActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val uri = Uri.parse(gnome.avatar)
        gnome_image.loadRemoteAsset(uri)
        gnome_name.text = gnome.name
        gnome_age.text = getString(R.string.age_text, gnome.age)
        gnome_weight.text = getString(R.string.weight_text, gnome.weight)
        gnome_height.text = getString(R.string.height_text, gnome.height)
        gnome_hair_color.text = getString(R.string.hair_color_text, gnome.hairColor)

        gnome_profession.text = if (gnome.professions.isNotEmpty()) {
            getString(R.string.profession_text, Utils.printList(gnome.professions))
        } else {
            getString(R.string.profession_text, getString(R.string.general_none))
        }
        gnome_friend.text = if (gnome.friends.isNotEmpty()) {
            getString(R.string.friend_text, Utils.printList(gnome.friends))
        } else {
            getString(R.string.friend_text, getString(R.string.general_none))
        }

        contact_button.text = getString(R.string.contact_gnome_button, gnome.name)
        contact_button.setOnClickListener { Toast.makeText(
            requireContext(),
            R.string.general_not_implemented,
            Toast.LENGTH_SHORT
        ).show() }
    }
    // endregion

}