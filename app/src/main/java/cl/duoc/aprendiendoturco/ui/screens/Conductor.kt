package cl.duoc.aprendiendoturco.ui.screens

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import cl.duoc.aprendiendoturco.ui.utils.obtenerWindowSizeClass

@Composable
fun Conductor () {
    val windowSizeClass = obtenerWindowSizeClass()
    when(windowSizeClass.widthSizeClass){
        WindowWidthSizeClass.Compact -> HomeScreenCompact()
        // WindowWidthSizeClass.Medium -> HomeScreenMedium()
        // WindowWidthSizeClass.Expanded -> HomeScreenExpanded()
    }
}