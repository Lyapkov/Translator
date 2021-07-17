package com.lyapkov.translator.di

import androidx.room.Room
import com.lyapkov.translator.view.history.HistoryInteractor
import com.lyapkov.translator.view.history.HistoryViewModel
import com.lyapkov.translator.view.main.MainInteractor
import com.lyapkov.translator.view.main.MainViewModel
import org.koin.dsl.module

val application = module {
    single {
        Room.databaseBuilder(
            get(),
            com.lyapkov.repository.room.HistoryDataBase::class.java,
            "HistoryDB"
        ).build()
    }
    single { get<com.lyapkov.repository.room.HistoryDataBase>().historyDao() }
    single<com.lyapkov.repository.Repository<List<com.lyapkov.model.data.DataModel>>> {
        com.lyapkov.repository.RepositoryImplementation(
            com.lyapkov.repository.RetrofitImplementation()
        )
    }
    single<com.lyapkov.repository.RepositoryLocal<List<com.lyapkov.model.data.DataModel>>> {
        com.lyapkov.repository.RepositoryImplementationLocal(
            com.lyapkov.repository.RoomDataBaseImplementation(
                get()
            )
        )
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}