package br.com.soluevo.microappviaverejo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.soluevo.microapplibrary.NavigationHostActivity
import br.com.soluevo.microapplibrary.application.commom.utils.Constants.CompanyThemeConstant.EXTRA_COMPANY
import br.com.soluevo.microapplibrary.application.commom.utils.Constants.EXTRA_CONSTANTS.URL_BASE
import br.com.soluevo.microapplibrary.domain.Company
import br.com.soluevo.microapplibrary.domain.CompanyFilter
import br.com.soluevo.microapplibrary.domain.Theme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val theme = Theme("#CD5C5C", "#FFA07A")
        val companyFilters = listOf(
            CompanyFilter(
                1,
                "HP",
                "https://static.netshoes.com.br/r0.0.390.13/netshoesbr/images/share.png"
            ),
            CompanyFilter(
                2,
                "Zoom",
                "https://static.netshoes.com.br/r0.0.390.13/netshoesbr/images/share.png"
            ),
            CompanyFilter(
                3,
                "Netshoes",
                "https://static.netshoes.com.br/r0.0.390.13/netshoesbr/images/share.png"
            ),
            CompanyFilter(
                4,
                "Adidas",
                "https://static.netshoes.com.br/r0.0.390.13/netshoesbr/images/share.png"
            )
        )
        val company = Company(
            1,
            "Company name",
            "https://static.netshoes.com.br/r0.0.390.13/netshoesbr/images/share.png",
            theme,
            companyFilters
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
