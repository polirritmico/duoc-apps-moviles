package cl.duoc.aprendiendoturco.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF8DDBCA),
    onPrimary = Color(0xFF093B35),
    primaryContainer = Color(0xFF23564E),
    onPrimaryContainer = Color(0xFFC2F5E9),
    secondary = Color(0xFFADC7E7),
    tertiary = Color(0xFFF2BD86),
    background = Color(0xFF142B40),
    onBackground = Color(0xFFECF2F7),
    surface = Color(0xFF203B50),
    onSurface = Color(0xFFECF2F7),
    surfaceVariant = Color(0xFF304B5F),
    onSurfaceVariant = Color(0xFFCBD8E2),
    surfaceContainerLow = Color(0xFF1A3349),
    surfaceContainer = Color(0xFF203B50),
    surfaceContainerHigh = Color(0xFF29465B),
    outline = Color(0xFF8CA6B8)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF226B60),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFCBEDE3),
    onPrimaryContainer = Color(0xFF123E37),
    secondary = Color(0xFF405E7B),
    tertiary = Color(0xFF966028),
    background = Color(0xFFF8F4EB),
    onBackground = Color(0xFF213747),
    surface = Color(0xFFFFFCF6),
    onSurface = Color(0xFF213747),
    surfaceVariant = Color(0xFFE2ECE7),
    onSurfaceVariant = Color(0xFF445A61),
    surfaceContainerLow = Color(0xFFEEF2EA),
    surfaceContainer = Color(0xFFE5EEE7),
    surfaceContainerHigh = Color(0xFFDCE8E1),
    outline = Color(0xFF738B8C)
)

@Composable
fun AprendiendoTurcoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Conservamos la paleta de la app aunque cambie el fondo de pantalla del teléfono.
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes(
            small = RoundedCornerShape(12.dp),
            medium = RoundedCornerShape(18.dp),
            large = RoundedCornerShape(24.dp)
        ),
        content = content
    )
}
