package org.nkoro.medanalytics

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    startKoin {
        appModules
    }
    return ComposeUIViewController { App() }
}