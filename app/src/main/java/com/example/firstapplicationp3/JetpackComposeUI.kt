package com.example.firstapplicationp3

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstapplicationp3.ui.theme.FirstApplicationP3Theme

class JetpackComposeUI : ComponentActivity() {
    val LOG: String = "JetpackComposeUI"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyComposable()
        }
    }

    fun F2C(f: Int): Float {
        return ((f - 32) * 5/9).toFloat()
    }

    fun C2F(c: Int): Float {
        return ((c * 9/5) + 32).toFloat()
    }

    @SuppressLint("DefaultLocale")
    @Preview(showBackground = true)
    @Composable
    fun MyComposable() {

        var temp by remember { mutableStateOf("") }
        var result by remember { mutableStateOf(0f) }
        var isFahrenheit by remember { mutableStateOf(false) }

        Column(modifier = Modifier.fillMaxWidth()) {

            // TextField for user input
            TextField(
                value = temp,
                onValueChange = { it -> temp = it},
                label = { Text("Digite temperatura")}
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "C")
                Switch(checked = isFahrenheit, onCheckedChange = { it -> isFahrenheit = it} )
                Text(text = "F")

            }

            Button(onClick = {
                result = if (isFahrenheit) {
                    F2C(temp.toInt())
                } else {
                    C2F(temp.toInt())
                }
                Log.d(LOG, "button click " + result)
            }
            ) {
                Text(text = "convert")
            }

            Text(text = String.format("%.2f", result))
        }
    }
}
