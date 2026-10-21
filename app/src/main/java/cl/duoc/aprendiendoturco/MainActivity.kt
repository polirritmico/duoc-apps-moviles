package cl.duoc.aprendiendoturco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cl.duoc.aprendiendoturco.navigation.AppNavigation
import cl.duoc.aprendiendoturco.ui.theme.AprendiendoTurcoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AprendiendoTurcoTheme {
                AppNavigation()
            }
        }
    }
}