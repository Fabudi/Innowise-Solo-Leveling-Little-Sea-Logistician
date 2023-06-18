package inc.fabudi.littlesealogistician.model

import inc.fabudi.littlesealogistician.data.CargoType
import inc.fabudi.littlesealogistician.data.State
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class Dock(
    val imageUrl: String, val cargoType: CargoType, var state: State? = State.IDLE
) {
    var progress = 0
    var maxProgress = 0

    private var job: Job? = null

    fun start() {
        state = State.LOADING
        job = GlobalScope.launch(Dispatchers.IO, start = CoroutineStart.LAZY) {
            while (true) {
                if (!processShips()) {
                    state = State.IDLE
                    pause()
                    break
                }
            }
        }
        job!!.start()
    }

    private fun pause() = job?.cancel()

    private suspend fun processShips(): Boolean = runBlocking {
        val ship = Tunnel.askForShip(cargoType) ?: return@runBlocking false
        GlobalScope.launch {
            maxProgress = ship.capacity.weight / 10
            ship.maxProgress = ship.capacity.weight / 10
            while (progress <= maxProgress) {
                delay(1000)
                ship.progress += 1
                progress += 1
                withContext(Dispatchers.Main) {
                    Tunnel.adapter.update(ship)
                    Harbor.adapter.update(this@Dock)
                }
            }
            withContext(Dispatchers.Main) {
                Tunnel.report(ship)
                Tunnel.adapter.update(ship)
            }
            progress = 0
        }.join()
        return@runBlocking true
    }

}
