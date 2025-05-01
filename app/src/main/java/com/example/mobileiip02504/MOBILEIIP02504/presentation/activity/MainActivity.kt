package com.example.mobileiip02504.MOBILEIIP02504.presentation.activity

/**
 * File: MainActivity.kt
 * Created by Vinay Parihar
 */
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.mobileiip02504.MOBILEIIP02504.presentation.activity.ui.theme.MOBILEIIP02504Theme
import com.example.mobileiip02504.MOBILEIIP02504.presentation.viewModels.PasswordViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: PasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MOBILEIIP02504Theme() {
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}
