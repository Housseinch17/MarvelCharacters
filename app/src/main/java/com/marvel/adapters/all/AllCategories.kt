import android.os.Parcel
import android.os.Parcelable
import com.marvel.beans.Results

class AllCategories(
    var title: String,
    var categories: ArrayList<Results>
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AllCategories

        if (title != other.title) return false
        if (categories != other.categories) return false

        return true
    }

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createTypedArrayList(Results.CREATOR) as ArrayList<Results>
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeTypedList(categories)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AllCategories> {
        override fun createFromParcel(parcel: Parcel): AllCategories {
            return AllCategories(parcel)
        }

        override fun newArray(size: Int): Array<AllCategories?> {
            return arrayOfNulls(size)
        }
    }
}
