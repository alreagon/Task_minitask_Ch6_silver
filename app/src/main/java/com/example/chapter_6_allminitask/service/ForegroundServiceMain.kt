package com.example.chapter_6_allminitask.service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.chapter_6_allminitask.databinding.ActivityForegroundServiceMainBinding

class ForegroundServiceMain : AppCompatActivity() {
    lateinit var binding: ActivityForegroundServiceMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForegroundServiceMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonStart.setOnClickListener {
            ForegroundService.startService(this, "Foreground Service is running...")
        }
        binding.buttonStop.setOnClickListener {
            ForegroundService.stopService(this)
        }
    }
}
