package com.grabieckacper.oauth2.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginView(onNavigateToDetailsView: () -> Unit) {
    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(text = "Login View")

            Button(onClick = onNavigateToDetailsView) {
                Text(text = "Details View")
            }
        }
    }
}
