package br.com.soluevo.microapplibrary.application.components.companies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.soluevo.microapplibrary.NavigationHostActivity
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.commom.EventObserver
import br.com.soluevo.microapplibrary.application.commom.di.components.fragments.DaggerCustomViewWithSimpleRecyclerViewHorizontalComponent
import br.com.soluevo.microapplibrary.application.commom.di.modules.application.ContextModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.network.NetWorkModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.recyclerview.SympleRecyclerViewHorizontal
import br.com.soluevo.microapplibrary.application.commom.utils.Constants
import br.com.soluevo.microapplibrary.application.commom.utils.listeners.CompanyClickListener
import br.com.soluevo.microapplibrary.application.components.companies.adapter.PartnersAdapter
import br.com.soluevo.microapplibrary.databinding.PartnerCompaniesBinding
import br.com.soluevo.microapplibrary.domain.Company
import javax.inject.Inject

class CompaniesComponent(context: Context, var attrs: AttributeSet) : LinearLayout(context, attrs) {


    lateinit var binding: PartnerCompaniesBinding

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mLayoutManager: LinearLayoutManager

    @Inject
    lateinit var mRecyclerView: RecyclerView

    private var viewModel: CompaniesViewModel? = null

    private lateinit var mAdapter: PartnersAdapter


    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.partner_companies, this, true
        )

        setUpElements()
    }

    private fun setUpElements() {
        initPartnersCompaniesAdapter()
        setUpDagger()
    }

    private fun initPartnersCompaniesAdapter() {
        mAdapter = PartnersAdapter(mutableListOf(), object : CompanyClickListener {
            override fun onClick(company: Company) {
                goToNavHost(company)
            }

            override fun onLongClick(company: Company) {

            }

        })
    }

    private fun goToNavHost(company: Company) {
        val bundle = Bundle()
        val intent = Intent(context, NavigationHostActivity::class.java).apply {

            bundle.putSerializable(Constants.CompanyThemeConstant.EXTRA_COMPANY, company)
            bundle.putString(
                Constants.EXTRA_CONSTANTS.URL_BASE,
                "https://private-c04e04-viavarejo1.apiary-mock.com/"
            )
            putExtras(bundle)
        }

        context.startActivity(intent)
    }

    private fun setUpDagger() {
        var url: String? = ""
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CompaniesComponent,
            0, 0
        ).apply {

            try {
                url = getString(R.styleable.CompaniesComponent_url)

            } finally {
                recycle()
            }
        }

        DaggerCustomViewWithSimpleRecyclerViewHorizontalComponent.builder()
            .contextModule(ContextModule(context))
            .netWorkModule(NetWorkModule(url!!))
            .sympleRecyclerViewHorizontal(
                SympleRecyclerViewHorizontal(
                    binding.partnersImagesRecyclerView,
                    mAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>
                )
            )
            .build()
            .inject(this)
    }

    fun getCompanies(activity: AppCompatActivity) {
        viewModel = ViewModelProviders.of(activity, mFactory)[CompaniesViewModel::class.java]
        viewModel?.getCompanies()

        binding.lifecycleOwner = activity
//        binding.viewModel = viewModel

        viewModel?.successObserver?.observe(activity, EventObserver {
            mAdapter.updateItems(it)
        })

        viewModel?.errorObserver?.observe(activity, EventObserver {
            print("ff")
        })

    }

    fun clearDisposable() {
        viewModel?.disposables?.clear()
    }

}