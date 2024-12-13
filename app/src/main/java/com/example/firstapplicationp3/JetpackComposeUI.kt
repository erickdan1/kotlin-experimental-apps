package com.example.firstapplicationp3

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstapplicationp3.ui.theme.FirstApplicationP3Theme

class JetpackComposeUI : ComponentActivity() {
    private val TAG = "JetpackComposeUI"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TemperatureConverterScreen()
        }
    }

    private fun fahrenheitToCelsius(f: Float): Float {
        return (f - 32) * 5f / 9f
    }

    private fun celsiusToFahrenheit(c: Float): Float {
        return (c * 9f / 5f) + 32f
    }

    @SuppressLint("DefaultLocale")
    @Preview(showBackground = true)
    @Composable
    fun TemperatureConverterScreen() {
        var temperature by remember { mutableStateOf(0f) }
        var result by remember { mutableStateOf(0f) }
        var isFahrenheit by remember { mutableStateOf(false) }

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {

            TextField(
                value = temperature.toString(),
                onValueChange = { newTemp ->
                    temperature = newTemp.toFloatOrNull() ?: temperature
                },
                label = { Text("Digite temperatura") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "C")
                Switch(checked = isFahrenheit, onCheckedChange = { checked -> isFahrenheit = checked })
                Text(text = "F")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                result = if (isFahrenheit) {
                    fahrenheitToCelsius(temperature)
                } else {
                    celsiusToFahrenheit(temperature)
                }
                Log.d(TAG, "button click $result")
            }) {
                Text(text = "convert")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = String.format("%.2f", result))
        }
    }
}