package com.example.dicerollercompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.media.MediaPlayer


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerApp()
        }
    }
}

@Composable
fun DiceRollerApp() {
    var result1 by remember { mutableIntStateOf(0) }
    var result2 by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val imageResource1 = when (result1) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_0
    }

    val imageResource2 = when (result2) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_0
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Image(
                painter = painterResource(imageResource1),
                contentDescription = stringResource(R.string.dice_1_desc),
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(imageResource2),
                contentDescription = stringResource(R.string.dice_2_desc),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                repeat(10){
                    result1 = (1..6).random()
                    result2 =  (1..6).random()
                    delay(100)
                }

                if (result1 == result2) {
                    Toast.makeText(context, context.getString(R.string.win_message), Toast.LENGTH_SHORT).show()
                    val winPlayer = MediaPlayer.create(context, R.raw.win_sound)
                    winPlayer?.start()
                    winPlayer.setOnCompletionListener { it.release() }
                } else {
                    Toast.makeText(context, context.getString(R.string.lose_message), Toast.LENGTH_SHORT).show()
                    val winPlayer = MediaPlayer.create(context, R.raw.lose_sound)
                    winPlayer?.start()
                    winPlayer.setOnCompletionListener { it.release() }
                }
            }
        }) {
            Text(stringResource(R.string.roll_button))
        }
    }
}