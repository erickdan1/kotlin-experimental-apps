package com.example.prog3experiments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
//import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge() // Commented out, potentially for edge-to-edge display
        // Set the content of the screen using Jetpack Compose
        setContent {
            ToDoApp2_UI()
        }
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun ToDoApp2_UI() {
        // State variable to hold the user's input for a new to-do item
        var input by remember { mutableStateOf("") }
        // List to store to-do items (initial items: "blah", "bleh")
        // var list: MutableList<String> = remember { mutableListOf("blah", "bleh") } // Not used in current implementation

        // Map to store to-do items and their completion status (initially false)
        var list_map: MutableMap<String, Boolean> = remember { mutableStateMapOf("blah" to false, "bleh" to false) }

        // Main layout using a Column
        Column {
            // LazyColumn to display to-do items efficiently
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) { // Occupies most of the screen height
                // Iterate through the keys of the list_map (to-do items)
                items(list_map.keys.toList()) { item ->
                    Row { // Display each item in a Row
                        Text(text = item) // Display the to-do item text
                        // Checkbox to mark items as complete
                        Checkbox(
                            checked = list_map[item] == true, // Checked if the item is marked as true in the map
                            onCheckedChange = { value -> list_map[item] = value } // Update completion status in the map
                        )
                    }
                }
            }

            // Row for input field and add button
            Row {
                // TextField for user input
                TextField(
                    value = input,
                    onValueChange = { newValue -> input = newValue }, // Update input state
                    label = { Text(text = "todo") } // Label for the TextField
                )

                // Button to add a new to-do item
                Button(onClick = { list_map.put(input, false) }) { // Add item to the map with initial status false
                    Text(text = "+") // Button text
                }
            }
        }
    }
}