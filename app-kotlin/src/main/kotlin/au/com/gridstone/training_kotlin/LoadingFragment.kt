package au.com.gridstone.training_kotlin

import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.Timer
import kotlin.concurrent.timerTask

/**
 * Created by Stewart Collins on 2/09/17.
 */
class LoadingFragment: Fragment() {
  lateinit var listener: OnListLoaded

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
    val timer = Timer()
    timer.schedule( timerTask { listener.OnListLoaded()},3000)
  }

  public interface OnListLoaded{
    fun OnListLoaded()

  }
}