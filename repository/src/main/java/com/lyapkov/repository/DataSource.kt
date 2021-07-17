package com.lyapkov.repository

interface DataSource<T> {

    suspend fun getData(word: String): T
}
