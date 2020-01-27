package br.com.soluevo.microapplibrary.application.activities.filter.category

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.commom.utils.Constants
import br.com.soluevo.microapplibrary.domain.CheckBoxModel
import br.com.soluevo.microapplibrary.domain.Company
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_filter_category.*
import java.util.*

class FilterCategoryActivity : AppCompatActivity() {

    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    private lateinit var mCompany: Company

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_category)
        setUpElements()
    }

    private fun setUpElements() {
        setUpToolbarBar()
        handleIntent()
        setUpListViewCheck()
        initApplyFilterClickListener()
    }

    private fun initApplyFilterClickListener() {
        applyFilterButton.setOnClickListener {
            finish()
        }
    }

    private fun setUpListViewCheck() {
        val listView = listView
        val dataModels = getCheckBoxModels()
        val adapter = CategoryAdapter(applicationContext, dataModels)

        listView.adapter = adapter
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val dataModel: CheckBoxModel = dataModels[position]
                dataModel.checked = !dataModel.checked
                adapter.notifyDataSetChanged()
            }

        initClearFilterClickListener(dataModels, adapter)
    }

    private fun initClearFilterClickListener(
        dataModels: ArrayList<CheckBoxModel>,
        adapter: CategoryAdapter
    ) {
        clearFilterTextView.setOnClickListener {
            dataModels.forEach {
                it.checked = false
            }

            adapter.notifyDataSetChanged()
        }
    }

    private fun getCheckBoxModels(): ArrayList<CheckBoxModel> {
        val dataModels = ArrayList<CheckBoxModel>()

        dataModels.add(CheckBoxModel(1, "Acessórios", false))
        dataModels.add(CheckBoxModel(2, "Bolsas", false))
        dataModels.add(CheckBoxModel(3, "Blusas", false))
        dataModels.add(CheckBoxModel(4, "Camisas", false))
        dataModels.add(CheckBoxModel(5, "Calças", false))
        dataModels.add(CheckBoxModel(6, "Calçados", false))
        dataModels.add(CheckBoxModel(7, "Cintos", false))
        return dataModels
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
