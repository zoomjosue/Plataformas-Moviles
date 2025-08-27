package com.example.lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab6.ui.theme.Lab6Theme
import kotlin.math.max
import kotlin.math.min

data class HistorialItem(
    val valor: Int,
    val esIncremento: Boolean
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab6Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContadorScreen()
                }
            }
        }
    }
}

@Composable
fun ContadorScreen() {
    var contador by remember { mutableIntStateOf(0) }
    var totalIncrementos by remember { mutableIntStateOf(0) }
    var totalDecrementos by remember { mutableIntStateOf(0) }
    var valorMaximo by remember { mutableIntStateOf(0) }
    var valorMinimo by remember { mutableIntStateOf(0) }
    var historial by remember { mutableStateOf(emptyList<HistorialItem>()) }

    val totalCambios = totalIncrementos + totalDecrementos

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Josué García",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Contador
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Botón decrementar
            FloatingActionButton(
                onClick = {
                    contador--
                    totalDecrementos++
                    if (historial.isEmpty()) {
                        valorMinimo = contador
                        valorMaximo = contador
                    } else {
                        valorMinimo = min(valorMinimo, contador)
                        valorMaximo = max(valorMaximo, contador)
                    }
                    historial = historial + HistorialItem(contador, false)
                },
                containerColor = Color(0xFF5C6BC0),
                contentColor = Color.White,
                modifier = Modifier.size(56.dp)
            ) {
                Text(
                    text = "−",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            // Número del contador
            Text(
                text = contador.toString(),
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.width(120.dp),
                textAlign = TextAlign.Center
            )

            // Botón incrementar
            FloatingActionButton(
                onClick = {
                    contador++
                    totalIncrementos++
                    if (historial.isEmpty()) {
                        valorMinimo = contador
                        valorMaximo = contador
                    } else {
                        valorMinimo = min(valorMinimo, contador)
                        valorMaximo = max(valorMaximo, contador)
                    }
                    historial = historial + HistorialItem(contador, true)
                },
                containerColor = Color(0xFF5C6BC0),
                contentColor = Color.White,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Incrementar",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Estadísticas
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                EstadisticaRow("Total incrementos:", totalIncrementos)
                EstadisticaRow("Total decrementos:", totalDecrementos)
                EstadisticaRow("Valor máximo:", valorMaximo)
                EstadisticaRow("Valor mínimo:", valorMinimo)
                EstadisticaRow("Total cambios:", totalCambios)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Historial
        if (historial.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Historial:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Layout manual para simular FlowRow con máximo 5 columnas
                HistorialGrid(historial)
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(32.dp))

        // Botón Reiniciar
        Button(
            onClick = {
                contador = 0
                totalIncrementos = 0
                totalDecrementos = 0
                valorMaximo = 0
                valorMinimo = 0
                historial = emptyList()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5C6BC0)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Reiniciar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun EstadisticaRow(label: String, valor: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = valor.toString(),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun HistorialGrid(historial: List<HistorialItem>) {
    val itemsPerRow = 5
    val rows = historial.chunked(itemsPerRow)

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        rows.forEach { rowItems ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Espaciador izquierdo para centrar la fila
                Spacer(modifier = Modifier.weight(1f))

                rowItems.forEach { item ->
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                if (item.esIncremento) Color(0xFF4CAF50) else Color(0xFFF44336)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item.valor.toString(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }

                // Espaciador derecho para centrar la fila
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ContadorScreenPreview() {
    Lab6Theme {
        ContadorScreen()
    }
}