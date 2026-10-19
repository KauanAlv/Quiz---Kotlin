package com.example.quiz

data class Pergunta(
    val id: Int,
    val titulo: String,
    val opcoes: List<String>,
    val respostaCorreta: String
)