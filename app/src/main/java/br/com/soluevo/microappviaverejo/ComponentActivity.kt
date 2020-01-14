package br.com.soluevo.microappviaverejo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_component.*

class ComponentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component)

        val component = companiesComponent
//        component.url = "https://private-c04e04-viavarejo1.apiary-mock.com/"

        component.getCompanies(this)

    }
}
