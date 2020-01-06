package br.com.soluevo.microapplibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MicroLibraryMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppThemeLibrary)
        setContentView(R.layout.activity_main_micro)
    }
}
