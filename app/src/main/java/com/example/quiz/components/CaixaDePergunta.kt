package com.example.quiz.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val VerdeCorreta = Color(0xFF4CAF50)
private val VermelhoErrada = Color(0xFFE53935)
private val BordaPadrao = Color(0xFFD3D3D3)

@Composable
fun CardPergunta(
    pergunta: String,
    opcoes: List<String>,
    respostaCorreta: String,
    opcaoSelecionada: String?,
    onOpcaoSelecionada: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val respondeu = opcaoSelecionada != null

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Text(
                text = pergunta,
                fontSize = 24.sp,
                lineHeight = 30.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                opcoes.forEach { opcao ->
                    // Azul = correta | Vermelho = errada escolhida | Branco = demais
                    val corFundo = when {
                        !respondeu -> Color.White
                        opcao == respostaCorreta -> VerdeCorreta
                        opcao == opcaoSelecionada -> VermelhoErrada
                        else -> Color.White
                    }
                    val corBotao = when {
                        opcaoSelecionada == null -> MaterialTheme.colorScheme.surface // Cor normal

                        opcao == opcaoSelecionada -> Color(0xFFE53935) // Vermelho se escolheu a errada
                        else -> MaterialTheme.colorScheme.surface
                    }
                    val colorida = corFundo != Color.White

                    OutlinedButton(
                        onClick = {
                            if (!respondeu) onOpcaoSelecionada(opcao)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(
                            width = if (colorida) 2.dp else 1.dp,
                            color = if (colorida) corFundo else BordaPadrao
                        ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = corFundo,
                            contentColor = if (colorida) Color.White else Color.Black
                        ),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        Text(
                            text = opcao,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}
