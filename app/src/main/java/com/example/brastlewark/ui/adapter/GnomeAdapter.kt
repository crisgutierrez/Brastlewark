package com.example.brastlewark.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.brastlewark.R
import com.example.brastlewark.ext.loadRemoteAsset
import com.example.brastlewark.model.Gnome
import com.example.brastlewark.util.Utils

class GnomeAdapter(private val onGnomeClickedListener: OnGnomeClickedListener? = null): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnGnomeClickedListener {
        fun onGnomeClicked(gnomeId: Int)
    }

    private var items= listOf<Gnome>()

    class GnomeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val iconView: ImageView = view.findViewById(R.id.gnome_avatar)
        val nameView: TextView = view.findViewById(R.id.gnome_name)
        val professionView: TextView = view.findViewById(R.id.gnome_profession)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_gnome, parent, false)
        return GnomeViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GnomeViewHolder).apply {
            val uri = Uri.parse(items[position].avatar)
            iconView.loadRemoteAsset(uri, true)
            nameView.text = items[position].name
            professionView.text = Utils.printList(items[position].professions)
            professionView.isVisible = items[position].professions.isNotEmpty()


            onGnomeClickedListener?.let { listener ->
                holder.view.setOnClickListener {
                    listener.onGnomeClicked(items[position].id)
                }
            }
        }
    }

    fun setGnomes(gnomes: List<Gnome>) {
        items = gnomes
        notifyDataSetChanged()
    }

}