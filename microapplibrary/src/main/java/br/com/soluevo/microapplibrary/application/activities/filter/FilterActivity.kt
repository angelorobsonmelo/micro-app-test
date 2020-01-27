package br.com.soluevo.microapplibrary.application.activities.filter

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.activities.filter.category.FilterCategoryActivity
import br.com.soluevo.microapplibrary.application.commom.utils.Constants
import br.com.soluevo.microapplibrary.domain.Company
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_filter_ctivity.*
import kotlinx.android.synthetic.main.host_navigation_activity.appbar
import kotlinx.android.synthetic.main.host_navigation_activity.toolbar

class FilterActivity : AppCompatActivity() {

    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    private lateinit var mCompany: Company

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_ctivity)
        setUpElements()
    }

    private fun setUpElements() {
        setUpToolbarBar()
        handleIntent()

        initApplyFilterClickListener()
        initCategoryFilterClickListener()
    }

    private fun initCategoryFilterClickListener() {
        categoryTextInputLayout.setOnClickListener {
            val bundle = Bundle()
            val intent = Intent(this, FilterCategoryActivity::class.java).apply {

                bundle.putSerializable(
                    Constants.CompanyThemeConstant.EXTRA_COMPANY,
                    mCompany
                )
                putExtras(bundle)
            }

            startActivityForResult(intent, 550)
        }
    }

    private fun initApplyFilterClickListener() {
        applyFilterButton.setOnClickListener {
            finishActivity(5046)
            finish()
        }
    }

    private fun handleIntent() {
        intent.apply {
            extras?.apply {
                mCompany =
                    getSerializable(Constants.CompanyThemeConstant.EXTRA_COMPANY) as Company
                val companyTheme = mCompany.theme

                appbar.setBackgroundColor(Color.parseColor(companyTheme.toolbarHex))

                setImageInToolbarBar(mCompany.imageUrl)

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
