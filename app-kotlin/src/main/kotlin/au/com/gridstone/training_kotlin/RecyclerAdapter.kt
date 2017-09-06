package au.com.gridstone.training_kotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.util.Log
import com.squareup.picasso.Picasso

/**
 * Created by Stewart Collins on 30/08/17.
 */

class RecyclerAdapter(val itemListener: OnImageItemSelected, val imageDetails: Array<ImageDetails>) : RecyclerView.Adapter<RecyclerAdapter.ListImageHolder>() {

  class ListImageHolder(parent: View) : RecyclerView.ViewHolder(parent) {
    var itemImage : ImageView
    var itemTitle : TextView

    init{
      itemImage = parent.findViewById(R.id.list_image) as ImageView
      itemTitle = parent.findViewById(R.id.list_title) as TextView
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerAdapter.ListImageHolder{
    if(parent != null){
      val inflatedView : View = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_image_list_item, parent, false)
      return ListImageHolder(inflatedView)
    }else{
      Log.v(MainActivity.LOG_TAG,"Null list item ViewGroup")
      throw NullPointerException()
    }
  }

  override fun onBindViewHolder(holder: RecyclerAdapter.ListImageHolder?, position: Int) {
    if(holder != null) {
      holder.itemTitle.setText(imageDetails[position].title)
      Picasso.with(holder.itemView.context)
          .load(imageDetails[position].link)
          .into(holder.itemImage)
      holder.itemView.setOnClickListener() {
        itemListener.onImageItemSelected(imageDetails[position])
      }
    }
  }

  override fun getItemCount(): Int {
    return imageDetails.size;
  }

  interface OnImageItemSelected{
    fun onImageItemSelected(details: ImageDetails)
  }

}




