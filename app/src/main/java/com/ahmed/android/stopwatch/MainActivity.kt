package com.ahmed.android.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.ahmed.android.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private var seconds = 0
    private var running = false
    private var wasRunning = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        runTimer()

        binding.startButton.setOnClickListener {
            running = true
        }
        binding.stopButton.setOnClickListener {
            running = false
        }
        binding.resetButton.setOnClickListener {
            running = false
            seconds = 0
        }
    }

    private fun runTimer() {
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60
                val time = String.format("%d:%02d:%02d", hours, minutes, secs)
                binding.timeView.text = time
                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }
}