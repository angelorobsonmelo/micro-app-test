package br.com.soluevo.microapplibrary.application.commom.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.host_navigation_micro_apps_activity.*
import java.lang.Exception


open class FragmentBase : Fragment() {


    //    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
////        setUpBehavorBottomNavigation()
//    }
//
//    /*private fun setUpBehavorBottomNavigation() {
//        val bottomNavigationView = activity?.bottomNavigation
//        val layoutParams = bottomNavigationView?.layoutParams as CoordinatorLayout.LayoutParams
//        layoutParams.behavior = BottomNavigationBehavior()
//    }*/
//
//    fun showAlertError(message: String) {
//        val builder = AlertDialog.Builder(context!!)
//
//        builder
//            .setMessage(message.toInt())
//            .setCancelable(false)
//            .setPositiveButton("OK") { _, _ -> }
//
//        val alert = builder.create()
//        alert.show()
//    }
//
//   /* fun showConfirmDialog(title: String, message: String, dialogListener: ListenerConfirmDialog) {
//        val builder = AlertDialog.Builder(context!!)
//
//        builder
//            .setMessage(message.toInt())
//            .setCancelable(false)
//            .setPositiveButton("OK") { dialog, id ->
//                dialogListener.onPressPositiveButton(dialog, id)
//            }
//        builder.setNegativeButton(R.string.close) { dialog, id ->
//            dialogListener.onPressNegativeButton(dialog, id)
//        }
//
//        val alert = builder.create()
//        alert.show()
//    }*/
//
//    /*fun hideBottomNavigation() {
//        activity?.bottomNavigation?.visibility = View.GONE
//    }*/
//
//   /* fun showBottomNavigation() {
//        activity?.bottomNavigation?.visibility = View.VISIBLE
//    }*/
//
//    fun hideToolbar() {
//        activity?.toolbar?.visibility = View.GONE
//    }
//
//
    fun showToolbarWithDisplayArrowBack(title: String) {
        val toolbar = activity?.toolbar
        toolbar?.visibility = View.VISIBLE
        toolbar?.title = title

        val appCompatActivity = activity as AppCompatActivity?

        appCompatActivity?.setSupportActionBar(toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(true)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun showToolbarWithDisplayArrowBackAndLogo(logo: String) {
        val toolbar = activity?.toolbar

        val appCompatActivity = activity as AppCompatActivity?

        appCompatActivity?.setSupportActionBar(toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(false)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appCompatActivity?.supportActionBar?.setDisplayUseLogoEnabled(true)


//        setImageInToolbarBar(logo, appCompatActivity)
    }

    private fun setImageInToolbarBar(
        logo: String,
        appCompatActivity: AppCompatActivity?
    ) {
        Picasso.get()
            .load(logo)
            .resize(100, 50)
            .into(object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {

                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    activity?.baseContext?.apply {
                        val drawImage = BitmapDrawable(this.resources, bitmap)
                        appCompatActivity?.supportActionBar?.setLogo(drawImage)
                    }

                }

            })
    }


}