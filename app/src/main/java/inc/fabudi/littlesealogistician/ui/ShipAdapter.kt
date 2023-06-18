package inc.fabudi.littlesealogistician.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import inc.fabudi.littlesealogistician.R
import inc.fabudi.littlesealogistician.model.Ship


class ShipAdapter(private val ships: ArrayList<Ship>? = null) :
    RecyclerView.Adapter<ShipViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.ship_item, parent, false)
        return ShipViewHolder(view)
    }


    fun removeItem(ship: Ship) {
        val index = ships!!.indexOf(ship)
        ships.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun getItemCount(): Int {
        return ships?.size ?: 0
    }

    override fun onBindViewHolder(holder: ShipViewHolder, position: Int) {
        holder.bind(ships!![position])
    }


    fun update(ship: Ship) {
        val index = ships!!.indexOf(ship)
        notifyItemChanged(index, 0)
    }

    fun addItem(ship: Ship) {
        ships!!.add(ship)
        notifyItemInserted(ships.lastIndex)
    }
}