package com.example.brastlewark.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.brastlewark.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        
        supportFragmentManager.fragmentFactory = fragmentFactory
    }
}