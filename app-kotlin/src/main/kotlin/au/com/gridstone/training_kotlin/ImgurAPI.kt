package au.com.gridstone.training_kotlin

/**
 * Created by Stewart Collins on 3/09/17.
 */

import io.reactivex.Observable
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.Call;
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import retrofit2.http.Headers

interface ImgurAPIService {

  @Headers("Authorization: Client-ID 3436c108ccc17d3")
  @GET("3/gallery/hot")
  fun getImageDetails(): Observable<ImgurGalleryResponse>

  companion object Factory{
    fun create(): ImgurAPIService {
      val retrofit = Retrofit.Builder()
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create())
          .baseUrl("https://api.imgur.com/")
          .build()

      return retrofit.create(ImgurAPIService::class.java)

    }
  }

}