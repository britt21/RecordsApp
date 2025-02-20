package com.mobile.recorduserapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mobile.recorduserapp.data.cons.HOMESCREEN
import com.mobile.recorduserapp.ui.HomeScreen
import com.mobile.recorduserapp.ui.theme.RecordUserAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecordUserAppTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = Color.White, darkIcons = false)

                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    NavHost(
                        modifier = Modifier,
                        navController = navController,
                        startDestination = HOMESCREEN
                    ) {


                        composable(HOMESCREEN) {
                            HomeScreen(modifier = Modifier.padding(innerPadding))
                        }
                    }

                }
            }
        }
    }
}
