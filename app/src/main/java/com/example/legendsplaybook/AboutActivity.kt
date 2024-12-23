package com.example.legendsplaybook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var userPhoto: ImageView
    private lateinit var btnLinkedin: Button
    private lateinit var btnGithub: Button
    private lateinit var btnSourceCode: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.about)

        userPhoto = findViewById(R.id.iv_photo_profile)
        btnLinkedin = findViewById(R.id.btn_linkedin)
        btnGithub = findViewById(R.id.btn_github)
        btnSourceCode = findViewById(R.id.btn_source_code)

        // Set Foto dari Drawable
        userPhoto.setImageResource(R.drawable.photo_profile)

        // Set Listener untuk Button
        btnLinkedin.setOnClickListener(this)
        btnGithub.setOnClickListener(this)
        btnSourceCode.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent()
        intent.setAction(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_BROWSABLE)

        when(v?.id) {
            R.id.btn_linkedin -> {
                val linkedinUrl = getString(R.string.about_linkedin)
                intent.data = Uri.parse(linkedinUrl)
                startActivity(intent)
            }

            R.id.btn_github -> {
                val githubUrl = getString(R.string.about_github)
                intent.data = Uri.parse(githubUrl)
                startActivity(intent)
            }

            R.id.btn_source_code -> {
                val repoUrl = getString(R.string.about_repo)
                intent.data = Uri.parse(repoUrl)
                startActivity(intent)
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