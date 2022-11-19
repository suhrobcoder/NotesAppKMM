package uz.suhrob.notesapp.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import uz.suhrob.notesapp.di.initKoin

class NotesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@NotesApplication)
        }
    }
}