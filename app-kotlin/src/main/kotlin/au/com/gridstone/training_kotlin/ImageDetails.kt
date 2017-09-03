package au.com.gridstone.training_kotlin

/**
 * Created by Stewart Collins on 3/09/17.
 */

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ImageDetails(

    val title: String,
    val datetime: Int,
    val width: Int,
    val height: Int,
    val views: Int,
    val link: String,
    val is_album: Boolean

) : Parcelable {
  constructor(source: Parcel) : this(
      source.readString(),
      source.readInt(),
      source.readInt(),
      source.readInt(),
      source.readInt(),
      source.readString(),
      1 == source.readInt()
  )

  override fun describeContents() = 0

  override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
    writeString(title)
    writeInt(datetime)
    writeInt(width)
    writeInt(height)
    writeInt(views)
    writeString(link)
    writeInt((if (is_album) 1 else 0))
  }

  companion object {
    @JvmField val CREATOR: Parcelable.Creator<ImageDetails> = object : Parcelable.Creator<ImageDetails> {
      override fun createFromParcel(source: Parcel): ImageDetails = ImageDetails(source)
      override fun newArray(size: Int): Array<ImageDetails?> = arrayOfNulls(size)
    }
  }
}

data class ImgurGalleryResponse(val data: List<ImageDetails>)
