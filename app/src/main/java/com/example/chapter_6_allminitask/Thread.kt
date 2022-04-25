package com.example.chapter_6_allminitask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapter_6_allminitask.databinding.ActivityThreadBinding
import java.lang.Thread

class Thread : AppCompatActivity() {
    private lateinit var binding:ActivityThreadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            val stringTime = binding.et1.text.toString()
            val intTime = Integer.parseInt(stringTime)
            Thread(Runnable {
                for (i in intTime downTo 0){
                    runOnUiThread {
                        binding.tv1.text = i.toString()
                    }
                    Thread.sleep(1000)
                }
            }).start()
        }


    }
}