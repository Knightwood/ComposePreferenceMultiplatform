package com.knightwood.floor.core.core

import kotlinx.coroutines.flow.Flow

interface IPreferenceDao<T : Any> {
    val flow: Flow<T>
    val value: T
    suspend fun update(value: T)
    suspend fun clear()
}
