package pl.marrod.kotlinshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import pl.marrod.kotlinshowcase.navigation.NavGraph
import pl.marrod.kotlinshowcase.ui.theme.KotlinShowcaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Enable edge-to-edge display to allow content to flow behind system bars.
        // This is mandatory for modern Android UI and Material 3 design.
        enableEdgeToEdge()
        
        setContent {
            KotlinShowcaseTheme {
                // Surface provides a background color and handles the layout's root.
                // Each screen (Gallery/Detail) uses its own Scaffold to manage
                // TopAppBars and window insets individually for precise control.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    // The NavGraph manages all app navigation, 
                    // starting with the GalleryScreen.
                    NavGraph(navController = navController)
                }
            }
        }
    }
}
