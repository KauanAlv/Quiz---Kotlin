package com.example.quiz.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CaixaDeEntrada (
    modifier: Modifier,
    label: String,
    placeholder: String,
    singleLine: Boolean,
    colors: TextFieldColors,
    keyboardType: KeyboardType,
    value: String,
    atualizarValor: (String) -> Unit
) {

    OutlinedTextField(
        modifier = modifier,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        singleLine = singleLine,
        colors = colors,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        value = value,
        onValueChange = {
            if (it.all { caractere ->
                    caractere.isLetter() || caractere == ' '
                }) {
                atualizarValor(it)
            }
        }
    )
}