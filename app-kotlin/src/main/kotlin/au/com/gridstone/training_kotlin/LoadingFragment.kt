package au.com.gridstone.training_kotlin

import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Stewart Collins on 2/09/17.
 */
class LoadingFragment: Fragment() {
  //Just left the pages_to_retrieve in as an option to retrieve multiple pages of
  //responses (created as I was only getting 60 mostly gallery items per request
  //initially but then found a request that gets 600 so thought one was enough)
  val PAGES_TO_RETRIEVE = 1

  lateinit var listener: OnListLoaded
  lateinit var observerDisposable: Disposable

  companion object {
    fun newInstance(): LoadingFragment{
      return LoadingFragment()
    }
  }

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    if(context is OnListLoaded){
      listener = context
    }
  }

  override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?,
      @Nullable savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    if (inflater == null) {
      return null
    }
    val view: View? = inflater.inflate(R.layout.loading_fragment, container, false)
    return view
  }

  override fun onStart() {
    super.onStart()
    val imgurApi: ImgurAPIService = ImgurAPIService.create()
    val STARTING_PAGE = 1
    getGalleryItems(STARTING_PAGE, imgurApi)
  }

  private fun getGalleryInfo(data: ImgurGalleryResponse): List<ImageDetails>{
    return data.data
  }

  private fun getGalleryItems(page: Int, imgurApi: ImgurAPIService){
    if(page > PAGES_TO_RETRIEVE) {
      loadingComlpete()
      return
    }

    observerDisposable = imgurApi.getImageDetails(page)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(
            { result ->
              printRecievedItems(result)
              loadList(getGalleryInfo(result))
              getGalleryItems(page+1,imgurApi)
            }
            ,{ error -> error.printStackTrace()}
        )
  }

  private fun loadList(list: List<ImageDetails>){
      listener.onListSectionRecieved(list.filter{ !it.is_album })
  }

  private fun printRecievedItems(items: ImgurGalleryResponse){
    val logMsg = "There are ${items.data.size} items in list"
    Log.v(MainActivity.LOG_TAG, logMsg)
    for(s in items.data){
      Log.v(MainActivity.LOG_TAG,s.title)
    }
  }

  private fun loadingComlpete(){
    observerDisposable.dispose()
    listener.onListComplete()
  }

  interface OnListLoaded{
    fun onListSectionRecieved(imageDetails: List<ImageDetails>)
    fun onListComplete()
  }
}