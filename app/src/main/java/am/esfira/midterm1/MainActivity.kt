package am.esfira.midterm1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import am.esfira.midterm1.ui.theme.Midterm1Theme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Midterm1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                }
            }
        }
    }
}
@Composable
fun BullGame() {
    var targetValue by remember { mutableStateOf((0..100).random()) }
    var playerGuess by remember { mutableStateOf(0) }
    var playerScore by remember { mutableStateOf(0) }
    var feedback by remember { mutableStateOf("") }
    var sliderValue by remember { mutableStateOf(50) }
    var minRange by remember { mutableStateOf(1) }
    var maxRange by remember { mutableStateOf(100) }

    fun calculateScore(targetValue: Int, playerGuess: Int) {
        val difference = targetValue - playerGuess
        when {
            difference in -3..3 -> {
                feedback = "Perfect! You scored 5 points."
                playerScore += 5
            }
            difference in -8..8 -> {
                feedback = "Good! You scored 1 point."
                playerScore += 1
            }
            else -> {
                feedback = "Try again!"
            }
        }
        //playerGuess.value = 50
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Welcome to the Bull's Eye Game!", fontSize = 24.sp )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Move the slider as close as you can to $targetValue")
        Spacer(modifier = Modifier.height(16.dp))


        Slider (
            value = sliderValue.toFloat(),
            onValueChange = { newValue -> sliderValue = newValue.toInt() },
            valueRange = minRange.toFloat() .. maxRange.toFloat()
        )

        Button (
            onClick = {
                println(sliderValue)
                println(calculateScore(targetValue,playerGuess)) },

            ){
            Text("Hit me")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Your score: $playerScore")

        Spacer(modifier = Modifier.height(36.dp))


    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BullGame()
}