package com.example.movieapplication.domain.usecases.app_entry

import com.example.movieapplication.domain.manager.LocalUserManager

class SaveAppEntry(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke()
    {
localUserManager.saveAppEntry()
    }
}