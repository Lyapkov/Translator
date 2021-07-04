package com.lyapkov.translator.model.datasource

import com.lyapkov.translator.model.data.AppState
import com.lyapkov.translator.model.data.DataModel
import com.lyapkov.translator.room.HistoryDao
import com.lyapkov.translator.utils.convertDataModelSuccessToEntity
import com.lyapkov.translator.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}

