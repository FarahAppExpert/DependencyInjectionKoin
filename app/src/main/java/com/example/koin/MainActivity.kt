package com.example.koin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.koin.viewmodel.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity()
{
    private val userViewModel by viewModel<ViewModel>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel.data.observe(this, Observer {
            // Populate the UI
        })

        userViewModel.loadingState.observe(this, Observer {
            // Observe the loading state
        })
    }
    }
