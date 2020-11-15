package com.example.assignment.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.R
import com.example.assignment.adapters.NewsAdapater
import com.example.assignment.ui.MainActivity
import com.example.assignment.ui.NewsViewModel.NewsViewModel
import com.example.assignment.util.Resource
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment: Fragment(R.layout.fragment_news) {

    lateinit var  viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapater

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("result", it)
            }
            findNavController().navigate(
                R.id.action_newsFragment_to_articleFragment, bundle
            )
        }

        viewModel.news.observe(viewLifecycleOwner, Observer { response->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let{newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.results)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {messagge->
                        Toast.makeText(activity, "Error $messagge",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }


    private fun setupRecyclerView() {
        newsAdapter = NewsAdapater()
        rvBreakingNews.apply{
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}