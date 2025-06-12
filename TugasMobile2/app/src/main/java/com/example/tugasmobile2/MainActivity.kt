package com.example.beedataapi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.tugasmobile2.R
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var txtHasil: TextView
    private lateinit var btnAmbil: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtHasil = findViewById(R.id.txtHasil)
        btnAmbil = findViewById(R.id.btnAmbil)

        btnAmbil.setOnClickListener {
            ambilDataDariApi()
        }
    }
    private fun ambilDataDariApi() {
        val url = "https://datamahasiswa.free.beeceptor.com/data"
        val requestQueue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url,
            { response ->
                val json = JSONObject(response)
                val nama = json.getString("nama")
                val nim = json.getString("nim")
                val jurusan = json.getString("jurusan")
                val angkatan = json.getInt("angkatan")

                val hasil = "Nama: $nama\nNIM: $nim\nJurusan: $jurusan\nAngkatan: $angkatan"
                txtHasil.text = hasil
            },
            { error ->
                txtHasil.text = "Gagal: ${error.message}"
            })

        requestQueue.add(request)
    }
}
