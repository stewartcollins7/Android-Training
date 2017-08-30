package au.com.gridstone.training_kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {

  lateinit var recyclerView: RecyclerView
  lateinit var listAdapter: RecyclerAdapter
  lateinit var layoutManager: LinearLayoutManager


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    listAdapter = RecyclerAdapter()
    layoutManager = LinearLayoutManager(this)
    recyclerView = findViewById(R.id.recycler_view) as RecyclerView
    recyclerView.layoutManager = layoutManager;
    recyclerView.adapter = listAdapter
  }
}
