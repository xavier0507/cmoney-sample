package xy.hippocampus.cmoney.extension

import android.app.Activity
import android.widget.Toast
import xy.hippocampus.cmoney.R

fun Activity.showCommonToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Activity.slideInRight() {
    overridePendingTransition(R.anim.slide_in_right, R.anim.fix_center)
}

fun Activity.slideOutRight() {
    overridePendingTransition(R.anim.fix_center, R.anim.slide_out_right)
}