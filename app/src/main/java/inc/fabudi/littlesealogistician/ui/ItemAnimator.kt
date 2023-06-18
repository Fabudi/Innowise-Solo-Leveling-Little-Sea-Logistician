package inc.fabudi.littlesealogistician.ui

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

class ItemAnimator : DefaultItemAnimator() {

    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder,
        newHolder: RecyclerView.ViewHolder,
        preInfo: ItemHolderInfo,
        postInfo: ItemHolderInfo
    ): Boolean {
        if (preInfo is DockItemHolderInfo) {
            dispatchAnimationFinished(newHolder)
            if (preInfo.changed) return true
        }
        return super.animateChange(oldHolder, newHolder, preInfo, postInfo)
    }

    class DockItemHolderInfo(val changed: Boolean) : ItemHolderInfo()

    override fun recordPreLayoutInformation(
        state: RecyclerView.State,
        viewHolder: RecyclerView.ViewHolder,
        changeFlags: Int,
        payloads: MutableList<Any>
    ): ItemHolderInfo {
        if (changeFlags == FLAG_CHANGED) {
            for (payload in payloads) {
                if (payload as? Int == 0) {
                    return DockItemHolderInfo(true)
                }
            }
        }
        return super.recordPreLayoutInformation(state, viewHolder, changeFlags, payloads)
    }
}