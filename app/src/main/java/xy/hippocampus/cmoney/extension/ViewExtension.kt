package xy.hippocampus.cmoney.extension

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun View.setBackgroundColorByColorRes(@ColorRes colorRes: Int) =
    setBackgroundColor(ContextCompat.getColor(context, colorRes))