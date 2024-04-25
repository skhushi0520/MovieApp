package com.example.movieapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.domain.usecases.app_entry.AppEntryUseCases
import com.example.movieapplication.presentation.nvgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel()
class MainViewModel @Inject constructor(
    appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    var splashCondition by mutableStateOf(true)
        private set


    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach{ shouldStartFromHomeScreen ->
            if(shouldStartFromHomeScreen) {
                startDestination = Route.HomeScreen.route
            } else {
                startDestination = Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}