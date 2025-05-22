package com.example.mahasiswabiografi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val btnBack: TextView = findViewById(R.id.button_kembali)
        btnBack.setOnClickListener {
            finish()
        }


        val getData = intent.getParcelableExtra<Mahasiswa>("mahasiswa")

        if (getData != null) {
            val detailNama: TextView = findViewById(R.id.detailNama)
            val detailNim: TextView = findViewById(R.id.detailNim)
            val detailJurusan: TextView = findViewById(R.id.detailJurusan)
            val detailBio: TextView = findViewById(R.id.detailBio)
            val detailFoto: ImageView = findViewById(R.id.detailFoto)
            val detailTanggalLahir: TextView = findViewById(R.id.detailTanggalLahir)
            val detailHobi: TextView = findViewById(R.id.detailHobi)
            val detailCitaCita: TextView = findViewById(R.id.detailCitaCita)
            val detailAsal: TextView = findViewById(R.id.detailAsal)
            val detailMinat: TextView = findViewById(R.id.detailMinat)
            val detailPengalamanProyek: TextView = findViewById(R.id.detailPengalamanProyek)
            val detailPengalamanOrganisasi: TextView = findViewById(R.id.detailPengalamanOrganisasi)
            val detailPrestasi: TextView = findViewById(R.id.detailPrestasi)


            detailNama.text = getData.nama
            detailNim.text = getData.nim
            detailJurusan.text = getData.jurusan
            detailBio.text = getData.bio
            detailFoto.setImageResource(getData.foto)
            detailTanggalLahir.text = "Tanggal Lahir: ${getData.tanggalLahir}"
            detailHobi.text = "Hobi: ${getData.hobi}"
            detailCitaCita.text = "Cita-cita: ${getData.citaCita}"
            detailAsal.text = "Asal: ${getData.asal}"
            detailMinat.text = "Minat: ${getData.minat}"
            detailPengalamanProyek.text = "Pengalaman Proyek: ${getData.pengalamanProyek}"
            detailPengalamanOrganisasi.text = "Pengalaman Organisasi: ${getData.pengalamanOrganisasi}"
            detailPrestasi.text = "Prestasi: ${getData.prestasi}"


        } else {

            Toast.makeText(this, "Data mahasiswa tidak tersedia", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
