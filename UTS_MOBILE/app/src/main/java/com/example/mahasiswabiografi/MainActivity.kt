package com.example.mahasiswabiografi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mahasiswaList: ArrayList<Mahasiswa>
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        mahasiswaList = ArrayList()
        loadData()

        adapter = Adapter(mahasiswaList)
        recyclerView.adapter = adapter

        adapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("mahasiswa", it)
            startActivity(intent)
        }
    }

    private fun loadData() {
        mahasiswaList.add(
            Mahasiswa("Dina Izzati Elfadheya", "2310817120001", "Teknologi Informasi", R.drawable.profildina,
                "31 Januari 2005", "Banjarbaru, Kalimantan Selatan", "Membaca Fiksi, Travelling ", "Administrasi Data",
                "Analisis Penerapan Konsep Aljabar Linier dalam ALgoritma AES pada Kriptografi", "Sekretaris Umum HMTI 2025",
                "Finalis lomba AI tingkat nasional", "Data Analys",
                "Dina adalah mahasiswa yang sangat teliti dalam setiap pekerjaan yang dilakukan. Ia tertarik dalam bidang analisis Data. Di luar kelas, Dina juga aktif menulis artikel teknologi dan menjadi mentor bagi mahasiswa baru. Ia percaya bahwa teknologi yang bermanfaat adalah teknologi yang dapat menyentuh kehidupan orang banyak secara langsung.")
        )

        mahasiswaList.add(
            Mahasiswa("Park Chanyeol", "202110370311202", "Teknik Komputer", R.drawable.profil2,
                "6 Mei 2000", "Busan, Korea Selatan", "Public speaking", "Inovator Teknologi Kesehatan",
                "Wearable kesehatan untuk lansia", "Ketua komunitas teknologi",
                "Pembicara seminar teknologi nasional", "Inovator Kesehatan Digital",
                "Park Chanyeol merupakan mahasiswa energik yang memiliki ketertarikan besar pada teknologi wearable. Ia sedang mengembangkan perangkat pelacak kesehatan untuk lansia berbasis Android. Chanyeol juga aktif sebagai ketua komunitas teknologi di kampusnya dan sering menjadi pembicara dalam seminar teknologi tingkat universitas dan regional.")
        )

        mahasiswaList.add(
            Mahasiswa("Lee Taeyong", "202110370311203", "Sistem Informasi", R.drawable.profil3,
                "1 Juli 1999", "Incheon, Korea Selatan", "Membaca jurnal", "Kepala Proyek Sistem Informasi",
                "Digitalisasi perpustakaan daerah", "Koordinator proyek kampus",
                "Penghargaan inovasi sistem kampus", "Manajer Sistem Informasi",
                "Lee Taeyong adalah mahasiswa yang visioner dan sangat peduli dengan pengelolaan data yang efisien. Ia pernah mengerjakan proyek sistem informasi perpustakaan digital untuk sekolah di daerah terpencil. Taeyong memiliki kepemimpinan yang kuat dan sering dipercaya menjadi ketua tim dalam proyek kolaborasi dengan industri lokal.")
        )

        mahasiswaList.add(
            Mahasiswa("Huang Renjun", "202110370311204", "Teknik Komputer", R.drawable.profil4,
                "23 Maret 2000", "Beijing, Tiongkok", "Desain UI", "Desainer UX Profesional",
                "UI aplikasi pendidikan anak", "Tim desain UX kampus",
                "Pemenang UI/UX Hackathon", "UX Specialist",
                "Huang Renjun memiliki keahlian dalam desain UI/UX dan sangat perfeksionis dalam merancang antarmuka aplikasi. Ia memiliki impian menciptakan aplikasi pendidikan anak dengan pendekatan yang menyenangkan dan mudah diakses. Ia juga aktif membuat prototipe aplikasi berbasis Figma dan Flutter.")
        )

        mahasiswaList.add(
            Mahasiswa("Kim Jongin", "202110370311205", "Informatika", R.drawable.profil5,
                "14 Januari 2000", "Gwangju, Korea Selatan", "Coding", "Developer Mobile Profesional",
                "Aplikasi edukasi anak di Google Play", "Asisten lab pemrograman",
                "Top Developer kampus 3 tahun berturut-turut", "Engineer Mobile",
                "Kim Jongin adalah mahasiswa berprestasi di bidang pengembangan aplikasi mobile dan memiliki banyak karya di Google Play. Ia telah membuat berbagai aplikasi edukasi dan hiburan untuk anak-anak. Selain itu, Jongin juga aktif sebagai asisten laboratorium pemrograman dan sangat gemar membagikan ilmu kepada teman-teman seangkatannya.")
        )

        mahasiswaList.add(
            Mahasiswa("Lee Mark", "202110370311206", "Sistem Informasi", R.drawable.profil6,
                "2 Agustus 2000", "Toronto, Kanada", "Analisis Data", "Data Scientist",
                "Analisis data startup lokal", "Magang Google Cloud Club",
                "Sertifikasi Google dan AWS", "Ilmuwan Data",
                "Lee Mark merupakan mahasiswa yang sangat antusias terhadap analisis data dan teknologi cloud computing. Ia sedang mengikuti program sertifikasi cloud dari Google dan AWS. Mark juga sedang magang di perusahaan startup sebagai data analyst junior dan memiliki kemampuan komunikasi lintas budaya yang baik karena latar belakang internasionalnya.")
        )

        mahasiswaList.add(
            Mahasiswa("Jeong Jaehyun", "202110370311207", "Teknik Komputer", R.drawable.profil7,
                "14 Februari 2000", "Daegu, Korea Selatan", "Belajar jaringan", "System Engineer",
                "Load balancing dengan Kubernetes", "Asisten riset sistem terdistribusi",
                "Lolos seleksi kompetisi DevOps nasional", "Engineer Infrastruktur",
                "Jeong Jaehyun adalah mahasiswa yang tenang dan cerdas, ia mendalami dunia pemrograman sistem dan jaringan. Jaehyun juga aktif dalam penelitian dosen mengenai pengembangan sistem terdistribusi. Ia sedang mengerjakan tugas akhir tentang load balancing pada sistem server mikroservice menggunakan Kubernetes dan Docker.")
        )

        mahasiswaList.add(
            Mahasiswa("Choi Seungcheol", "202110370311208", "Sistem Informasi", R.drawable.profil8,
                "8 Agustus 1999", "Jeonju, Korea Selatan", "Organisasi", "Manajer TI",
                "Sistem keuangan organisasi mahasiswa", "Ketua himpunan mahasiswa",
                "Penggagas digitalisasi organisasi kampus", "Manajer Transformasi Digital",
                "Choi Seungcheol adalah mahasiswa yang memiliki jiwa kepemimpinan kuat. Ia sangat peduli terhadap sistem manajemen organisasi berbasis digital. Saat ini ia sedang mengembangkan sistem informasi keuangan untuk organisasi mahasiswa di kampus. Selain akademik, ia aktif dalam kegiatan sosial dan menjadi inspirasi banyak teman seangkatannya.")
        )

        mahasiswaList.add(
            Mahasiswa("Lee Jihoon", "202110370311209", "Informatika", R.drawable.profil9,
                "22 November 2000", "Seoul, Korea Selatan", "Security Testing", "Ahli Keamanan Siber",
                "Sistem login biometrik", "Peserta pelatihan ethical hacking",
                "Finalis Capture The Flag Nasional", "Pakar Cyber Security",
                "Lee Jihoon memiliki passion besar di bidang pemrograman backend dan keamanan siber. Ia sering mengikuti pelatihan ethical hacking dan pengujian keamanan aplikasi. Jihoon sedang merancang sistem login dengan autentikasi biometrik sebagai proyek akhir dan berharap temuannya bisa digunakan pada aplikasi kampus.")
        )

        mahasiswaList.add(
            Mahasiswa("Wen Junhui", "202110370311210", "Teknik Komputer", R.drawable.profil10,
                "10 Juni 2000", "Guangzhou, Tiongkok", "Mikrokontroler", "Insinyur Embedded System",
                "Otomatisasi rumah dengan Arduino", "Pengajar workshop mikrokontroler",
                "Juara lomba inovasi alat otomatisasi", "Teknisi IoT",
                "Wen Junhui adalah mahasiswa internasional yang sangat menonjol dalam bidang embedded systems. Ia telah membuat berbagai alat otomatisasi rumah tangga menggunakan Arduino dan Raspberry Pi. Junhui senang berbagi ilmu dan sering mengadakan workshop terbuka bagi mahasiswa lintas jurusan yang ingin belajar teknologi dari awal.")
        )

        mahasiswaList.add(
            Mahasiswa("Kim Minjeong", "202110370311211", "Informatika", R.drawable.profil11,
                "25 April 2001", "Jeju, Korea Selatan", "Desain grafis", "AR/VR Developer",
                "AR edukasi budaya lokal", "Tim seni digital kampus",
                "Juara 1 desain UI nasional", "Pengembang AR",
                "Kim Minjeong adalah mahasiswa kreatif yang menggabungkan seni dan teknologi dalam karya-karyanya. Ia mengembangkan aplikasi augmented reality untuk edukasi sejarah budaya lokal. Minjeong juga aktif dalam kegiatan seni digital dan pernah memenangkan lomba desain UI tingkat nasional.")
        )

        mahasiswaList.add(
            Mahasiswa("Na Jaemin", "202110370311212", "Sistem Informasi", R.drawable.profil12,
                "13 Agustus 2000", "Seoul, Korea Selatan", "Hackathon", "Arsitek Sistem Enterprise",
                "Platform ERP untuk UMKM", "Aktif hackathon nasional & internasional",
                "Juara hackathon ASEAN 2023", "Arsitek Sistem",
                "Na Jaemin merupakan mahasiswa dengan minat kuat pada pengembangan sistem enterprise. Ia sedang membuat platform ERP sederhana untuk UMKM lokal. Jaemin dikenal sebagai problem solver handal dan aktif mengikuti kompetisi hackathon baik tingkat nasional maupun internasional.")
        )

        mahasiswaList.add(
            Mahasiswa("Park Jisung", "202110370311213", "Teknik Komputer", R.drawable.profil13,
                "5 Februari 2002", "Suwon, Korea Selatan", "Robotika", "Insinyur Otomasi",
                "Robot bantu lansia", "Anggota tim robotika kampus",
                "Finalis kompetisi robotik nasional", "Engineer Robotik",
                "Park Jisung adalah mahasiswa muda berbakat yang tertarik pada robotika dan otomasi. Ia tergabung dalam tim robotika kampus dan telah mengikuti berbagai kompetisi tingkat nasional. Jisung juga sedang mengembangkan robot sederhana untuk membantu tugas rumah tangga lansia.")
        )

        mahasiswaList.add(
            Mahasiswa("Lee Haechan", "202110370311214", "Informatika", R.drawable.profil14,
                "7 Juni 2001", "Ulsan, Korea Selatan", "Game development", "Game Developer",
                "Mini game edukatif Android", "Mentor coding SD",
                "Top 10 Unity Game Jam Asia", "Pengembang Game",
                "Lee Haechan dikenal karena kepiawaiannya dalam pengembangan game berbasis Android dan Unity. Ia telah merilis beberapa mini game edukatif untuk anak-anak. Haechan juga senang mengajar dan aktif menjadi mentor coding untuk siswa sekolah dasar.")
        )

        mahasiswaList.add(
            Mahasiswa("Kim Jungwoo", "202110370311215", "Sistem Informasi", R.drawable.profil15,
                "19 April 2000", "Shanghai, Tiongkok", "Visualisasi data", "Digital Transformation Expert",
                "Dashboard manajemen kampus", "Asisten riset transformasi digital",
                "Pemenang lomba visualisasi data", "Konsultan Digitalisasi",
                "Kim Jungwoo adalah mahasiswa internasional yang fokus pada integrasi sistem bisnis dan data analytics. Ia sedang mengerjakan proyek dashboard visualisasi data untuk pengambilan keputusan manajemen kampus. Jungwoo juga sering menjadi asisten riset dosen di bidang transformasi digital.")
        )
    }
}
