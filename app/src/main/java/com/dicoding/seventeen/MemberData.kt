import android.os.Parcel
import android.os.Parcelable

data class MemberData(
    val name: String?,
    val realName: String?,
    val position: String?,
    val dob: String?,
    val nationality: String?,
    val description: String?,
    val photo: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(realName)
        parcel.writeString(position)
        parcel.writeString(dob)
        parcel.writeString(nationality)
        parcel.writeString(description)
        parcel.writeInt(photo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MemberData> {
        override fun createFromParcel(parcel: Parcel): MemberData {
            return MemberData(parcel)
        }

        override fun newArray(size: Int): Array<MemberData?> {
            return arrayOfNulls(size)
        }
    }
}
