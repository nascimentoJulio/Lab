package com.example.clima

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.lifecycle.LiveData
import coil.compose.rememberImagePainter
import com.example.clima.api.model.ClimaReponse
import com.example.clima.api.model.Forecastday
import com.example.clima.viewmodel.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


class MainActivity : ComponentActivity() {
    @ExperimentalUnitApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(Color(0xFF0656CF))
            val viewModel = MainViewModel()
            viewModel.getClima("São Leopoldo")
            MainContainer(viewModel.clima)
        }
    }

    @ExperimentalUnitApi
    @Composable
    fun MainContainer(clima: LiveData<ClimaReponse?>) {
        val Azul = Color(0xFF0656CF)
        Scaffold(
            content = {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Azul)
                        .padding(top = Dp(30f))

                ) {
                    ClimaAtualContainer(clima)
                    PrevisaoContainer(clima)
                }

            }
        )
    }

    @Composable
    private fun PrevisaoContainer(previsoes: LiveData<ClimaReponse?>) {
        val a by previsoes.observeAsState()

        LazyRow(contentPadding = PaddingValues(Dp(16f))) {
            a?.forecast?.let { itemsIndexed(it.forecastday) { _, previsao -> CardPrevisao(previsao = previsao) } }
        }

    }


    @Composable
    private fun CardPrevisao(previsao: Forecastday?) {
        Scaffold(
            Modifier
                .height(Dp(150f))
                .width(Dp(150f))
                .padding(paddingValues = PaddingValues(horizontal = Dp(8f))),
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.background(Color(0x1BAFB8C0))
                ) {
                    previsao?.date?.let { it1 -> Text(text = it1) }
                    Image(
                        painter = rememberImagePainter(data = "https:" + previsao?.day?.condition?.icon,
                            builder = {
                                crossfade(true)
                            }),
                        contentDescription = null,
                        alignment = Alignment.CenterEnd,
                        modifier = Modifier.size(Dp(50f))
                    )
                    Text(text = previsao?.day?.maxtemp_c?.toInt().toString())

                }

            }
        )
    }

}

@SuppressLint("InvalidColorHexValue")
@ExperimentalUnitApi
@Composable
fun ClimaAtualContainer(clima: LiveData<ClimaReponse?>) {
    val a by clima.observeAsState()
    val CinzaVidro = Color(0x7EAFB8C0)
    Row(
        modifier = Modifier
            .background(CinzaVidro)
            .fillMaxWidth()
            .height(Dp(300f))
    ) {
        Row(
            modifier = Modifier
                .padding(Dp(30f))
        ) {
            a?.let {
                Column() {
                    Image(
                        painter = rememberImagePainter(data = "https:" + it.current.condition.icon,
                            builder = {
                                crossfade(true)
                            }),
                        contentDescription = null,
                        alignment = Alignment.CenterEnd,
                        modifier = Modifier.size(Dp(50f))
                    )

                    it.current.condition.let { it1 ->
                        Text(
                            text = it1.text,
                            textAlign = TextAlign.End,
                            fontSize = TextUnit(18f, TextUnitType.Sp)
                        )
                    }
                    Text(
                        it.current.temp_c.toInt().toString() + "ºC",
                        textAlign = TextAlign.End,
                        fontSize = TextUnit(48f, TextUnitType.Sp)
                    )
                    a?.location?.name?.let { it1 ->
                        Text(
                            it1,
                            fontSize = TextUnit(18f, TextUnitType.Sp)
                        )
                    }
                }
            }
        }
    }
}

