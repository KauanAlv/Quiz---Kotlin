package com.example.quiz.quiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz.Pergunta
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class QuizUiState(
    val indice: Int = 0,
    val total: Int = 0,
    val perguntaAtual: Pergunta,
    val opcoes: List<String> = emptyList(),
    val opcaoSelecionada: String? = null,
    val pontos: Int = 0,
    val finalizado: Boolean = false
)

class QuizViewModel : ViewModel() {

    private val perguntas = listOf(
        Pergunta(
            id = 1,
            titulo = "Quantas patas tem um cachorro?",
            opcoes = listOf("1", "2", "3", "4"),
            respostaCorreta = "4"
        ),
        Pergunta(
            id = 2,
            titulo = "Qual é a capital do Brasil?",
            opcoes = listOf("São Paulo", "Brasília", "Rio de Janeiro", "Bahia"),
            respostaCorreta = "Brasília"
        ),
        Pergunta(
            id = 3,
            titulo = "Quem foi o primeiro homem a pisar na Lua?",
            opcoes = listOf("Neil Armstrong", "Buzz Aldrin", "Yuri Gagarin", "Alan Shepard"),
            respostaCorreta = "Neil Armstrong"
        ),
        Pergunta(
            id = 4,
            titulo = "Quando o Brasil foi descoberto?",
            opcoes = listOf("1805", "1950", "1500", "1450"),
            respostaCorreta = "1500"
        )
    ).shuffled()

    var uiState by mutableStateOf(estadoDaPergunta(indice = 0, pontos = 0))
        private set

    private fun estadoDaPergunta(indice: Int, pontos: Int): QuizUiState {
        val pergunta = perguntas[indice]
        return QuizUiState(
            indice = indice,
            total = perguntas.size,
            perguntaAtual = pergunta,
            opcoes = pergunta.opcoes.shuffled(), // embaralha UMA vez por pergunta
            pontos = pontos
        )
    }

    fun selecionarOpcao(opcao: String) {
        val estado = uiState
        if (estado.opcaoSelecionada != null || estado.finalizado) return

        val acertou = opcao == estado.perguntaAtual.respostaCorreta
        val novosPontos = if (acertou) estado.pontos + 1 else estado.pontos

        uiState = estado.copy(opcaoSelecionada = opcao, pontos = novosPontos)

        // Espera 1s para o usuário ver verde/vermelho e então avança
        viewModelScope.launch {
            delay(1000L)
            avancar()
        }
    }

    private fun avancar() {
        val estado = uiState
        uiState = if (estado.indice < perguntas.size - 1) {
            estadoDaPergunta(estado.indice + 1, estado.pontos)
        } else {
            estado.copy(opcaoSelecionada = null, finalizado = true)
        }
    }
}
