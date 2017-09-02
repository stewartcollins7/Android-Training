package au.com.gridstone.training_kotlin

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by Stewart Collins on 2/09/17.
 */
class GalleryImageFragment: Fragment(){

  companion object {
    fun newInstance(): GalleryImageFragment{
      Log.v("MY_TRAINING_APP","Creating fragment")
      val fragment: GalleryImageFragment = GalleryImageFragment()
      return fragment
    }
  }


  override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?,
      @Nullable savedInstanceState: Bundle?): View? {
    Log.v("MY_TRAINING_APP","Creating view")
    if(inflater == null || container == null){
      Log.v("MY_TRAINING_APP","Invalid null argument")
      return null
    }
    val view: View = inflater.inflate(R.layout.gallery_image_fragment, container, false)
    val imageView: ImageView = view.findViewById(R.id.gallery_image_fragment_image) as ImageView
    val textView: TextView = view.findViewById(R.id.gallery_image_fragment_details) as TextView
    imageView.setImageResource(R.drawable.testimage)

    Log.v("MY_TRAINING_APP","Returning view")
    return view

  }
}