package com.example.connectapi.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.connectapi.databinding.FragmentDetailBinding
import com.example.connectapi.domain.model.Drama

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

        val drama = arguments?.getParcelable<Drama>("drama")
        drama?.let {
            binding.tvDetailTitle.text = it.name
            binding.tvDetailOverview.text = it.overview
            binding.tvDetailReleaseDate.text = it.firstAirDate
            binding.ivDetailPoster.load("https://image.tmdb.org/t/p/w500${it.posterPath}") {
                crossfade(true)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}