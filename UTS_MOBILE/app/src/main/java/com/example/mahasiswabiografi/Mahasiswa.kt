package com.example.mahasiswabiografi

import android.os.Parcel
import android.os.Parcelable

data class Mahasiswa(
    val nama: String,
    val nim: String,
    val jurusan: String,
    val foto: Int,
    val tanggalLahir: String,
    val asal: String,
    val hobi: String,
    val minat: String,
    val pengalamanProyek: String,
    val pengalamanOrganisasi: String,
    val prestasi: String,
    val citaCita: String,
    val bio: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nama)
        parcel.writeString(nim)
        parcel.writeString(jurusan)
        parcel.writeInt(foto)
        parcel.writeString(tanggalLahir)
        parcel.writeString(asal)
        parcel.writeString(hobi)
        parcel.writeString(minat)
        parcel.writeString(pengalamanProyek)
        parcel.writeString(pengalamanOrganisasi)
        parcel.writeString(citaCita)
        parcel.writeString(prestasi)
        parcel.writeString(bio)

    }


    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Mahasiswa> {
        override fun createFromParcel(parcel: Parcel): Mahasiswa {
            return Mahasiswa(parcel)
        }

        override fun newArray(size: Int): Array<Mahasiswa?> {
            return arrayOfNulls(size)
        }
    }
}

