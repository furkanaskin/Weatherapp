package com.faskn.app.weatherapp.db.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity()
class Example() : Parcelable {
    @PrimaryKey(autoGenerate = true) var id: Long = 0
    var exampleString: String = ""

    constructor(exampleString : String) : this() {
        id = 0
        this@Example.exampleString = exampleString
    }
    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        exampleString = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(exampleString)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Example> {
        override fun createFromParcel(parcel: Parcel): Example {
            return Example(parcel)
        }

        override fun newArray(size: Int): Array<Example?> {
            return arrayOfNulls(size)
        }
    }
}