package com.example.quiz.home

import android.R.id.home
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quiz.R

@Composable
fun TelaInicial(modifier: Modifier = Modifier, homeScreenViewModel: HomeScreenViewModel, navController: NavController) {

    val nome by homeScreenViewModel.nome.observeAsState(initial = "")
    val nomeValido by homeScreenViewModel.nomeValido.observeAsState(initial = false)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF82D8F7))
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        //     --- Imagem ---
        Image(
            painter = painterResource(R.drawable.quiz),
            contentDescription = "Logo do Quiz",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "QUIZATRON 3000",
            fontSize = 35.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Recebe o nome
        OutlinedTextField(
            value = nome,
            onValueChange = { homeScreenViewModel.onNomeChanged(it) },
            singleLine = true,
            modifier = Modifier.width(300.dp),
            placeholder = {
                Text(text = "Digite seu nome")
            },
            shape = RoundedCornerShape(
                size = 10.dp
            ),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Blue
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = { navController.navigate("primeira_pergunta/$nome") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(300.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                Color(0xFFF9D658),
                disabledContainerColor = Color(0x00404040)
            ),
            enabled = nomeValido

        ) {
            Text(
                text = "COMEÇAR!",
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }
}