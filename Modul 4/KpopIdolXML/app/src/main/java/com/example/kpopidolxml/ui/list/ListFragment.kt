package com.example.kpopidolxml.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kpopidolxml.R
import com.example.kpopidolxml.adapter.KpopAdapter
import com.example.kpopidolxml.adapter.KpopHorizontalAdapter
import com.example.kpopidolxml.databinding.FragmentListBinding
import com.example.kpopidolxml.model.KpopIdol
import com.example.kpopidolxml.viewmodel.KpopViewModel
import com.example.kpopidolxml.viewmodel.KpopViewModelFactory
import kotlinx.coroutines.launch
import androidx.core.net.toUri

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: KpopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = KpopViewModelFactory("Daftar Kpop XML")
        viewModel = ViewModelProvider(this, factory)[KpopViewModel::class.java]

        val listIdol = listOf(
            KpopIdol("Carmen", "Hearts2Hearts", "Gadis bali kesukaanku, ceria bagaikan matahari", R.drawable.carmen, "https://instagram.com/carmenh2h__/"),
            KpopIdol("Wonyoung", "IVE", "perfect idol, literally perfect", R.drawable.wonyoung, "https://instagram.com/for_everyoung10/"),
            KpopIdol("Eunchae", "Le Sserafim", "ci imupp dari hybe, manchaee", R.drawable.eunchae, "https://intstagram.com/hhh.e_c.v/"),
            KpopIdol("Yuha", "Hearts2Hearts", "ini my bini my semesta my universe", R.drawable.yuha, "https://instagram.com/all_about_yuha"),
            KpopIdol("Karina", "Aespa", "cantik-cantik clumsy, imupp", R.drawable.karina, "https://instagram.com/katarinabluu/"),
            KpopIdol("Haerin", "NewJeans", "KAMU HILANG KEMANA KUCINGG, plis balik", R.drawable.haerin, "https://instagram.com/nj.kangrin/"),
            KpopIdol(nama = "Juun", grup = "Hearts2Hearts", deskripsi = "omaygat ini my oppa, my adek, cancii", foto = R.drawable.juun, urlProfil = "https://instagram.com/juun.h2h/")
        )

        viewModel.loadData(listIdol)

        binding.rvKpop.layoutManager = LinearLayoutManager(requireContext())

        // Mengamati StateFlow untuk data list
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.idolList.collect { idols ->
                if (idols.isNotEmpty()) {
                    val adapter = KpopAdapter(
                        listKpop = idols,
                        onDetailClick = { idol -> viewModel.onDetailClicked(idol) },
                        onExplicitIntentClick = { url -> viewModel.onExplicitIntentClicked(url) }
                    )
                    adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                    binding.rvKpop.adapter = adapter

                    val horizontalAdapter = KpopHorizontalAdapter(
                        listKpop = idols,
                        onDetailClick = { idol -> viewModel.onDetailClicked(idol) },
                        onExplicitIntentClick = { url -> viewModel.onExplicitIntentClicked(url) }
                    )
                    horizontalAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                    binding.rvKpopHorizontal.adapter = horizontalAdapter
                }
            }
        }

        // Mengamati StateFlow untuk navigasi Detail
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.navigateToDetail.collect { idol ->
                idol?.let {
                    val bundle = Bundle().apply {
                        putString("NAMA", it.nama)
                        putString("GRUP", it.grup)
                        putString("DESKRIPSI", it.deskripsi)
                        putInt("FOTO", it.foto)
                    }
                    findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
                    viewModel.onDetailNavigated()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.navigateToUrl.collect { url ->
                url?.let {
                    val intent = Intent(Intent.ACTION_VIEW, it.toUri())
                    startActivity(intent)
                    viewModel.onExplicitIntentNavigated()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}