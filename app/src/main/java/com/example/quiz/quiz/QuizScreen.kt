package com.example.quiz.quiz

import android.R.attr.fontWeight
import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quiz.R
import com.example.quiz.components.CardPergunta
import com.example.quiz.home.HomeViewModel
import com.example.quiz.quiz.QuizViewModel


@Composable
fun QuizScreen(
    nomeJogador: String,
    navController: NavController,
    modifier: Modifier = Modifier,
    quizViewModel: QuizViewModel = viewModel()
) {
    val estado = quizViewModel.estadoGeral

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.blue_white))
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!estado.finalizado) {
            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(R.drawable.quiz),
                contentDescription = "logo quiz"
            )

            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.82f)
                    .background(
                        color = Color(0xFF6DD5A3),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xFF555555),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Pergunta ${estado.indice + 1} de ${estado.total}",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            CardPergunta(
                pergunta = estado.perguntaAtual.titulo,
                opcoes = estado.opcoes,
                respostaCorreta = estado.perguntaAtual.respostaCorreta,
                opcaoSelecionada = estado.opcaoSelecionada,
                onOpcaoSelecionada = { quizViewModel.selecionarOpcao(it) }
            )
        } else {
            ResultadoContent(
                nomeJogador = nomeJogador,
                pontos = estado.pontos,
                total = estado.total,
                navController = navController
            )
        }
    }
}

@Composable
private fun ResultadoContent(
    nomeJogador: String,
    pontos: Int,
    total: Int,
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(R.drawable.quiz),
            contentDescription = "logo quiz"
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .width(330.dp)
                    .height(70.dp)
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Color(0xFF62DB96)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Bom trabalho, $nomeJogador!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Você acertou $pontos de $total perguntas",
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.height(50.dp))

            BotaoVoltar(
                onClick = {
                    navController.navigate("home") {
                        popUpTo("home") {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
fun BotaoVoltar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(80.dp)
            .width(250.dp)
            .padding(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.teal_200)
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 2.dp,
            color = Color.Gray
        )
    ) {
        Text(
            text = "TENTAR NOVAMENTE",
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
    }
}
