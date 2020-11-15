package com.example.assignment.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.assignment.R
import com.example.assignment.ui.MainActivity
import com.example.assignment.ui.NewsViewModel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment: Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        val articleDetails = args.result
        webView.apply {
           webViewClient = WebViewClient()
           loadUrl(articleDetails.url)
        }
        fab.setOnClickListener {
            viewModel.saveNews(articleDetails)
            Snackbar.make(view,"News saved !!", Snackbar.LENGTH_SHORT).show()
        }
    }
}