package br.com.soluevo.microapplibrary.application.activities.filter.category

import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.domain.CheckBoxModel
import kotlinx.android.synthetic.main.activity_filter_category.*
import java.util.*

class FilterCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_category)

        val listView = listView
        val dataModels = ArrayList<CheckBoxModel>()

        dataModels.add(CheckBoxModel(1, "Acessórios", false))
        dataModels.add(CheckBoxModel(2, "Bolsas", false))
        dataModels.add(CheckBoxModel(3, "Blusas", false))
        dataModels.add(CheckBoxModel(4, "Camisas", false))
        dataModels.add(CheckBoxModel(5, "Calças", false))
        dataModels.add(CheckBoxModel(6, "Calçados", false))
        dataModels.add(CheckBoxModel(7, "Cintos", false))

        val adapter = CategoryAdapter(applicationContext, dataModels)

        listView.adapter = adapter
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val dataModel: CheckBoxModel = dataModels[position]
                dataModel.checked = !dataModel.checked
                adapter.notifyDataSetChanged()
            }
    }
}
