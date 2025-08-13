package com.example.pantallabasica

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pantallabasica.ui.theme.PantallaBasicaTheme


@Preview(showBackground = true)
@Composable
fun PresentacionUVG() {
    PantallaBasicaTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Contenedor con borde verde y logo de fondo
                Box(
                    modifier = Modifier
                        .border(
                            width = 5.dp,
                            color = Color(0xFF006400),
                            shape = RectangleShape
                        )
                        .padding(16.dp)
                ) {
                    // Imagen de fondo semitransparente
                    Image(
                        painter = painterResource(id = R.drawable.uvg),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(50.dp),
                        contentScale = ContentScale.Fit,
                        alpha = 0.08f
                    )

                    // Contenido encima del logo
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment
                            .CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Universidad del Valle de Guatemala",
                            style = MaterialTheme.typography.headlineSmall,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 170.dp)
                        )
                        Text(
                            text = "Programación de plataformas móviles, Sección 30",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "INTEGRANTES",
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp,
                                modifier = Modifier.padding(horizontal =25.dp, vertical = 8.dp)
                            )
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "Juan Durini")
                                Text(text = "Cristiano Ronaldo")
                                Text(text = "Lionel Messi")
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "CATEDRÁTICO",
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp,
                                modifier = Modifier.padding(horizontal =25.dp, vertical = 8.dp)
                            )
                            Text(
                                text = "Juan Carlos Durini",
                                modifier = Modifier.padding(top = 10.dp))
                        }
                        Column(
                            modifier = Modifier.padding(top = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Josué García")
                            Text(text = "24918")
                        }
                    }
                }
            }
        }
    }
}
