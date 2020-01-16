package br.com.soluevo.microapplibrary

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.soluevo.microapplibrary.application.commom.utils.Constants
import br.com.soluevo.microapplibrary.application.commom.utils.Constants.CompanyThemeConstant.EXTRA_COMPANY
import br.com.soluevo.microapplibrary.application.commom.utils.listeners.OnBackPressedListener
import br.com.soluevo.microapplibrary.domain.Company
import kotlinx.android.synthetic.main.host_navigation_activity.*

class NavigationHostActivity : AppCompatActivity() {

    private var url = ""
    private var mOnBackPressedListener: OnBackPressedListener? = null
    private var company: Company? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.host_navigation_activity)

        intent.apply {
            extras?.apply {
                company = getSerializable(EXTRA_COMPANY) as Company
                val companyTheme = company?.theme
                url = getString(Constants.EXTRA_CONSTANTS.URL_BASE, "")

                appbar.setBackgroundColor(Color.parseColor(companyTheme?.bottomBarHex))
            }
        }
    }

    fun getUrl(): String = url

    fun onBackPressedListener(onBackPressedListener: OnBackPressedListener) {
        this.mOnBackPressedListener = onBackPressedListener
    }

    fun finishActivity() {
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        mOnBackPressedListener?.apply {
            onBackPressedClicked()
        }

        return false
    }

    fun getCompany() = company
}
