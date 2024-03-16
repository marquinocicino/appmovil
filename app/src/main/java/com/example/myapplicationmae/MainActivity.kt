package com.example.myapplicationmae

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationmae.ui.theme.MyApplicationMaeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationMaeTheme {
                // A surface container using the 'background' color from the theme
                Surface(  // contenedor elementos
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

// modifier mutar decorar alinear  espacios
// compose crealas intefaces usando componentes funciones
@Composable
fun Greeting(name: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Hello $name!",
            color= Color.Blue,
            fontSize = 40.sp
        )

        Text(
            text = "OTRO TEXTO",
            color= Color.Green,
            fontSize = 40.sp
        )
    }

}

@Composable
fun BoxDemo(){
    Box(
        modifier = Modifier
            .size(400.dp)
    ){
        Text(
            text = "Segundo compose",
            color = Color.Cyan,
            fontSize = 40.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)

        )
        Text(
            text = "Texto 2",
            color = Color.Cyan,
            fontSize = 40.sp,
            modifier = Modifier
                .align(Alignment.CenterStart)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationMaeTheme {
        Greeting("Android Mae hw a los tiempos")
    }
}