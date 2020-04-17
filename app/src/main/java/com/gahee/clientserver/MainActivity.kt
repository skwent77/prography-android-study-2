package com.gahee.clientserver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gahee.clientserver.database.CityRoomViewModel
import com.gahee.clientserver.network.CityViewModel
import com.gahee.clientserver.network.NetworkState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var cityViewModel : CityViewModel
    private lateinit var cityPagerAdapter : CityPagerAdapter
    private lateinit var cityRoomViewModel: CityRoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        cityViewModel = ViewModelProviders.of(this).get(CityViewModel::class.java)
        cityRoomViewModel = ViewModelProviders.of(this).get(CityRoomViewModel::class.java)

        cityViewModel.fetchCitiesData()
        cityViewModel.citiesLiveData.observe(this, Observer{cities ->
            cityPagerAdapter = CityPagerAdapter(cities, cityRoomViewModel)
            view_pager_cities.adapter = cityPagerAdapter

            cityRoomViewModel.allCities.observe(this, Observer { cityEntities ->
                cityPagerAdapter.cityEntities = cityEntities

            })
        })

        cityViewModel.networkState.observe(this, Observer{
            when(it){
                NetworkState.LOADING -> {
                    main_progress_bar.visibility = View.VISIBLE
                }
                NetworkState.DONE -> {
                    main_progress_bar.visibility = View.GONE
                }
                NetworkState.ERROR -> {
                    Toast.makeText(this@MainActivity, "Network Error Occurred!", Toast.LENGTH_LONG)
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_check_database -> {
                val intent = Intent(this, DetailActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return false
    }


}
