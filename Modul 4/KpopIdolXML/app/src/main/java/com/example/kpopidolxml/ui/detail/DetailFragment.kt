package com.example.kpopidolxml.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kpopidolxml.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nama = arguments?.getString("NAMA")
        val grup = arguments?.getString("GRUP")
        val deskripsi = arguments?.getString("DESKRIPSI")
        val foto = arguments?.getInt("FOTO") ?: 0

        binding.tvDetailNama.text = nama
        binding.tvDetailGrup.text = grup
        binding.tvDetailDeskripsi.text = deskripsi
        if (foto != 0) {
            binding.imgDetailFoto.setImageResource(foto)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}