package com.example.viewmodel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewmodel.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DramaViewModel by viewModels {
        DramaViewModelFactory(requireContext().resources)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvDrama.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDrama.setHasFixedSize(true)

        val adapter = DramaAdapter(
            listDrama = emptyList(),
            onWikiClick = { link ->
                val uri = Uri.parse(link)
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            },
            onDetailClick = { drama ->
                viewModel.onDramaClicked(drama)
            }
        )

        binding.rvDrama.adapter = adapter

        lifecycleScope.launch {
            viewModel.dramaList.collectLatest { dramas ->
                adapter.updateData(dramas)
            }
        }

        lifecycleScope.launch {
            viewModel.eventClick.collectLatest { drama ->
                android.util.Log.d(
                    "HomeFragment",
                    "Pindah ke Detail dengan data: ${drama.title}, ${drama.year}, ${drama.cast}"
                )

                val detailFragment = DetailFragment().apply {
                    arguments = Bundle().apply {
                        putString("EXTRA_TITLE", drama.title)
                        putString("EXTRA_PHOTO", drama.photo)
                        putString("EXTRA_PLOT", drama.plot)
                        putString("EXTRA_YEAR", drama.year)
                        putString("EXTRA_CAST", drama.cast)
                    }
                }
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_container, detailFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

