package com.example.movieapplication.domain.usecases.app_entry

import com.example.movieapplication.domain.manager.LocalUserManager
import java.util.concurrent.Flow

class ReadAppEntry(private val localUserManager: LocalUserManager){
     operator fun invoke() :kotlinx.coroutines.flow.Flow<Boolean>
    {
        return localUserManager.readAppEntry()
    }
}