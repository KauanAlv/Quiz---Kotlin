package com.example.quiz.quiz.SegundaPergunta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SegundaPerguntaViewModel : ViewModel() {

    val numeroPergunta = 2
    val totalPerguntas = 3

    val enunciado = "De onde é a invenção do chuveiro elétrico?"


    val alternativas = listOf(
        "França",
        "Inglaterra",
        "Brasil",
        "Austrália"
    )

    private val respostaCorreta = "Brasil"

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