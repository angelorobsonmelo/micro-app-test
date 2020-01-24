package br.com.soluevo.microapplibrary.application.activities.filter.category

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.NonNull
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.domain.CheckBoxModel
import java.util.*

class CategoryAdapter(context: Context, private var data: ArrayList<CheckBoxModel>) :
    ArrayAdapter<CheckBoxModel>(context, R.layout.row_check_item) {


    // View lookup cache
    private class ViewHolder {
        var txtName: TextView? = null
        var checkBox: CheckBox? = null
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): CheckBoxModel {
        return data[position]
    }


    override fun getView(
        position: Int,
        convertView: View?, @NonNull parent: ViewGroup
    ): View {
        var convertView = convertView
        val viewHolder: ViewHolder
        val result: View?
        if (convertView == null) {
            viewHolder = ViewHolder()
            convertView =
                LayoutInflater.from(parent.context).inflate(R.layout.row_check_item, parent, false)
            viewHolder.txtName =
                convertView!!.findViewById<View>(R.id.txtName) as TextView
            viewHolder.checkBox =
                convertView.findViewById<View>(R.id.checkBox) as CheckBox
            result = convertView
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
            result = convertView
        }
        val item: CheckBoxModel = getItem(position)
        viewHolder.txtName?.text = item.name
        viewHolder.checkBox!!.isChecked = item.checked
        return result
    }

    fun updateItems(dataModels: ArrayList<CheckBoxModel>) {
        data.clear()
        data.addAll(dataModels)
        data = dataModels

        notifyDataSetChanged()
    }
}