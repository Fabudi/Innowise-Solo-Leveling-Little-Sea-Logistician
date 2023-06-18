package inc.fabudi.littlesealogistician.model

import inc.fabudi.littlesealogistician.data.Capacity
import inc.fabudi.littlesealogistician.data.CargoType
import inc.fabudi.littlesealogistician.data.State

class Ship(
    val imageUrl: String,
    val capacity: Capacity,
    val cargoType: CargoType,
    var number: String,
    var state: State? = State.WAITING
){
    var progress = 0
    var maxProgress = 0
}

