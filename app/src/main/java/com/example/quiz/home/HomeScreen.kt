package com.example.quiz.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quiz.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colorResource(R.color.blue_white)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(130.dp),
                painter = painterResource(
                    R.drawable.quiz
                ),
                contentDescription = "logo quiz"
            )
        }

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "QUIZATRON 3000",
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = { navController.navigate("dados") },
            modifier = Modifier
                .height(80.dp)
                .width(300.dp)
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.yellow_white)),
            border = BorderStroke(
                width = 2.dp,
                color = colorResource(R.color.black)
            )
        ) {
            Text(
                text = "COMEÇAR!",
                color = colorResource(R.color.black),
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }

    }
}