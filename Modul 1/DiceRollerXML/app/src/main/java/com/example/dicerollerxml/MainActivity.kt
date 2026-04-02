package com.example.dicerollerxml

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.roll_button)
        val diceImage1: ImageView = findViewById(R.id.dice_image_1)
        val diceImage2: ImageView = findViewById(R.id.dice_image_2)

        rollButton.setOnClickListener {
            rollButton.isEnabled = false

            CoroutineScope(Dispatchers.Main).launch {
                var result1 = 0
                var result2 = 0

                repeat(10) {
                    result1 = (1..6).random()
                    result2 = (1..6).random()

                    val drawableResource1 = when (result1) {
                        1 -> R.drawable.dice_1
                        2 -> R.drawable.dice_2
                        3 -> R.drawable.dice_3
                        4 -> R.drawable.dice_4
                        5 -> R.drawable.dice_5
                        6 -> R.drawable.dice_6
                        else -> R.drawable.dice_0
                    }

                    val drawableResource2 = when (result2) {
                        1 -> R.drawable.dice_1
                        2 -> R.drawable.dice_2
                        3 -> R.drawable.dice_3
                        4 -> R.drawable.dice_4
                        5 -> R.drawable.dice_5
                        6 -> R.drawable.dice_6
                        else -> R.drawable.dice_0
                    }

                    diceImage1.setImageResource(drawableResource1)
                    diceImage2.setImageResource(drawableResource2)

                    delay(100)
                }

                if (result1 == result2) {
                    Toast.makeText(this@MainActivity, getString(R.string.win_message), Toast.LENGTH_SHORT).show()
                    val winPlayer = MediaPlayer.create(this@MainActivity, R.raw.win_sound)
                    winPlayer?.start()
                    winPlayer?.setOnCompletionListener { it.release() }
                } else {
                    Toast.makeText(this@MainActivity, getString(R.string.lose_message), Toast.LENGTH_SHORT).show()
                    val losePlayer = MediaPlayer.create(this@MainActivity, R.raw.lose_sound)
                    losePlayer?.start()
                    losePlayer?.setOnCompletionListener { it.release() }
                }

                rollButton.isEnabled = true
            }
        }
    }
}