package xy.hippocampus.cmoney.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import xy.hippocampus.cmoney.share.Verifiable

@Parcelize
data class PhotoData(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : Verifiable, Parcelable {

    override fun isValid(): Boolean = this != defaultInstance

    fun getIdString() = id.toString()

    companion object {
        val defaultInstance = PhotoData(0, 0, "", "", "")
    }
}