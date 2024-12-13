package com.example.firstapplicationp3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstapplicationp3.ui.theme.FirstApplicationP3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            ToDoApp2_UI()
        }
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun ToDoApp2_UI() {

        var input by remember { mutableStateOf("") }
        var list: MutableList<String> = remember { mutableListOf("blah", "bleh") }

        var list_map: MutableMap<String, Boolean> = remember { mutableStateMapOf("blah" to false, "bleh" to false) }

        Column {
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                items(list_map.keys.toList()) {
                    Row {
                        Text(text = it)
                        Checkbox(
                            checked = list_map[it] == true,
                            onCheckedChange = { value -> list_map[it] = value })
                    }
                }
            }

            Row {
                TextField(value = input, onValueChange = { newValue -> input = newValue }, label = { Text(text = "todo") })

                Button(onClick = { list_map.put(input, false) }) {
                    Text(text = "+")
                }
            }
        }
    }
}