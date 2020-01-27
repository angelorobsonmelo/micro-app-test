package br.com.soluevo.microapplibrary.application.fragments.products.productdetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.domain.Picture
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso

class SliderAdapterExample(private var mPictures: List<Picture>) :
    SliderViewAdapter<SliderAdapterExample.SliderAdapterVH>() {


    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.image_slider_layout_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {

        viewHolder.itemView.setOnClickListener {
        }

        val picture = mPictures[position]

        Picasso.get()
            .load(picture.url)
            .into(viewHolder.imageViewBackground)
    }

    override fun getCount(): Int {
        return mPictures.size
    }

    fun setImages(pictures: List<Picture>) {
        mPictures = pictures
    }

    inner class SliderAdapterVH(var itemView: View) :
        SliderViewAdapter.ViewHolder(itemView) {
        var imageViewBackground: ImageView = itemView.findViewById(R.id.iv_auto_image_slider)
//        var textViewDescription: TextView = itemView.findViewById(R.id.tv_auto_image_slider)
    }

}
