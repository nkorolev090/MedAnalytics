package org.nkoro.medanalytics.feature.home.di

import org.koin.dsl.module
import org.nkoro.medanalytics.feature.home.mainPage.MainScreenModel

val homeScreensModule
    get() = module {
        factory { MainScreenModel() }
    }