package au.com.gridstone.training_kotlin

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import au.com.gridstone.training_kotlin.RecyclerAdapter.ListImageHolder
import java.io.Console
import android.util.Log

/**
 * Created by Stewart Collins on 30/08/17.
 */

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ListImageHolder>() {

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
      Log.v("MY_TRAINING_APP","Null list item ViewGroup")
      throw NullPointerException()
    }
  }

  override fun onBindViewHolder(holder: RecyclerAdapter.ListImageHolder?, position: Int) {
    if(holder != null){
      holder.itemTitle.setText(position.toString())
      //holder.itemImage.setImageResource()
    }
  }

  override fun getItemCount(): Int {
    return 20;
  }

}




