package com.gahee.clientserver

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gahee.clientserver.database.CityEntity
import kotlinx.android.synthetic.main.detail_item_layout.view.*

class CityRecyclerViewAdapter(private val cityEntities: List<CityEntity>)
    : RecyclerView.Adapter<CityDatabaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityDatabaseViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.detail_item_layout, parent, false)
        return CityDatabaseViewHolder(layout)
    }

    override fun getItemCount(): Int{
        return this.cityEntities.size
    }

    override fun onBindViewHolder(holder: CityDatabaseViewHolder, position: Int) {
        cityEntities[position].let { holder.bind(it) }
    }


}

class CityDatabaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(cityEntity: CityEntity){
        itemView.detail_city_name.text = cityEntity.cityName

        Glide.with(itemView.context)
            .load(cityEntity.cityPhotoUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(itemView.img_detail_city_photo)
    }
}
