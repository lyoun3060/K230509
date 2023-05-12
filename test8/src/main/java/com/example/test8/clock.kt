package com.example.test8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.test8.databinding.ActivityClockBinding

class clock : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_clock)
        var binding = ActivityClockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var initTime = 0L
        var pauseTime = 0L

        //이벤트 핸들러 부분. 방법 3
        binding.btnStart.setOnClickListener{
            binding.chronometer.base = SystemClock.elapsedRealtime()+pauseTime
            binding.chronometer.start()
            binding.btnStop.isEnabled = true
            binding.btnReset.isEnabled = true
            binding.btnStart.isEnabled = false
        }

        binding.btnStop.setOnClickListener{
        pauseTime=binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.btnStop.isEnabled = false
            binding.btnReset.isEnabled = true
            binding.btnStart.isEnabled = true

        }

        binding.btnReset.setOnClickListener{
            pauseTime = 0L
            binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.btnStop.isEnabled = false
            binding.btnReset.isEnabled = false
            binding.btnStart.isEnabled = true

        }
    }
}