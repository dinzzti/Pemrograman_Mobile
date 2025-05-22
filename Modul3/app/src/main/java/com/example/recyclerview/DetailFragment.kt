package com.example.recyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.recyclerview.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val title = arguments?.getString("EXTRA_TITLE")
        val photo = arguments?.getString("EXTRA_PHOTO")
        val plot = arguments?.getString("EXTRA_PLOT")
        val year = arguments?.getString("EXTRA_YEAR")
        val cast = arguments?.getString("EXTRA_CAST")

        binding.tvName.text = title
        binding.tvPlot.text = plot
        binding.tvYear.text = "Year: $year"
        binding.tvCast.text = "Cast: $cast"
        photo?.let {
            Glide.with(requireContext())
                .load(it)
                .into(binding.imgItemPhoto)
        }

        binding.buttonKembali.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
