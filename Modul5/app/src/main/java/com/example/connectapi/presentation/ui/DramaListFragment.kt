package com.example.connectapi.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connectapi.BuildConfig
import com.example.connectapi.R
import com.example.connectapi.data.local.Database.DramaDatabase
import com.example.connectapi.data.remote.ApiClient
import com.example.connectapi.data.repository.DramaRepositoryImpl
import com.example.connectapi.databinding.FragmentDramaListBinding
import com.example.connectapi.domain.usecase.GetDramaUseCase
import com.example.connectapi.utils.Resource
import com.example.connectapi.utils.AppPreferences
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.coroutines.launch

class DramaListFragment : Fragment() {
    private var _binding: FragmentDramaListBinding? = null
    private val binding get() = _binding!!

    private val dramaViewModel: DramaViewModel by viewModels {
        val dramaDao = DramaDatabase.getDatabase(requireContext()).dramaDao()
        val dramaApiService = ApiClient.dramaApiService
        val apiKey = BuildConfig.TMDB_API_KEY

        val dramaRepository = DramaRepositoryImpl(dramaDao, dramaApiService, apiKey)
        val getDramaUseCase = GetDramaUseCase(dramaRepository)

        DramaViewModelFactory(requireActivity().application, getDramaUseCase)
    }
    private lateinit var dramaAdapter: DramaAdapter
    private lateinit var appPreferences: AppPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDramaListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appPreferences = AppPreferences(requireContext())

        setupRecyclerView()
        observeViewModel()

        val darkModeSwitch = binding.darkModeSwitch

        darkModeSwitch.isChecked = appPreferences.getDarkModeState()

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            appPreferences.saveDarkModeState(isChecked)

            applyTheme(isChecked)
        }
        binding.btnRefreshApi.setOnClickListener {
            dramaViewModel.refreshDramasFromLocal()
        }
        binding.btnClearLocalData.setOnClickListener {
            clearLocalData()
        }
    }
    private fun setupRecyclerView() {
        dramaAdapter = DramaAdapter()
        binding.rvDrama.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dramaAdapter
        }
        dramaAdapter.onDetailClickListener = { drama ->
            val bundle = Bundle().apply {
                putParcelable("drama", drama)
            }
            findNavController().navigate(R.id.action_dramaListFragment_to_detailFragment, bundle)
        }
        dramaAdapter.onNetflixClickListener = { drama ->
            Toast.makeText(context, "Tombol Netflix untuk ${drama.name} diklik!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                dramaViewModel.popularDramasState.collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            if (resource.data.isNullOrEmpty()) {
                                binding.progressBar.visibility = View.VISIBLE
                                binding.rvDrama.visibility = View.GONE
                                binding.tvError.visibility = View.GONE
                            } else {
                                binding.progressBar.visibility = View.GONE
                                binding.rvDrama.visibility = View.VISIBLE
                                binding.tvError.visibility = View.GONE
                                dramaAdapter.submitList(resource.data)
                            }
                        }
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.rvDrama.visibility = View.VISIBLE
                            binding.tvError.visibility = View.GONE
                            resource.data?.let {
                                dramaAdapter.submitList(it)
                            }
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.rvDrama.visibility = View.GONE
                            binding.tvError.visibility = View.VISIBLE
                            binding.tvError.text = resource.message ?: "Terjadi kesalahan tidak diketahui"
                            Toast.makeText(context, "Error: ${resource.message}", Toast.LENGTH_LONG).show()
                            Log.e("DramaListFragment", "Error: ${resource.message}")
                            resource.data?.let {
                                if (it.isNotEmpty()) {
                                    dramaAdapter.submitList(it)
                                    binding.rvDrama.visibility = View.VISIBLE
                                    binding.tvError.visibility = View.GONE
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private fun clearLocalData() {
        lifecycleScope.launch {
            try {
                dramaViewModel.clearLocalData()
                Toast.makeText(context, "Data lokal berhasil dihapus!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(context, "Gagal menghapus data lokal: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                Log.e("DramaListFragment", "Error clearing local data: ${e.localizedMessage}", e)
            }
        }
    }
    private fun applyTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        requireActivity().recreate()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}