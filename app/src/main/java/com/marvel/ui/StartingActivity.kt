package com.marvel.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marvel.databinding.ActivityStartingBinding
import com.marvel.ui.fragments.MarvelCharactersFragment

class StartingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityStartingBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}