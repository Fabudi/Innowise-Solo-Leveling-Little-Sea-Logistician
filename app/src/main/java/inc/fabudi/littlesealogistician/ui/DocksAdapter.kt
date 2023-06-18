package inc.fabudi.littlesealogistician.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import inc.fabudi.littlesealogistician.R
import inc.fabudi.littlesealogistician.model.Dock


class DocksAdapter(private val docks: ArrayList<Dock>? = null) :
    RecyclerView.Adapter<DockViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DockViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.dock_item, parent, false)
        return DockViewHolder(view)
    }

    override fun getItemCount(): Int {
        return docks?.size ?: 0
    }

    override fun onBindViewHolder(holder: DockViewHolder, position: Int) {
        holder.bind(docks!![position])
    }

    fun update(dock: Dock) = notifyItemChanged(docks!!.indexOf(dock), 0)

}