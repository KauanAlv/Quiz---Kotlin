package com.example.quiz.quiz.SegundaPergunta

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quiz.R
import com.example.quiz.quiz.PrimeiraPergunta.PrimeiraPerguntaConteudo

@Composable
fun SegundaPerguntaScreen(
    modifier: Modifier = Modifier,
    segundaPerguntaViewModel: SegundaPerguntaViewModel = viewModel(),
    nome: String
) {

    val respostaSelecionada by segundaPerguntaViewModel.respostaSelecionada.observeAsState()

    val acertou by segundaPerguntaViewModel.acertou.observeAsState()

    PrimeiraPerguntaConteudo(
        numeroPergunta = segundaPerguntaViewModel.numeroPergunta,
        totalPerguntas = segundaPerguntaViewModel.totalPerguntas,
        enunciado = segundaPerguntaViewModel.enunciado,
        alternativas = segundaPerguntaViewModel.alternativas,
        respostaSelecionada = respostaSelecionada,
        acertou = acertou,
        onRespostaSelecionada = {
            segundaPerguntaViewModel.onRespostaSelecionada(it)
        },
        modifier = modifier
    )
}

@Composable
fun SegundaPerguntaConteudo(
    numeroPergunta: Int,
    totalPerguntas: Int,
    enunciado: String,
    alternativas: List<String>,
    respostaSelecionada: String?,
    acertou: Boolean?,
    onRespostaSelecionada: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF86E7F8))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.quiz),
            contentDescription = "Logo do Quiz",
            modifier = Modifier.size(90.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Indicador da pergunta
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
                text = "Pergunta ${numeroPergunta} de ${totalPerguntas}",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Text(
                    text = enunciado,
                    fontSize = 24.sp,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    alternativas.forEach { alternativa ->

                        val selecionada = alternativa == respostaSelecionada

                        OutlinedButton(
                            onClick = {
                                onRespostaSelecionada(alternativa)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(
                                width = if (selecionada) 2.dp else 1.dp,
                                color = if (selecionada) {
                                    Color(0xFF2196F3)
                                } else {
                                    Color(0xFFD3D3D3)
                                }
                            ),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (selecionada) {
                                    Color(0xFFE3F2FD)
                                } else {
                                    Color.White
                                },
                                contentColor = Color.Black
                            ),
                            contentPadding = PaddingValues(
                                horizontal = 16.dp
                            )
                        ) {
                            Text(
                                text = alternativa,
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 20.sp
                            )
                        }
                    }
                }

                if (acertou != null) {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = if (acertou) {
                            "Resposta correta!"
                        } else {
                            "Resposta incorreta. Tente novamente!"
                        },
                        color = if (acertou) {
                            Color(0xFF2E7D32)
                        } else {
                            Color(0xFFC62828)
                        },
                        fontSize = 18.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}