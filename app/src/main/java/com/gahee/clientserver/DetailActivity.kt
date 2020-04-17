package com.gahee.clientserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gahee.clientserver.database.CityRoomViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var cityRoomViewModel: CityRoomViewModel
    private lateinit var cityRecyclerViewAdapter: CityRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        cityRoomViewModel = ViewModelProviders.of(this).get(CityRoomViewModel::class.java)

        cityRoomViewModel.allCities.observe(this, Observer{ cityEntities ->
            //RecyclerView Adapter
            cityRecyclerViewAdapter = CityRecyclerViewAdapter(cityEntities)
            rv_detail.apply {
                adapter = cityRecyclerViewAdapter
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@DetailActivity)
            }

        })
    }
}
