package inc.fabudi.littlesealogistician.model

import inc.fabudi.littlesealogistician.data.CargoType
import inc.fabudi.littlesealogistician.data.State
import inc.fabudi.littlesealogistician.ui.DocksAdapter

object Harbor {
    var docks = ArrayList<Dock>()
    lateinit var adapter: DocksAdapter
    private var docksUrls = arrayListOf(
        "https://m.media-amazon.com/images/I/51YF2hNuf8L.__AC_SX300_SY300_QL70_ML2_.jpg",
        "https://m.media-amazon.com/images/I/51a8KdRKSlL._AC_SX522_.jpg",
        "https://m.media-amazon.com/images/I/41T9oNebF7L._AC_.jpg"
    )

    fun notify(cargoType: CargoType) {
        for (dock in docks) {
            if (dock.cargoType == cargoType && dock.state != State.LOADING) {
                dock.start()
                break
            }
        }
    }

    fun addDock(cargoType: CargoType): Harbor {
        docks.add(
            Dock(
                docksUrls[when (cargoType) {
                    CargoType.BANANA -> 0
                    CargoType.BREAD -> 1
                    CargoType.CLOTHES -> 2
                }], cargoType
            )
        )
        return this
    }

}