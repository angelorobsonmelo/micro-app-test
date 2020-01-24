package br.com.soluevo.microapplibrary.application.activities.filter

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.commom.utils.Constants
import br.com.soluevo.microapplibrary.domain.Company
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_filterctivity.*
import kotlinx.android.synthetic.main.host_navigation_activity.*
import kotlinx.android.synthetic.main.host_navigation_activity.appbar
import kotlinx.android.synthetic.main.host_navigation_activity.toolbar

class FilterActivity : AppCompatActivity() {

    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filterctivity)
        setUpElements()
    }

    private fun setUpElements() {
        setUpToolbarBar()
        handleIntent()

        applyFilterButton.setOnClickListener {
            finishActivity(5046)
            finish()
        }
    }

    private fun handleIntent() {
        intent.apply {
            extras?.apply {
                val company =
                    getSerializable(Constants.CompanyThemeConstant.EXTRA_COMPANY) as Company
                val companyTheme = company.theme

                appbar.setBackgroundColor(Color.parseColor(companyTheme.bottomBarHex))

                setImageInToolbarBar(company.imageUrl)

            }
        }
    }

    private fun setUpToolbarBar() {
        this.mToolbar = toolbar
        setSupportActionBar(mToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun setImageInToolbarBar(
        logo: String
    ) {
        Picasso.get()
            .load(logo)
            .into(object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {

                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    baseContext?.apply {
                        val drawImage = BitmapDrawable(this.resources, bitmap)
                        supportActionBar?.setLogo(drawImage)
                    }
                }

            })
    }

}
