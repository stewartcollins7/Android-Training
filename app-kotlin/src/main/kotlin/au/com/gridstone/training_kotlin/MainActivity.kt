package au.com.gridstone.training_kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import au.com.gridstone.training_kotlin.LoadingFragment.OnListLoaded
import au.com.gridstone.training_kotlin.RecyclerAdapter.OnImageItemSelected
import java.util.LinkedList


class MainActivity : AppCompatActivity(), OnImageItemSelected, OnListLoaded {
  var imageDetails: List<ImageDetails> = LinkedList<ImageDetails>()
  val DETAILS_FRAGMENT_ID: String = "galleryItemFragment"
  val LIST_FRAGMENT_ID: String = "galleryListFragment"
  val LOADING_FRAGMENT_ID: String = "loadingFragment"

  companion object {
    val LOG_TAG = "KotlinTraining"
    val PARECELABLE_TAG = "ImageDetails"
  }

  override fun onImageItemSelected(details: ImageDetails) {
    Log.v(LOG_TAG,"Adding list item fragment")
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.main_layout, GalleryImageFragment.newInstance(details), DETAILS_FRAGMENT_ID)
        .addToBackStack(null)
        .commit()
  }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if(savedInstanceState == null){
      getSupportFragmentManager()
          .beginTransaction()
          .add(R.id.main_layout, LoadingFragment.newInstance(), LOADING_FRAGMENT_ID)
          .commit()
    }
  }

  override fun onListComplete() {
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.main_layout, GalleryListFragment.newInstance(imageDetails), LIST_FRAGMENT_ID)
        .commit()
  }

  override fun onListSectionRecieved(imageDetails: List<ImageDetails>) {
    this.imageDetails = this.imageDetails.plus(imageDetails)
  }
}
