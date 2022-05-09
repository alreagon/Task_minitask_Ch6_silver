package com.example.chapter_6_allminitask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.example.chapter_6_allminitask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.biruMantep)


        binding.topic1.setOnClickListener {
            val Intent = Intent(this, Thread::class.java)
            startActivity(Intent)
        }
        binding.topic1.setOnLongClickListener {
            Toast.makeText(this, "Android Background Process: Thread", Toast.LENGTH_SHORT).show()
            true
        }
        binding.topic3.setOnClickListener {
            val Intent = Intent(this, Thread::class.java)
            startActivity(Intent)
        }
        binding.topic3.setOnLongClickListener {
            Toast.makeText(this, "DataStore", Toast.LENGTH_SHORT).show()
            true
        }
    }
}