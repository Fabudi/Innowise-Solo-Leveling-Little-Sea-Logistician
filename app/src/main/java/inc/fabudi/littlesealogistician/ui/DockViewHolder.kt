package inc.fabudi.littlesealogistician.ui

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import inc.fabudi.littlesealogistician.model.Dock
import inc.fabudi.littlesealogistician.R

class DockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val image = itemView.findViewById<AppCompatImageView>(R.id.dock_image)
    private val dockTypeLabel = itemView.findViewById<TextView>(R.id.dock_type)
    private val dockStateLabel = itemView.findViewById<TextView>(R.id.dock_state)
    private val dockProgressBar = itemView.findViewById<ProgressBar>(R.id.dock_progress)

    fun bind(dock: Dock) {
        Glide.with(itemView).load(dock.imageUrl).centerCrop().into(image)
        dockTypeLabel.text = dock.cargoType.name.lowercase()
        dockStateLabel.text = dock.state!!.name.lowercase()
        dockProgressBar.max = dock.maxProgress
        dockProgressBar.progress = dock.progress
    }
}