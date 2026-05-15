package com.example.kpopidolxml.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kpopidolxml.databinding.ItemKpopHorizontalBinding
import com.example.kpopidolxml.model.KpopIdol

class KpopHorizontalAdapter(
    private val listKpop: List<KpopIdol>,
    private val onDetailClick: (KpopIdol) -> Unit,
    private val onExplicitIntentClick: (String) -> Unit
) : RecyclerView.Adapter<KpopHorizontalAdapter.HorizontalViewHolder>() {

    class HorizontalViewHolder(var binding: ItemKpopHorizontalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        val binding = ItemKpopHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HorizontalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        val idol = listKpop[position]
        holder.binding.tvNamaHorizontal.text = idol.nama
        holder.binding.tvGrupHorizontal.text = idol.grup
        holder.binding.tvDeskripsiHorizontal.text = idol.deskripsi
        holder.binding.imgFotoHorizontal.setImageResource(idol.foto)

        holder.binding.btnProfilHorizontal.setOnClickListener {
            onExplicitIntentClick(idol.urlProfil)
        }

        holder.binding.btnDetailHorizontal.setOnClickListener {
            onDetailClick(idol)
        }
    }

    override fun getItemCount(): Int {
        return listKpop.size
    }
}