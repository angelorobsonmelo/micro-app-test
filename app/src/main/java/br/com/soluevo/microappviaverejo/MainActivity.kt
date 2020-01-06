package br.com.soluevo.microappviaverejo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.soluevo.microapplibrary.MicroLibraryMainActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, MicroLibraryMainActivity::class.java))
    }
}
