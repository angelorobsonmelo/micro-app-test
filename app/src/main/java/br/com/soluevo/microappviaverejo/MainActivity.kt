package br.com.soluevo.microappviaverejo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.soluevo.microapplibrary.NavigationHostActivity
import br.com.soluevo.microapplibrary.application.commom.utils.Constants.CompanyThemeConstant.EXTRA_COMPANY_THEME
import br.com.soluevo.microapplibrary.application.commom.utils.Constants.EXTRA_CONSTANTS.URL_BASE
import br.com.soluevo.microapplibrary.domain.CompanyThemeConfig

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        finish()
        val companyThemeConfig = CompanyThemeConfig("#CD5C5C", "#FFA07A")

        val intent = Intent(this, NavigationHostActivity::class.java).apply {
            val bundle = Bundle()
            bundle.putSerializable(EXTRA_COMPANY_THEME, companyThemeConfig)
            bundle.putString(URL_BASE, "https://private-c04e04-viavarejo1.apiary-mock.com/")
            putExtras(bundle)
        }

        startActivity(intent)
    }
}
