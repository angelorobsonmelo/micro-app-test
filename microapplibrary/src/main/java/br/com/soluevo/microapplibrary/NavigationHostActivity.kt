package br.com.soluevo.microapplibrary

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import br.com.soluevo.microapplibrary.application.commom.utils.Constants
import br.com.soluevo.microapplibrary.application.commom.utils.Constants.CompanyThemeConstant.EXTRA_COMPANY_THEME
import br.com.soluevo.microapplibrary.domain.CompanyThemeConfig
import kotlinx.android.synthetic.main.host_navigation_activity.*

class NavigationHostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.host_navigation_activity)

        intent.apply {
            extras?.apply {
                val themeConfig = getSerializable(EXTRA_COMPANY_THEME) as CompanyThemeConfig
                appbar.setBackgroundColor(Color.parseColor(themeConfig.bottomBarHex))
            }

        }

    }
}
