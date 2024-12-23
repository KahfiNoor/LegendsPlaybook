package com.example.legendsplaybook

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.legendsplaybook.adapter.LegendsListAdapter
import com.example.legendsplaybook.datas.Legends

class MainActivity : AppCompatActivity() {
    private lateinit var rvLegends: RecyclerView
    private val legendsList = ArrayList<Legends>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_main)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Legends Playbook"

        rvLegends = findViewById(R.id.rv_legends)
        rvLegends.setHasFixedSize(true)

        legendsList.addAll(getLegendsList())
        showRecyclerList()
    }

    private fun getLegendsList(): ArrayList<Legends> {
        val dataName = resources.getStringArray(R.array.legends_name_data)
        val dataRole = resources.getStringArray(R.array.legends_role_data)
        val dataClass = resources.getStringArray(R.array.legends_class_data)
        val dataPhotoCover = resources.obtainTypedArray(R.array.legends_photo_cover_data)
        val dataPhotoFull = resources.obtainTypedArray(R.array.legends_photo_full_data)
        val dataDescription = resources.getTextArray(R.array.legends_description_data)
        val dataRealName = resources.getStringArray(R.array.legends_real_name_data)
        val dataAge = resources.getStringArray(R.array.legends_age_data)
        val dataHome = resources.getStringArray(R.array.legends_home_world_data)
        val dataTactical = resources.getStringArray(R.array.legends_tactical_data)
        val dataPassive = resources.getStringArray(R.array.legends_passive_data)
        val dataUltimate = resources.getStringArray(R.array.legends_ultimate_data)

        val legendsList = ArrayList<Legends>()

        for (i in dataName.indices) {
            val classIcon = when (dataClass[i]) {
                "Skirmisher" -> R.drawable.icon_skirmisher
                "Assault" -> R.drawable.icon_assault
                "Controller" -> R.drawable.icon_controller
                "Support" -> R.drawable.icon_support
                "Recon" -> R.drawable.icon_recon
                else -> R.drawable.apex_icon
            }
            val legends = Legends(
                name = dataName[i],
                role = dataRole[i],
                class_legends = dataClass[i],
                photo_cover = dataPhotoCover.getResourceId(i, -1),
                photo_full = dataPhotoFull.getResourceId(i, -1),
                description = dataDescription[i].toString(),
                real_name = dataRealName[i],
                age = dataAge[i],
                home = dataHome[i],
                tactical_ability = dataTactical[i],
                passive_ability = dataPassive[i],
                ultimate_ability = dataUltimate[i],
                classIcon = classIcon
            )
            legendsList.add(legends)
        }

        dataPhotoCover.recycle()
        dataPhotoFull.recycle()

        return legendsList
    }

    private fun showRecyclerList() {
        rvLegends.layoutManager = LinearLayoutManager(this)
        val legendsListAdapter = LegendsListAdapter(legendsList)
        rvLegends.adapter = legendsListAdapter
        rvLegends.setItemViewCacheSize(20)

        legendsListAdapter.setOnItemClickCallback(object : LegendsListAdapter.OnItemClickCallback {
            override fun onItemClicked(legend: Legends) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("EXTRA_LEGEND", legend)
                startActivity(intentToDetail)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val intentToAbout = Intent(this, AboutActivity::class.java)
                startActivity(intentToAbout)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}