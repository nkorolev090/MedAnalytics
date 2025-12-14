package org.nkoro.medanalytics

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.nkoro.medanalytics.feature.home.mainScreen.MainScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(MainScreen())
    }
}