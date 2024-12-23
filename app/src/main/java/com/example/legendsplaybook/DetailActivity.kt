package com.example.legendsplaybook

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.legendsplaybook.datas.Legends

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Detail Information"

        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailRole: TextView = findViewById(R.id.tv_detail_role)
        val tvDetailClass: TextView = findViewById(R.id.tv_detail_class)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val ivDetailPhoto: ImageView = findViewById(R.id.iv_detail_photo)
        val tvDetailRealName: TextView = findViewById(R.id.tv_detail_realname)
        val tvDetailAge: TextView = findViewById(R.id.tv_detail_age)
        val tvDetailHome: TextView = findViewById(R.id.tv_detail_home)
        val tvDetailTactical: TextView = findViewById(R.id.tv_detail_tactical)
        val tvDetailPassive: TextView = findViewById(R.id.tv_detail_passive)
        val tvDetailUltimate: TextView = findViewById(R.id.tv_detail_ultimate)
        val ivClassIcon: ImageView = findViewById(R.id.iv_class_icon)
        val btnReadMore: Button = findViewById(R.id.btn_read_more)
        val bShare: Button = findViewById(R.id.btn_action_share)

        val legend = intent.getParcelableExtra<Legends>("EXTRA_LEGEND")

        if (legend != null) {
            tvDetailName.text = legend.name
            tvDetailRole.text = legend.role
            tvDetailClass.text = legend.class_legends
            tvDetailDescription.text = legend.description.replace("\\n", "\n")
            ivDetailPhoto.setImageResource(legend.photo_full)
            tvDetailRealName.text = legend.real_name
            tvDetailAge.text = legend.age
            tvDetailHome.text = legend.home
            tvDetailTactical.text = legend.tactical_ability
            tvDetailPassive.text = legend.passive_ability
            tvDetailUltimate.text = legend.ultimate_ability
            ivClassIcon.setImageResource(legend.classIcon)

            val maxLinesCollapsed = 3
            var isExpanded = false

            tvDetailDescription.maxLines = maxLinesCollapsed

            btnReadMore.setOnClickListener {
                if (isExpanded) {
                    tvDetailDescription.maxLines = maxLinesCollapsed
                    btnReadMore.text = "Read More"
                } else {
                    tvDetailDescription.maxLines = Integer.MAX_VALUE
                    btnReadMore.text = "Read Less"
                }
                isExpanded = !isExpanded
            }

            bShare.setOnClickListener {
                val shareText = """
                    ${legend.name} - ${legend.role} - ${legend.class_legends}
                    
                    Real Name : ${legend.real_name}
                    Age : ${legend.age}
                    Home : ${legend.home}
                    
                    Tactical Ability : ${legend.tactical_ability}
                    Passive Ability : ${legend.passive_ability}
                    Ultimate Ability : ${legend.ultimate_ability}
                    """.trimIndent()

                val shareIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TITLE, "${legend.name} - ${legend.role} - ${legend.class_legends}")
                    putExtra(Intent.EXTRA_TEXT, shareText)  // Kirim sebagai satu string
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Share"))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
