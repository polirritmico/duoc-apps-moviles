package cl.duoc.aprendiendoturco.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

/** Los motivos se concentran en los bordes para dejar libre la zona de lectura. */
@Composable
fun AppBackground(content: @Composable BoxScope.() -> Unit) {
    val scheme = MaterialTheme.colorScheme
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(scheme.background, scheme.surfaceContainerLow, scheme.surface)
                )
            )
    ) {
        Canvas(Modifier.fillMaxSize()) {
            val motifCenter = Offset(size.width * 1.05f, size.height * 0.08f)
            drawCircle(scheme.primary.copy(alpha = 0.13f),
                radius = size.minDimension * 0.42f, center = motifCenter)
            for (radius in listOf(0.29f, 0.35f, 0.49f)) {
                drawCircle(scheme.primary.copy(alpha = 0.18f),
                    radius = size.minDimension * radius, center = motifCenter,
                    style = Stroke(1.dp.toPx()))
            }
            drawCircle(scheme.tertiary.copy(alpha = 0.12f),
                radius = size.minDimension * 0.38f,
                center = Offset(-size.width * 0.17f, size.height * 0.91f))
            for (row in 0..3) for (column in 0..3) {
                drawCircle(scheme.tertiary.copy(alpha = 0.30f), radius = 1.5.dp.toPx(),
                    center = Offset(size.width - (20 + column * 12).dp.toPx(),
                        size.height - (48 + row * 12).dp.toPx()))
            }
        }
        content()
    }
}
