package com.dbdroid.weathernow.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineExceptionHandler
import com.dbdroid.weathernow.R
import com.dbdroid.weathernow.models.GeoWeatherModel
import com.dbdroid.weathernow.models.GeocodingData.GeocodingResult
import com.dbdroid.weathernow.utils.GeocodingHelper
import com.dbdroid.weathernow.utils.SharedPreferencesHelper
import com.dbdroid.weathernow.viewmodels.WeatherViewModel

class LocationRVAdapter(private val locationRVModalList: ArrayList<GeocodingResult>, private val lat: Double, private val long: Double, private val coroutineExceptionHandler: CoroutineExceptionHandler, private val weatherViewModel: WeatherViewModel) : RecyclerView.Adapter<LocationRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cities_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return locationRVModalList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cityName.text = locationRVModalList[position].name
        val cityAddress = listOf(
            locationRVModalList[position].admin4,
            locationRVModalList[position].admin3,
            locationRVModalList[position].admin2,
            locationRVModalList[position].admin1,
            locationRVModalList[position].country
        ).filterNot { it.isNullOrBlank() }.joinToString(", ")

        holder.cityAddress.text = cityAddress

        holder.addRemoveCity.setOnClickListener {
            holder.addRemoveCity.setImageResource(R.drawable.icon_right_arrow)
            val context = holder.itemView.context

            val geoWeatherModel = GeoWeatherModel(
                listOf(
                    locationRVModalList[position].latitude,
                    locationRVModalList[position].longitude
                ),
                locationRVModalList[position].name,
                locationRVModalList[position].country,
                locationRVModalList[position].timezone
            )

            weatherViewModel.getGeoWeather(coroutineExceptionHandler, geoWeatherModel)

            val sharedPreferencesHelper = SharedPreferencesHelper(context)
            sharedPreferencesHelper.saveGeocodingData(geoWeatherModel)

            if (context is Activity) {
                context.onBackPressed()
            }
        }

        val samePlace = GeocodingHelper.areLocationsSame(lat, long, locationRVModalList[position].latitude, locationRVModalList[position].longitude)

        if(samePlace) {
            holder.addRemoveCity.setImageResource(R.drawable.icon_right_arrow)
        } else {
            holder.addRemoveCity.setImageResource(R.drawable.icon_add)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName: TextView = itemView.findViewById(R.id.cityName)
        val cityAddress: TextView = itemView.findViewById(R.id.cityAddress)
        val addRemoveCity: ImageButton = itemView.findViewById(R.id.addRemoveCity)
    }
}