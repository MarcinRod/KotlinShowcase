package pl.marrod.kotlinshowcase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pl.marrod.kotlinshowcase.ui.screens.DetailScreen
import pl.marrod.kotlinshowcase.ui.screens.GalleryScreen

sealed class Screen(val route: String) {
    object Gallery : Screen("gallery")
    object Detail : Screen("detail/{conceptId}") {
        fun createRoute(conceptId: String) = "detail/$conceptId"
    }
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Gallery.route
    ) {
        composable(Screen.Gallery.route) {
            GalleryScreen(
                onConceptClick = { conceptId ->
                    navController.navigate(Screen.Detail.createRoute(conceptId))
                }
            )
        }
        composable(Screen.Detail.route) { backStackEntry ->
            val conceptId = backStackEntry.arguments?.getString("conceptId") ?: ""
            DetailScreen(
                conceptId = conceptId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
