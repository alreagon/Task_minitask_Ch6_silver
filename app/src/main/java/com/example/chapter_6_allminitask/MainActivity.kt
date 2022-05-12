package com.example.chapter_6_allminitask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
//import com.example.chapter_6_allminitask.dataStore.DataStoreMain
import com.example.chapter_6_allminitask.databinding.ActivityMainBinding
import com.example.chapter_6_allminitask.service.ForegroundServiceMain

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


        binding.nyoba.setOnClickListener {
            val Intent = Intent(this, Thread::class.java)
            startActivity(Intent)
        }
        binding.nyoba.setOnLongClickListener {
            Toast.makeText(this, "Nyoba Thread", Toast.LENGTH_SHORT).show()
            true
        }
        binding.topic1.setOnClickListener {
            val Intent = Intent(this, Thread2::class.java)
            startActivity(Intent)
        }
        binding.topic1.setOnLongClickListener {
            Toast.makeText(this, "Android Background Process: Thread", Toast.LENGTH_SHORT).show()
            true
        }
        binding.topic12.setOnClickListener {
            val Intent = Intent(this, ForegroundServiceMain::class.java)
            startActivity(Intent)
        }
        binding.topic12.setOnLongClickListener {
            Toast.makeText(this, "Android Background Process: Service dari internet", Toast.LENGTH_SHORT).show()
            true
        }
//        binding.topic3.setOnClickListener {
//            val Intent = Intent(this, DataStoreMain::class.java)
//            startActivity(Intent)
//        }
        binding.topic3.setOnLongClickListener {
            Toast.makeText(this, "DataStore", Toast.LENGTH_SHORT).show()
            true
        }
    }
}