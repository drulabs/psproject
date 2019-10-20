package org.drulabs.myshelf.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("author", alternate = ["Author"]) val author: String?,
    @SerializedName("country", alternate = ["Country"]) val country: String?,
    @SerializedName("language", alternate = ["lang"]) val language: String?,
    @SerializedName("link", alternate = ["wikiurl"]) val wikiLink: String?,
    @SerializedName("page", alternate = ["pages, page_count"]) val pageCount: Int,
    @SerializedName("title", alternate = ["Title", "book_title"]) val title: String?,
    @SerializedName("year", alternate = ["Year"]) val year: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun toString(): String {
        return Gson().toJson(this)
    }

    // companion object {
    //     fun fromJSON(jsonRepresentation: String): Book {
    //         return Gson().fromJson(jsonRepresentation, Book::class.java)
    //     }
    // }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(country)
        parcel.writeString(language)
        parcel.writeString(wikiLink)
        parcel.writeInt(pageCount)
        parcel.writeString(title)
        parcel.writeInt(year)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }

        fun fromJSON(jsonRepresentation: String): Book {
            return Gson().fromJson(jsonRepresentation, Book::class.java)
        }
    }


}
