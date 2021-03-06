package au.com.gridstone.training_kotlin

import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import au.com.gridstone.training_kotlin.RecyclerAdapter.OnImageItemSelected

/**
 * Created by Stewart Collins on 2/09/17.
 */
class GalleryListFragment: Fragment() {
  lateinit var recyclerView: RecyclerView
  lateinit var listAdapter: RecyclerAdapter
  lateinit var layoutManager: LinearLayoutManager
  lateinit var itemSelectedListener: OnImageItemSelected


  companion object {
    fun newInstance(list: List<ImageDetails>): GalleryListFragment{
      val args: Bundle = Bundle()
      var listArray = Array<ImageDetails>(list.size,{i->list[i]})
      args.putParcelableArray(MainActivity.PARECELABLE_TAG, listArray)

      val fragment = GalleryListFragment()
      fragment.arguments = args
      return fragment
    }
  }

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    if(context is OnImageItemSelected){
      itemSelectedListener = context
    }else{
      Log.e(MainActivity.LOG_TAG,"Calling activity must implement OnImageItemSelected")
    }
  }

  override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?,
      @Nullable savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)

    if(inflater == null){
      return null
    }
    val view: View? = inflater.inflate(R.layout.gallery_list_fragment, container, false)
    if(view == null){
      return view
    }

    val args: Bundle = arguments
    var imageDetails: Array<ImageDetails> =
        args.getParcelableArray(MainActivity.PARECELABLE_TAG) as Array<ImageDetails>

    listAdapter = RecyclerAdapter(itemSelectedListener, imageDetails)
    layoutManager = LinearLayoutManager(context)
    recyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
    recyclerView.layoutManager = layoutManager
    recyclerView.adapter = listAdapter

    return view
  }
}