package br.com.soluevo.microapplibrary

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import br.com.soluevo.microapplibrary.application.commom.utils.Constants
import br.com.soluevo.microapplibrary.application.commom.utils.Constants.CompanyThemeConstant.EXTRA_COMPANY
import br.com.soluevo.microapplibrary.application.commom.utils.listeners.OnBackPressedListener
import br.com.soluevo.microapplibrary.domain.Company
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.host_navigation_activity.*

class NavigationHostMicroAppsActivity : AppCompatActivity() {

    private var url = ""
    private var mOnBackPressedListener: OnBackPressedListener? = null
    private var mCompany: Company? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.host_navigation_activity)

        intent.apply {
            extras?.apply {
                mCompany = getSerializable(EXTRA_COMPANY) as Company
                val companyTheme = mCompany?.theme
                url = getString(Constants.EXTRA_CONSTANTS.URL_BASE, "")

                appbar.setBackgroundColor(Color.parseColor(companyTheme?.toolbarHex))
            }
        }

        setUpImageLogo()
        setUpBottomNavigation()
        setUpToolbarBar()
    }


    private fun setUpToolbarBar() {
        val toolbar = toolbar

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        appbar.setBackgroundColor(Color.parseColor(mCompany?.theme?.toolbarHex))
        setUpImageLogo()
    }

    private fun setUpImageLogo() {
        Picasso.get()
            .load(mCompany?.imageUrl)
            .into(object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {

                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    val drawImage = BitmapDrawable(resources, bitmap)
                    supportActionBar?.setLogo(drawImage)
                }

            })
    }

    private fun setUpBottomNavigation() {
        val navController = Navigation.findNavController(this, R.id.my_nav_fragment)
        bottomNavigation?.setupWithNavController(navController)
        bottomNavigation?.setBackgroundColor(Color.parseColor("#FFFFFF"))
    }

    fun getUrl(): String = url

    fun onBackPressedListener(onBackPressedListener: OnBackPressedListener) {
        this.mOnBackPressedListener = onBackPressedListener
    }


    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return false
    }

    fun getCompany() = mCompany

}
