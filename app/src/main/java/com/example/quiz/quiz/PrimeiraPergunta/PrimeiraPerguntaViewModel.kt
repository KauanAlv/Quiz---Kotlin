package com.example.quiz.quiz.PrimeiraPergunta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PrimeiraPerguntaViewModel : ViewModel() {

    val numeroPergunta = 1
    val totalPerguntas = 3

    val enunciado = "Em que ano o Brasil foi descoberto?"


    val alternativas = listOf(
        "1515",
        "1500",
        "1490",
        "1822"
    )

    private val respostaCorreta = "1500"

    private val _respostaSelecionada = MutableLiveData<String?>(null)
    val respostaSelecionada: LiveData<String?> = _respostaSelecionada

    private val _acertou = MutableLiveData<Boolean?>(null)
    val acertou: LiveData<Boolean?> = _acertou

    fun onRespostaSelecionada(resposta: String) {
        if (_respostaSelecionada.value != null) return
        if (resposta !in alternativas) return

        _acertou.value = resposta == respostaCorreta
        _respostaSelecionada.value = resposta
    }
}