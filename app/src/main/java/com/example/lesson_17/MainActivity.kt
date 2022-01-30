package com.example.lesson_17

import android.content.Context
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
                startCountDownTimer((edTimer.text.toString().toLong() + 1) * 1000)
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
                vibro(this)
                Toast.makeText(this,"Таймер аля уля!",Toast.LENGTH_LONG).show()
                Log.d("MyLog","вибрацияяяЯЯЯ!!")
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
                vibro(this@MainActivity)
            }

        }.start()
    }

    companion object {
        fun vibro(context: Context) {
            // Kotlin
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            val canVibrate: Boolean = vibrator.hasVibrator()
            val milliseconds = 1000L

            if (canVibrate) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // API 26
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            milliseconds,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    // This method was deprecated in API level 26
                    vibrator.vibrate(milliseconds)
                }
            }
        }
    }
}