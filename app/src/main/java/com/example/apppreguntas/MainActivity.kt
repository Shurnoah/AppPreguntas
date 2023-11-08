package com.example.apppreguntas

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.RemoteInput.Source
import androidx.media3.extractor.mp4.Track
import com.example.apppreguntas.ui.theme.AppPreguntasTheme
import com.example.apppreguntas.ui.theme.Preguntas
import java.util.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppPreguntasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BotonContar()
                }
            }
        }
    }
}

@Composable
fun BotonContar() {

    var textoCabecera by remember { mutableStateOf("") }
    var textoRespuesta by remember { mutableStateOf("") }

    var colorBotonFalso by remember { mutableStateOf(R.color.ButtonBlue) }
    var colorBotonVerdadero by remember { mutableStateOf(R.color.ButtonBlue) }

    var actual by remember { mutableStateOf(0) }



 // lista preguntas
    var lista = ArrayList<Preguntas>()
    lista.add(
        Preguntas(
            "¿Es este animal un conejo?",
            R.drawable.conejo,
            true,
            "Si, es un conejo",
            "No, es un conejo"
        )
    )
    lista.add(
        Preguntas(
            "¿Es ese animal un perro?",
            R.drawable.zorro,
            false,
            "No, es un zorrito",
            "Si, es un zorro preocupado"
        )
    )
    lista.add(
        Preguntas(
            "¿Es ese animal un gato?",
            R.drawable.zorro,
            false,
            "No, es un zorrito",
            "Si, es un zorro preocupado"
        )
    )



    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.BackgroundYellow))
            .padding(8.dp)
    ) {



//Cabecera Pregunta
        Text(
            modifier = Modifier
                .weight(2f)
                .fillMaxSize()
                .wrapContentSize(Center),
            lineHeight = 50.sp,
            text = if (actual == 0)
                lista.get(actual).pregunta
            else lista.get(actual).pregunta,

            textAlign = TextAlign.Center,
            fontSize = 50.sp,
            color = colorResource(id = R.color.TexGreen)

        )



//Imagen
        Image(
            painter = painterResource(id = lista.get(actual).imagen),
            contentDescription = "Imagen a identificar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .weight(4f)
                .size(500.dp)
                .border(5.dp, colorResource(id = R.color.ButtonBlue))
                .align(CenterHorizontally)
                .fillMaxSize()
        )



//Respuesta Pregunta
        Text(
            modifier = Modifier
                .weight(2f)
                .fillMaxSize()
                .wrapContentSize(Center),
            lineHeight = 50.sp,
            text = textoRespuesta,

            textAlign = TextAlign.Center,
            fontSize = 50.sp,
            color = colorResource(id = R.color.TexGreen)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {


//Boton Verdadero
            TextButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
                    .fillMaxSize(),
                colors = ButtonDefaults.outlinedButtonColors(colorResource(id =  colorBotonVerdadero)),
                onClick = {
                    textoRespuesta = lista.get(actual).Rverdadero
                    if (lista.get(actual).respuesta){
                        colorBotonVerdadero = R.color.CorrectAnswer
                        colorBotonFalso = R.color.IncorrectAnswe
                    } else {
                        colorBotonVerdadero = R.color.IncorrectAnswe
                        colorBotonFalso = R.color.CorrectAnswer
                    }
                }

            ) {
                Text(
                    text = "Verdadero",
                    color = colorResource(id = R.color.BackgroundYellow),
                    fontSize = 20.sp
                )
            }


//Boton Falso
            TextButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
                    .fillMaxSize(),
                onClick = {
                        textoRespuesta = lista.get(actual).RFalso
                        if (!lista.get(actual).respuesta){
                            colorBotonVerdadero = R.color.IncorrectAnswe
                            colorBotonFalso = R.color.CorrectAnswer
                        } else {
                            colorBotonVerdadero = R.color.CorrectAnswer
                            colorBotonFalso = R.color.IncorrectAnswe
                        }
                          },
                colors = ButtonDefaults.outlinedButtonColors(colorResource(id = colorBotonFalso))
            ) {
                Text(
                    text = "Falso",
                    color = colorResource(id = R.color.BackgroundYellow),
                    fontSize = 20.sp
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {


//Boton Anterior
            TextButton(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .weight(1f),
                onClick = {
                    if (actual == 0) {
                        actual = lista.lastIndex
                    } else {
                        actual--
                    }
                    colorBotonVerdadero = R.color.ButtonBlue
                    colorBotonFalso = R.color.ButtonBlue
                    textoRespuesta = ""
                },
                colors = ButtonDefaults.outlinedButtonColors(colorResource(id = R.color.ButtonBlue)),
            ) {
                Row() {
                    Text(
                        text = "Anterior  ",
                        color = colorResource(id = R.color.BackgroundYellow),
                        fontSize = 20.sp
                    )
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "flecha",
                        tint = colorResource(id = R.color.BackgroundYellow)
                    )

                }
            }


//Boton Random
            TextButton(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .weight(1f),
                onClick = {
                    actual = Random().nextInt(lista.lastIndex + 1)

                    colorBotonVerdadero = R.color.ButtonBlue
                    colorBotonFalso = R.color.ButtonBlue
                    textoRespuesta = ""
                },
                colors = ButtonDefaults.outlinedButtonColors(colorResource(id = R.color.ButtonBlue)),
            ) {
                Row() {
                    Icon(
                        painter = painterResource(id = R.drawable.casino),
                        contentDescription = "Dado",
                        tint = colorResource(id = R.color.BackgroundYellow)
                    )
                    Text(
                        text = "",
                        color = colorResource(id = R.color.BackgroundYellow),
                        fontSize = 20.sp
                    )
                }
            }


//Boton Siguiente
            TextButton(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .weight(1f),
                onClick = {
                    if (lista.lastIndex == actual) {
                        actual = 0
                    } else {
                        actual++
                    }
                    colorBotonVerdadero = R.color.ButtonBlue
                    colorBotonFalso = R.color.ButtonBlue
                    textoRespuesta = ""
                },
                colors = ButtonDefaults.outlinedButtonColors(colorResource(id = R.color.ButtonBlue)),
            ) {
                Row() {
                    Icon(
                        Icons.Filled.ArrowForward,
                        contentDescription = "flecha",
                        tint = colorResource(id = R.color.BackgroundYellow)
                    )
                    Text(
                        text = "  Siguiente",
                        color = colorResource(id = R.color.BackgroundYellow),
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun pruebaBoton() {
    Column(
        modifier = Modifier
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            onClick = {/*TODO*/ }
        ) {
            Text(
                "boton",
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}