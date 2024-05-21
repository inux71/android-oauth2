package com.grabieckacper.oauth2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.grabieckacper.oauth2.ui.theme.OAuth2Theme
import com.grabieckacper.oauth2.ui.view.DetailsView
import com.grabieckacper.oauth2.ui.view.LoginView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            OAuth2Theme {
                val navController: NavHostController = rememberNavController()

                NavHost(navController = navController, startDestination = "login") {
                    composable(route = "login") {
                        LoginView(onNavigateToDetailsView = {
                            navController.navigate("details")
                        })
                    }
                    composable("details") {
                        DetailsView(onGoBackToLoginView = {
                            navController.popBackStack()
                        })
                    }
                }
            }
        }
    }
}
