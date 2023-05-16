package com.example.news_feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_feed.adapters.UsersListAdapter
import com.example.news_feed.databinding.FragmentUsersListBinding
import com.example.news_feed.models.UsersModel
import com.example.news_feed.viewModels.UsersFragmentViewModel

class UsersListFragment : Fragment() {

    private var _binding: FragmentUsersListBinding? = null
    private val userAdapter = UsersListAdapter()
    private val viewModel = UsersFragmentViewModel()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersListBinding.inflate(inflater, container, false)

        init()
        viewModel.init()
        return binding.root
    }

    private fun init() {
        binding.rcView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        binding.rcView.adapter = userAdapter

        val observer = Observer<List<UsersModel>> { newValue ->
            userAdapter.submitList(newValue)
        }
        viewModel.liveData.observe(viewLifecycleOwner, observer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}