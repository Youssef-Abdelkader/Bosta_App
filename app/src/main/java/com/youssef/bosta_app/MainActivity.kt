package com.youssef.bosta_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import com.youssef.bosta_app.navigation.AppNavGraph
import com.youssef.bosta_app.ui.theme.Bosta_AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Bosta_AppTheme {
                AppNavGraph()
            }
        }
    }
}
