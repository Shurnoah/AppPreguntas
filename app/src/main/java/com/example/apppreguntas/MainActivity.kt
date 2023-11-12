package com.example.apppreguntas

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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

    var textoRespuesta by remember { mutableStateOf("") }

    var colorBotonFalso by remember { mutableStateOf(R.color.ButtonBlue) }
    var colorBotonVerdadero by remember { mutableStateOf(R.color.ButtonBlue) }
//    var botonActivo  by remember {  mutableStateOf(true) }

    var puntuacion by remember {  mutableStateOf(0) }
    var actual by remember { mutableStateOf(0) }



 // lista preguntas
    var lista = ArrayList<Preguntas>()
    lista.add(
        Preguntas(
            "¿Es el 'Oryctolagus cuniculus' un tipo de conejo?",
            R.drawable.conejo,
            true,
            "Bien, es el conejo europeo",
            "Mal, es el conejo europeo"
        )
    )
    lista.add(
        Preguntas(
            "¿Es ese animal un perro?",
            R.drawable.zorro,
            false,
            "No, es un zorrito",
            "Correcto, es un zorro preocupado"
        )
    )
    lista.add(
        Preguntas(
            "¿Son los Quokas Africanos?",
            R.drawable.quoka,
            false,
            "Incorrecto, son Oceánicos",
            "Bien, son del Sureste Australiano"
        )
    )
    lista.add(
        Preguntas(
            "¿Es este animal mayor que un gato?",
            R.drawable.wombat,
            true,
            "Pesando entre 25 y 55 kg, si",
            "Midiendo casi un metro, si que lo son"
        )
    )
    lista.add(
        Preguntas(
            "¿Es este animal conocido como Guacamayo verde?",
            R.drawable.loro,
            false,
            "No, es conocido como loro frente azul",
            "Correcto es un Loro hablador "
        )
    )



    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.BackgroundYellow))
            .padding(8.dp)
    ) {

//Puntuacion
        Text(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize()
                .wrapContentSize(CenterEnd)
                .padding(2.dp),
            text = "Puntuacion de la partida: $puntuacion",

            textAlign = TextAlign.Right,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.ButtonBlue)

        )
//Cabecera Pregunta
        Text(
            modifier = Modifier
                .weight(2f)
                .fillMaxSize()
                .wrapContentSize(Center),
            lineHeight = 40.sp,
            text = if (actual == 0)
                lista.get(actual).pregunta
            else lista.get(actual).pregunta,

            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            color = colorResource(id = R.color.TexGreen)

        )



//Imagen
        Image(
            painter = painterResource(id = lista.get(actual).imagen),
            contentDescription = "Imagen a identificar",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .weight(4f)
                .size(400.dp,900.dp)
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
            lineHeight = 40.sp,
            text = textoRespuesta,

            textAlign = TextAlign.Center,
            fontSize = 40.sp,
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
                    .padding(6.dp)
                    .fillMaxSize(),
                colors = ButtonDefaults.outlinedButtonColors(colorResource(id =  colorBotonVerdadero)),
//              enabled = false,
                onClick = {
                    textoRespuesta = lista.get(actual).Rverdadero
                    if (lista.get(actual).respuesta){
                        colorBotonVerdadero = R.color.CorrectAnswer
                        colorBotonFalso = R.color.IncorrectAnswe
                        puntuacion++
                    } else {
                        colorBotonVerdadero = R.color.IncorrectAnswe
                        colorBotonFalso = R.color.CorrectAnswer
                        puntuacion--
                    }
//                 botonActivo = false
                }

            ) {
                Text(
                    text = "Verdadero",
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.BackgroundYellow),
                    fontSize = 20.sp
                )
            }


//Boton Falso
            TextButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .fillMaxSize(),
                colors = ButtonDefaults.outlinedButtonColors(colorResource(id = colorBotonFalso)),
//              enabled = botonActivo,
                onClick = {
                        textoRespuesta = lista.get(actual).RFalso
                        if (!lista.get(actual).respuesta){
                            colorBotonVerdadero = R.color.IncorrectAnswe
                            colorBotonFalso = R.color.CorrectAnswer
                            puntuacion++
                        } else {
                            colorBotonVerdadero = R.color.CorrectAnswer
                            colorBotonFalso = R.color.IncorrectAnswe
                            puntuacion--
                        }
//                      botonActivo = false
                          },
            ) {
                Text(
                    text = "Falso",
                    fontWeight = FontWeight.Bold,
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
                    .padding(6.dp)
                    .weight(2f),
                onClick = {
                    if (actual == 0) {
                        actual = lista.lastIndex
                    } else {
                        actual--
                    }
                    colorBotonVerdadero = R.color.ButtonBlue
                    colorBotonFalso = R.color.ButtonBlue
                    textoRespuesta = ""
//                  botonActivo = true
                },
                colors = ButtonDefaults.outlinedButtonColors(colorResource(id = R.color.ButtonBlue)),
            ) {
                Row() {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "flecha",
                        tint = colorResource(id = R.color.BackgroundYellow)
                    )
                    Text(
                        text = " Anterior",
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.BackgroundYellow),
                        fontSize = 15.sp
                    )
                }
            }


//Boton Random
            TextButton(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .weight(1f),
                onClick = {
                    actual = Random().nextInt(lista.lastIndex + 1)
                    colorBotonVerdadero = R.color.ButtonBlue
                    colorBotonFalso = R.color.ButtonBlue
                    textoRespuesta = ""
//                  botonActivo = true
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
                    .padding(4.dp)
                    .weight(2f),
                onClick = {
                    if (lista.lastIndex == actual) {
                        actual = 0
                    } else {
                        actual++
                    }
                    colorBotonVerdadero = R.color.ButtonBlue
                    colorBotonFalso = R.color.ButtonBlue
                    textoRespuesta = ""
//                  botonActivo = true
                },
                colors = ButtonDefaults.outlinedButtonColors(colorResource(id = R.color.ButtonBlue)),
            ) {
                Row() {
                    Text(
                        text = "Siguiente ",
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.BackgroundYellow),
                        fontSize = 15.sp
                    )
                    Icon(
                        Icons.Filled.ArrowForward,
                        contentDescription = "flecha",
                        tint = colorResource(id = R.color.BackgroundYellow)
                    )
                }
            }
        }
    }
}
