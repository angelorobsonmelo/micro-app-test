package br.com.soluevo.microapplibrary.application.components.companies.adapter

import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.commom.utils.BindingAdapter
import br.com.soluevo.microapplibrary.application.commom.utils.listeners.CompanyClickListener
import br.com.soluevo.microapplibrary.databinding.PartnersCompaniesItemBinding
import br.com.soluevo.microapplibrary.domain.Company

class PartnersAdapter(
    private var mCompanies: List<Company>,
    private val listener: CompanyClickListener
) : BindingAdapter<PartnersCompaniesItemBinding>() {

    override fun onBindViewHolder(binding: PartnersCompaniesItemBinding, position: Int) {
        binding.run {
            val company = mCompanies[position]
            this.company = company
            executePendingBindings()

            binding.imageView?.setOnClickListener {
                listener.onClick(company)
            }
        }
    }

    override fun getLayoutResId(): Int = R.layout.partners_companies_item

    override fun getItemCount(): Int = mCompanies.size

    fun updateItems(companies: List<Company>) {
        this.mCompanies = companies
        notifyDataSetChanged()
    }
}