package com.example.kpopidolxml.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kpopidolxml.R
import com.example.kpopidolxml.databinding.ItemKpopHorizontalBinding
import com.example.kpopidolxml.model.KpopIdol
import androidx.core.net.toUri

class KpopHorizontalAdapter(private val listKpop: List<KpopIdol>) : RecyclerView.Adapter<KpopHorizontalAdapter.HorizontalViewHolder>() {

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

        holder.binding.btnProfilHorizontal.setOnClickListener { view ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = idol.urlProfil.toUri()
            view.context.startActivity(intent)
        }

        holder.binding.btnDetailHorizontal.setOnClickListener { view ->
            val bundle = Bundle()
            bundle.putString("NAMA", idol.nama)
            bundle.putString("GRUP", idol.grup)
            bundle.putString("DESKRIPSI", idol.deskripsi)
            bundle.putInt("FOTO", idol.foto)

            view.findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return listKpop.size
    }
}