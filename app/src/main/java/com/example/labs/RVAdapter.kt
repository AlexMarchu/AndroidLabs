package com.example.labs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class RVAdapter(private var activities: List<ActivityItem> = emptyList()) :
    RecyclerView.Adapter<RVAdapter.ActivityViewHolder>() {

    fun updateData(newActivities: List<ActivityItem>) {
        activities = newActivities
        notifyDataSetChanged()
    }

//    private val activities = listOf(
//        ActivityItem(
//            user = "me",
//            type = ActivityType.RUNNING,
//            startTime = System.currentTimeMillis() - 86400000, // 1 день назад
//            endTime = System.currentTimeMillis() - 82800000 // 1 день + 1 час
//        ),

//        ActivityItem( "14.32 км", "2 часа 46 минут", "Серфинг", "14 часов назад"),
//        ActivityItem( "1000 м", "60 минут", "Велосипед", "29.05.2022"),
//        ActivityItem( "5.5 км", "45 минут", "Бег", "12.04.2022"),
//        ActivityItem( "3.2 км", "30 минут", "Ходьба", "10.04.2022"),
//        ActivityItem( "8.7 км", "1 час 15 минут", "Велосипед", "05.04.2022"),
//        ActivityItem( "12.0 км", "2 часа", "Плавание", "01.04.2022"),
//        ActivityItem( "6.5 км", "55 минут", "Бег", "28.03.2022"),
//        ActivityItem( "4.2 км", "40 минут", "Ходьба", "25.03.2022"),
//        ActivityItem( "10.3 км", "1 час 30 минут", "Велосипед", "20.03.2022"),
//        ActivityItem( "7.8 км", "1 час 5 минут", "Бег", "15.03.2022"),
//        ActivityItem( "5.0 км", "50 минут", "Ходьба", "10.03.2022")
//    )

    class ActivityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val distance: TextView = view.findViewById(R.id.distance)
        val time: TextView = view.findViewById(R.id.time)
        val activityType: TextView = view.findViewById(R.id.activityType)
        val timestamp: TextView = view.findViewById(R.id.timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = activities[position]
        holder.distance.text = "%.2f км".format(calculateDistance(activity))
        holder.time.text = formatDuration(activity.endTime - activity.startTime)
        holder.activityType.text = activity.type.displayName
        holder.timestamp.text = formatDate(activity.startTime)
    }

    override fun getItemCount(): Int {
        return activities.size
    }

    private fun calculateDistance(activity: ActivityItem): Double {
        val hours = (activity.endTime - activity.startTime).toDouble() / 3600000
        return when (activity.type) {
            ActivityType.RUNNING -> hours * 10
            ActivityType.BICYCLE -> hours * 20
            ActivityType.WALKING -> hours * 5
        }
    }

    private fun formatDuration(millis: Long): String {
        val seconds = millis / 1000
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        return when {
            hours > 0 -> "$hours ч ${minutes} мин"
            else -> "$minutes мин"
        }
    }

    private fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}