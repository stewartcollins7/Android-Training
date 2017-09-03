package au.com.gridstone.training_kotlin

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Timer
import kotlin.concurrent.timerTask

/**
 * Created by Stewart Collins on 2/09/17.
 */
class LoadingFragment: Fragment() {
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

  private fun getGalleryInfo(data: ImgurGalleryResponse): List<ImageDetails>{

    return data.data
  }

  override fun onStart() {
    super.onStart()
//    val timer = Timer()
//    timer.schedule( timerTask { listener.OnListLoaded()},3000)
    val imgurApi: ImgurAPIService = ImgurAPIService.create()
    observerDisposable = imgurApi.getImageDetails()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe({
          result ->
          Log.d("Result", "There are ${result.data.size} items in list")
          for(s in result.data){
            Log.d("Result",s.title)
          }
          loadList(result.data)
        }, { error ->
          error.printStackTrace()
        })
  }

  fun loadList(list: List<ImageDetails>){
      observerDisposable.dispose()
      listener.onListLoaded(list.filter{ !it.is_album })
  }

  public interface OnListLoaded{
    fun onListLoaded(imageDetails: List<ImageDetails>)

  }
}