package inc.fabudi.littlesealogistician.ui

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import inc.fabudi.littlesealogistician.R
import inc.fabudi.littlesealogistician.model.Ship
import inc.fabudi.littlesealogistician.data.State

class ShipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val image = itemView.findViewById<AppCompatImageView>(R.id.ship_image)
    private val shipLabel = itemView.findViewById<TextView>(R.id.ship_title)
    private val shipStateLabel = itemView.findViewById<TextView>(R.id.ship_state)
    private val shipProgressBar = itemView.findViewById<ProgressBar>(R.id.ship_progress)
    private val shipStateIcon = itemView.findViewById<ImageView>(R.id.ship_state_icon)


    fun bind(ship: Ship) {
        shipProgressBar.visibility = if (ship.state == State.LOADING) View.VISIBLE else View.GONE
        Glide.with(itemView).load(ship.imageUrl).centerCrop().into(image)
        shipLabel.text = ship.number
        shipStateLabel.text = ship.state!!.name.lowercase()
        shipProgressBar.progress = ship.progress
        shipProgressBar.max = ship.maxProgress
        shipStateIcon.setBackgroundResource(
            when (ship.state) {
                State.IDLE -> R.drawable.baseline_location_searching_24
                State.LOADING -> R.drawable.box
                State.WAITING -> R.drawable.time_left
                else -> R.drawable.baseline_location_searching_24
            }
        )

    }

}
