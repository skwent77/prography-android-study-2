package com.gahee.clientserver

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gahee.clientserver.database.CityEntity
import com.gahee.clientserver.database.CityRoomViewModel
import com.gahee.clientserver.network.City
import kotlinx.android.synthetic.main.city_item_view_pager_layout.view.*



class CityPagerAdapter(
    private val cities : List<City>,
    private val cityRoomViewModel: CityRoomViewModel
) : RecyclerView.Adapter<CityPagerAdapter.CityPagerViewHolder>(){

    var cityEntities : List<CityEntity> = emptyList()
        set(value) {
            field = value
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityPagerViewHolder {
        return CityPagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.city_item_view_pager_layout, parent, false))
    }

    override fun onBindViewHolder(holder: CityPagerViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    inner class CityPagerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bind(city : City){
            itemView.tv_city_name.text = String.format(itemView.context.getString(R.string.cityname), city.number, city.cityName)
            itemView.tv_city_url.text = city.url

            Glide.with(itemView)
                .load(city.cityPhotoUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(itemView.img_city_background)

            if(isAlreadyInserted(city)){
                setFabImageDrawable(true)
            }else{
                setFabImageDrawable(false)
            }

            itemView.fab_city_view_pager.setOnClickListener {

                var cityEntity = CityEntity(
                    city.number,
                    city.cityName,
                    city.url,
                    city.cityPhotoUrl
                )

                if(isAlreadyInserted(city)){
                    cityRoomViewModel.delete(cityEntity.number)
                    setFabImageDrawable(false)
                    Log.d("DEBUG", "DELETED")
                }else{
                    cityRoomViewModel.insert(cityEntity)
                    setFabImageDrawable(true)
                    Log.d("DEBUG", "INSERTED")
                }

            }
        }

        private fun isAlreadyInserted(city : City) : Boolean{
            cityEntities.forEach{
                if(it.cityName == city.cityName){
                    return true
                }
            }
            return false
        }

        private fun setFabImageDrawable(isMarked : Boolean){
            when(isMarked){
                true ->  {
                    itemView.fab_city_view_pager.isSelected = true
                }
                false -> {
                    itemView.fab_city_view_pager.isSelected = false
                }
            }
        }
    }

}
