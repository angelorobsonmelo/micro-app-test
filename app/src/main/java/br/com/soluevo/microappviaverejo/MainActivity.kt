package br.com.soluevo.microappviaverejo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.soluevo.microapplibrary.NavigationHostActivity
import br.com.soluevo.microapplibrary.application.commom.utils.Constants.CompanyThemeConstant.EXTRA_COMPANY
import br.com.soluevo.microapplibrary.application.commom.utils.Constants.EXTRA_CONSTANTS.URL_BASE
import br.com.soluevo.microapplibrary.domain.Company
import br.com.soluevo.microapplibrary.domain.CompanyThemeConfig

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val companyThemeConfig = CompanyThemeConfig("#CD5C5C", "#FFA07A")
        val company = Company(
            1,
            "Company name",
            "https://static.netshoes.com.br/r0.0.390.13/netshoesbr/images/share.png",
            companyThemeConfig
        )

        val intent = Intent(this, NavigationHostActivity::class.java).apply {
            val bundle = Bundle()
            bundle.putSerializable(EXTRA_COMPANY, company)
            bundle.putString(URL_BASE, "https://private-c04e04-viavarejo1.apiary-mock.com/")
            putExtras(bundle)
        }

        startActivity(intent)
    }
}
