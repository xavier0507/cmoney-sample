package xy.hippocampus.cmoney.view.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val totalItemCount = layoutManager.itemCount
        val lastCompletelyVisibleItemPosition =
            layoutManager.findLastCompletelyVisibleItemPosition()

        if (shouldLoadMoreItems(lastCompletelyVisibleItemPosition, totalItemCount, dy)) {
            loadMoreItems(totalItemCount)
        }
    }

    private fun shouldLoadMoreItems(lastCompletelyVisibleItemPosition: Int, total: Int, dy: Int) =
        total < MAX_TOTAL_COUNT && lastCompletelyVisibleItemPosition == (total - 1) && isScrollingUp(dy)

    private fun isScrollingUp(dy: Int) = dy > 0

    abstract fun loadMoreItems(total: Int)

    companion object {

        private const val MAX_TOTAL_COUNT = 200

        fun createPaginationScrollListener(
            layoutManager: LinearLayoutManager,
            listener: (Int) -> Unit
        ): PaginationScrollListener {
            return object : PaginationScrollListener(layoutManager) {
                override fun loadMoreItems(total: Int) {
                    listener(total)
                }
            }
        }
    }
}