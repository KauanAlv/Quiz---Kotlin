package com.example.quiz.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quiz.R
import com.example.quiz.components.CaixaDeEntrada
import com.example.quiz.home.HomeViewModel

@Composable
fun DadosScreen(
    homeViewModel: HomeViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colorResource(R.color.blue_white)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(80.dp),
            painter = painterResource(
                R.drawable.quiz
            ),
            contentDescription = "logo quiz"
        )

        Spacer(modifier = Modifier.height(50.dp))


        CaixaDeEntrada(
            modifier = Modifier,
            label = "Nome do Jogador",
            placeholder = "Digite seu nome de jogador",
            keyboardType = KeyboardType.Text,
            value = homeViewModel.nomeJogador,
            atualizarValor = { homeViewModel.atualizarNome(it) },
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                if (homeViewModel.nomeValido) {
                    navController.navigate("quiz")
                }
            },
            modifier = Modifier
                .height(80.dp)
                .width(250.dp)
                .padding(10.dp),
            enabled = homeViewModel.nomeValido,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.yellow_white),
                disabledContainerColor = Color.Transparent
            ),
            border = BorderStroke(
                width = 2.dp,
                color = if (homeViewModel.nomeValido) colorResource(R.color.black)
                else colorResource(R.color.black).copy(alpha = 0.3f)
            )
        ) {
            Text(
                text = "JOGAR!",
                color = if (homeViewModel.nomeValido) colorResource(R.color.black)
                else colorResource(R.color.black).copy(alpha = 0.3f),
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }

    }
}