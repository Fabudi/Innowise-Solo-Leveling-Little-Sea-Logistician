package inc.fabudi.littlesealogistician

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import inc.fabudi.littlesealogistician.data.CargoType
import inc.fabudi.littlesealogistician.model.Harbor
import inc.fabudi.littlesealogistician.model.Tunnel
import inc.fabudi.littlesealogistician.ui.DocksAdapter
import inc.fabudi.littlesealogistician.ui.ItemAnimator
import inc.fabudi.littlesealogistician.ui.ShipAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var harborList: RecyclerView
    private lateinit var tunnelList: RecyclerView
    private lateinit var tunnelAdapter: ShipAdapter
    private lateinit var harborAdapter: DocksAdapter
    private lateinit var shipsLabel: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        harborList = findViewById(R.id.harbor)
        tunnelList = findViewById(R.id.tunnel)
        shipsLabel = findViewById(R.id.ship_label)
        initHarbor()
        initTunnel()
    }

    private fun initTunnel() {
        tunnelAdapter = ShipAdapter(Tunnel.ships)
        tunnelList.itemAnimator = ItemAnimator()
        tunnelList.adapter = tunnelAdapter
        Tunnel.adapter = tunnelAdapter
    }

    private fun initHarbor() {
        Harbor.addDock(CargoType.BANANA).addDock(CargoType.CLOTHES).addDock(CargoType.BREAD)
        harborAdapter = DocksAdapter(Harbor.docks)
        harborList.itemAnimator = ItemAnimator()
        harborList.adapter = harborAdapter
        Harbor.adapter = harborAdapter
    }
}