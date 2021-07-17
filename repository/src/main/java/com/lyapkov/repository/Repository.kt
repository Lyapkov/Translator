package com.lyapkov.repository

interface Repository<T> {

    suspend fun getData(word: String): T
}
