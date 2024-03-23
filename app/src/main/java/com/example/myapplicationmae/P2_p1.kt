package com.example.myapplicationmae

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf

@Composable
fun TapCounter() {

    var counter by remember{
        mutableIntStateOf(0)
    }

    Column(
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally,
         modifier = Modifier
             .fillMaxSize()
    ) {

        Text(text = counter.toString(),
            fontSize = 30.sp
            )


        Button(onClick = {
            counter ++

        }) {
            Icon(imageVector = Icons.Default.Build,
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
            )
            Spacer(modifier  = Modifier
                .size(8.dp)
            )
            Text(text = "TAP ME!")
        }

        Button(onClick = {
            counter = 0

        }) {
            Icon(imageVector = Icons.Default.Refresh,
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
            )
            Spacer(modifier  = Modifier
                .size(8.dp)
            )
            Text(text = "RESET COUNTER!")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun TapPreview() {
    TapCounter()
}