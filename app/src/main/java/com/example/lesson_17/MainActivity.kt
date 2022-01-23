package com.example.lesson_17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import com.example.lesson_17.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.apply {
            btnStart.setOnClickListener {
                startCountDownTimer((edTimer.text.toString().toLong()+1) * 1000)
                binding.tvTimer.text = ""
                binding.checkBox.isChecked = false
                binding.checkBox.visibility = View.GONE
                binding.btnReset.text = "Reset"
            }
        }

        binding.btnPause.setOnClickListener {
            timer?.cancel()

        }

        binding.btnReset.setOnClickListener {
            binding.checkBox.visibility = View.VISIBLE
            binding.tvTimer.text = "Вы уверены, что хотите сбросить таймер?"
            binding.btnReset.text = "Ok"
            if (binding.checkBox.isChecked) {
                timer?.cancel()
                binding.edTimer.setText("0")
                binding.tvTimer.text = "Finish"
                binding.btnReset.text = "Reset"
                binding.checkBox.isChecked = false
                binding.checkBox.visibility = View.GONE
            }
        }
    }

    private fun startCountDownTimer(timeMillis: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(timeMillis, 1) {
            override fun onTick(millisUntilFinished: Long) {
                binding.edTimer.setText((millisUntilFinished / 1000).toString())

            }

            override fun onFinish() {
                binding.tvTimer.text = "Finish"
            }

        }.start()
    }
}