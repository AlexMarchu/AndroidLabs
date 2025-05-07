import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.labs.R

class ActivityTypeAdapter(
    private val items: List<String>,
    private val onItemSelected: (String) -> Unit
) : RecyclerView.Adapter<ActivityTypeAdapter.ViewHolder>() {

    private var selectedPosition = -1

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.activityNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_activity_type, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position]

        holder.itemView.isSelected = position == selectedPosition

        holder.itemView.setOnClickListener {
            selectedPosition = position
            onItemSelected(items[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = items.size

    fun getSelectedItem(): String? {
        return if (selectedPosition != -1) items[selectedPosition] else null
    }
}