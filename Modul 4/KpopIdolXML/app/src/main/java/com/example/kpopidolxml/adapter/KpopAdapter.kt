package com.example.kpopidolxml.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kpopidolxml.databinding.ItemKpopBinding
import com.example.kpopidolxml.model.KpopIdol

class KpopAdapter(
    private val listKpop: List<KpopIdol>,
    private val onDetailClick: (KpopIdol) -> Unit,
    private val onExplicitIntentClick: (String) -> Unit
) : RecyclerView.Adapter<KpopAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemKpopBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemKpopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val idol = listKpop[position]

        holder.binding.tvNama.text = idol.nama
        holder.binding.tvGrup.text = idol.grup
        holder.binding.tvDeskripsi.text = idol.deskripsi
        holder.binding.imgFoto.setImageResource(idol.foto)

        holder.binding.btnProfil.setOnClickListener {
            onExplicitIntentClick(idol.urlProfil)
        }

        holder.binding.btnDetail.setOnClickListener {
            onDetailClick(idol)
        }
    }

    override fun getItemCount(): Int {
        return listKpop.size
    }
}