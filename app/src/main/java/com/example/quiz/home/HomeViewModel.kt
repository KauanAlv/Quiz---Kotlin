package com.example.quiz.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    var nomeJogador by mutableStateOf("")
        private set

    val nomeValido: Boolean
        get() = nomeJogador.isNotBlank()

    fun atualizarNome(novoNome: String) {
        nomeJogador = novoNome
    }

    fun nomeFormatado(): String = nomeJogador.trim()
}
