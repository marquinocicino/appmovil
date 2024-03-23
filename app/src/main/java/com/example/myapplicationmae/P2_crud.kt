package com.example.myapplicationmae

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.delay
import androidx.compose.ui.graphics.Color

@Composable
fun ForTimer(
    duration: Int,
    onPause: () -> Unit = {},
    onReset: () -> Unit = {},
    onComplete: () -> Unit = {}
){
    var timeLeft by remember {
        mutableIntStateOf(duration)
    }

    var isPaused by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = timeLeft) {
        while (timeLeft > 0 && !isPaused){

            delay(1000L)
            timeLeft--
        }
        onComplete()
    }

    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ){
        Text(
            text = "Tiempo espera: ${timeLeft.toString()}",
            modifier = Modifier
                .padding(16.dp),
                fontSize = 20.sp
        )

        Spacer(modifier = Modifier.weight(1f))


        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                isPaused = true
                onPause()
            }) {
            Icon(modifier = Modifier.size(20.dp) ,
                imageVector = Icons.Default.Warning,
                contentDescription = null
            )
            /*Icon(modifier = Modifier.size(20.dp) ,
                imageVector = Icons.Default.Warning,
                contentDescription = null
            )*/
        }



        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                isPaused = false
                timeLeft = duration
                onReset()
            }) {
            Icon(modifier = Modifier.size(20.dp) ,
                imageVector = Icons.Default.Refresh,
                contentDescription = null
            )
        }
    }
}



@Composable
fun RegistrationForm(
    addPerson: (Person) -> Unit
){
    val context = LocalContext.current

    var name by remember {
        mutableStateOf("")
    }
    var age by remember {
        mutableStateOf("")
    }
    Column {
        TextField(value = name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Escriba su nombre") },
            onValueChange = { text ->
                name = text
        })

        TextField(
            value = age.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Escriba su edad")},
            onValueChange = { text ->
                age = text
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                val intAge = age.toIntOrNull()

                if(name.isNotBlank() && intAge != null){
                    //persons = persons + Person (name = name, age = intAge)
                    addPerson(Person(name = name, age = intAge))
                        name = ""
                        age  = ""

                } else {

                    Toast.makeText(context, "Ingrese infomracion valida", Toast.LENGTH_LONG).show()
                }

            }) {

            val color = Color(0xFF000000)
             Icon(imageVector = Icons.Default.Add , contentDescription = "Icono Añadir", tint = color)
        }
    }
}



@Composable
fun ListPerson () {
    var isFormEnabled by remember { mutableStateOf(true) }

    var persons by remember {
        mutableStateOf(listOf<Person>())
    }

    Column (
        modifier = Modifier.fillMaxSize()
    ){

        ForTimer(
            duration = 20,
            onReset = {
                isFormEnabled = true
            },
            onComplete = {
                isFormEnabled = false
            }
        )

        RegistrationForm(addPerson = {capturedPerson ->
                persons += capturedPerson
           }
        )

        LazyColumn {
            items(persons) {currentPerson ->
                Row {
                    Text(
                        text = currentPerson.name,
                        modifier = Modifier.padding(16.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Text(text = currentPerson.age.toString(),
                        modifier = Modifier.padding(16.dp)

                    )
                }
                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonPreview(){
    ListPerson()
}