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
import com.squareup.picasso.Picasso
import java.util.Date

/**
 * Created by Stewart Collins on 2/09/17.
 */
class GalleryImageFragment: Fragment(){


  companion object {
    fun newInstance(details: ImageDetails): GalleryImageFragment{
      Log.v(MainActivity.LOG_TAG,"Creating list item fragment")
      val fragment: GalleryImageFragment = GalleryImageFragment()
      var args = Bundle()
      args.putParcelable(MainActivity.PARECELABLE_TAG,details)
      fragment.arguments = args
      return fragment
    }
  }


  override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?,
      @Nullable savedInstanceState: Bundle?): View? {
    if(inflater == null || container == null){
      Log.e(MainActivity.LOG_TAG,"Invalid null argument")
      return null
    }
    val view: View = inflater.inflate(R.layout.gallery_image_fragment, container, false)
    val details = arguments.getParcelable<ImageDetails>(MainActivity.PARECELABLE_TAG)
    setDetailsText(details, view)

    val imageView: ImageView = view.findViewById(R.id.gallery_image_fragment_image) as ImageView
    Picasso.with(container.context).load(details.link).into(imageView)

    return view
  }

  fun setDetailsText(details: ImageDetails, view: View){
    val titleTextView: TextView = view.findViewById(R.id.gallery_image_titles) as TextView
    val timeTextView: TextView = view.findViewById(R.id.gallery_image_time_elapsed) as TextView
    val heightView: TextView = view.findViewById(R.id.gallery_image_height) as TextView
    val widthView: TextView = view.findViewById(R.id.gallery_image_width) as TextView
    val viewcountTextView: TextView = view.findViewById(R.id.gallery_image_viewcount) as TextView

    titleTextView.text = details.title
    timeTextView.text = getTimeElapsed(details.datetime)
    heightView.text = details.height.toString() + " px"
    widthView.text = details.width.toString() + " px"
    viewcountTextView.text = details.views.toString()
  }

  fun getTimeElapsed(datePosted: Int): String{
    val now = Date()
    val elapsedSeconds = (now.time/1000) - datePosted
    val elapsedMinutes = elapsedSeconds/60
    if(elapsedMinutes <= 0){
      if(elapsedSeconds > 1){
        return elapsedSeconds.toString() + " seconds ago"
      }else{
        return "1 second ago"
      }
    }
    val elapsedHours = elapsedMinutes/60
    if(elapsedHours <= 0){
      if(elapsedMinutes > 1){
        return elapsedMinutes.toString() + " minutes ago"
      }else{
        return "1 minute ago"
      }
    }
    val elapsedDays = elapsedHours/24
    if(elapsedDays <= 0){
      if(elapsedHours > 1){
        return elapsedHours.toString() + " hours ago"
      }else{
        return "1 hour ago"
      }
    }
    val elapsedWeeks = elapsedDays/7
    if(elapsedWeeks <= 0){
      if(elapsedDays > 1){
        return elapsedDays.toString() + " days ago"
      }else{
        return "1 day ago"
      }
    }
    val elapsedYears = elapsedDays/365
    if(elapsedYears <= 0){
      if(elapsedWeeks > 1){
        return elapsedSeconds.toString() + " weeks ago"
      }else{
        return "1 week ago"
      }

    }else{
      if(elapsedYears > 1){
        return elapsedYears.toString() + " years ago"
      }else{
        return "1 year ago"
      }
    }
  }
}