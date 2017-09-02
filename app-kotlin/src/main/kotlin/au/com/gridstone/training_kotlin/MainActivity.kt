package au.com.gridstone.training_kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import au.com.gridstone.training_kotlin.LoadingFragment.OnListLoaded
import au.com.gridstone.training_kotlin.RecyclerAdapter.OnImageItemSelected


class MainActivity : AppCompatActivity(), OnImageItemSelected, OnListLoaded {
  override fun OnListLoaded() {
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.main_layout, GalleryListFragment.newInstance(), LIST_FRAGMENT_ID)
        .commit()
  }

  val DETAILS_FRAGMENT_ID: String = "galleryItemFragment"
  val LIST_FRAGMENT_ID: String = "galleryListFragment"
  val LOADING_FRAGMENT_ID: String = "loadingFragment"

  override fun onImageItemSelected(url: String) {
    Log.v("MY_TRAINING_APP","Adding fragment")
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.main_layout, GalleryImageFragment.newInstance(), DETAILS_FRAGMENT_ID)
        .addToBackStack(null)
        .commit()
    Log.v("MY_TRAINING_APP","Fragment added")
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
}
