package inc.fabudi.littlesealogistician.model

import android.util.Log
import inc.fabudi.littlesealogistician.data.Capacity
import inc.fabudi.littlesealogistician.data.CargoType
import inc.fabudi.littlesealogistician.data.State
import inc.fabudi.littlesealogistician.ui.ShipAdapter
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.Random
import kotlin.math.abs

object Tunnel {
    lateinit var adapter: ShipAdapter
    private var cargoType = CargoType.BANANA
    var ships = ArrayList<Ship>()
    private val mutex = Mutex()
    private val shipUrls = arrayListOf(
        "https://m.media-amazon.com/images/I/51YF2hNuf8L.__AC_SX300_SY300_QL70_ML2_.jpg",
        "https://m.media-amazon.com/images/I/51a8KdRKSlL._AC_SX522_.jpg",
        "https://m.media-amazon.com/images/I/41T9oNebF7L._AC_.jpg"
    )

    init {
        for (i in 0..4) {
            val newShip = newShip()
            ships.add(newShip)
            Log.d("Tunnel", newShip.toString())
            Harbor.notify(ships.last().cargoType)
        }
    }

    suspend fun askForShip(cargoType: CargoType): Ship? {
        mutex.withLock {
            val ship: Ship
            try {
                ship = ships.first { it.cargoType == cargoType && it.state != State.LOADING }
                ship.state = State.LOADING
            } catch (e: NoSuchElementException) {
                return null
            }
            return ship
        }
    }

    private fun newShip(): Ship {
        cargoType = randomCargoType()
        return Ship(shipImageUrl(), randomCapacity(), cargoType, randomNumber())
    }

    private fun randomNumber() = when (cargoType) {
        CargoType.BANANA -> "BA-" + abs(Random().nextInt() % 100_000)
        CargoType.BREAD -> "BR-" + abs(Random().nextInt() % 100_000)
        CargoType.CLOTHES -> "CL-" + abs(Random().nextInt() % 100_000)
    }

    private fun randomCargoType() = CargoType.values()[Random().nextInt(CargoType.values().size)]

    private fun randomCapacity() = Capacity.values()[Random().nextInt(Capacity.values().size)]

    private fun shipImageUrl() = when (cargoType) {
        CargoType.BANANA -> shipUrls[0]
        CargoType.BREAD -> shipUrls[1]
        CargoType.CLOTHES -> shipUrls[2]
    }

    suspend fun report(ship: Ship) {
        mutex.withLock {
            adapter.removeItem(ship)
            adapter.addItem(newShip())
            Harbor.notify(ships.last().cargoType)
        }
    }
}