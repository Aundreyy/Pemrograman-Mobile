package com.example.kpopidolxml.adapter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.navigation.findNavController
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kpopidolxml.databinding.ItemKpopBinding
import com.example.kpopidolxml.model.KpopIdol
import com.example.kpopidolxml.R


class KpopAdapter(private val listKpop: List<KpopIdol>) : RecyclerView.Adapter<KpopAdapter.ListViewHolder>() {

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

        holder.binding.btnProfil.setOnClickListener { view ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(idol.urlProfil)
            view.context.startActivity(intent)
        }

        holder.binding.btnDetail.setOnClickListener { view ->
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