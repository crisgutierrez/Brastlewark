package com.example.brastlewark.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.brastlewark.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}