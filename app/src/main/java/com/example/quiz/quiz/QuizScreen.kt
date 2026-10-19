package com.example.quiz.quiz

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
import com.example.quiz.R
import com.example.quiz.components.CardPergunta
import com.example.quiz.quiz.QuizViewModel


@Composable
fun QuizScreen(
    nomeJogador: String,
    modifier: Modifier = Modifier,
    quizViewModel: QuizViewModel = viewModel()
) {
    val estado = quizViewModel.uiState

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.pink_white))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!estado.finalizado) {
            Image(
                modifier = Modifier.size(70.dp),
                painter = painterResource(R.drawable.quiz),
                contentDescription = "logo quiz"
            )

            Spacer(modifier = Modifier.height(16.dp))

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

            Spacer(modifier = Modifier.height(16.dp))

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
                total = estado.total
            )
        }
    }
}

@Composable
private fun ResultadoContent(nomeJogador: String, pontos: Int, total: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(70.dp),
            painter = painterResource(R.drawable.quiz),
            contentDescription = "logo quiz"
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(color = colorResource(R.color.blue_white)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .width(250.dp)
                    .height(60.dp)
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
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Você acertou $pontos de $total perguntas",
                fontSize = 20.sp
            )
        }
    }
}
