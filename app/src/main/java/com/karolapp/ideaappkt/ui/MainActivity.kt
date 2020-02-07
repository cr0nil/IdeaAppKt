package com.karolapp.ideaappkt.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.services.WorkManager.WorkerManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var toolbar: Toolbar? = null
    private var navigationView: NavigationView? = null
    private var navController: NavController? = null
    private var drawerLayout: DrawerLayout? = null
    private var firebase: FirebaseAuth? = null
//    @Inject
//    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment2
        )

        firebase = FirebaseAuth.getInstance()
        navigationView = findViewById(R.id.nav_view)

        NavigationUI.setupActionBarWithNavController(this, navController!!, drawerLayout)
        NavigationUI.setupWithNavController(navigationView!!, navController!!)
        navigationView!!.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            finishAffinity()

        }
    }

    fun setActionBarTitle(title: String) {
        getSupportActionBar()!!.setTitle(title)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                toolbar!!.setTitle("Exchange")
                navController!!.navigate(R.id.homeFragment)
            }
            R.id.nav_details -> {
                toolbar!!.setTitle("Historical data")

                navController!!.navigate(R.id.detailsFragment)
            }
            R.id.nav_alarms -> {
                toolbar!!.setTitle("Alarms")
                navController!!.navigate(R.id.alarmFragment)
            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

                firebase!!.signOut()
                val intent = Intent(this, EntranceActivity::class.java)
                startActivity(intent)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun getValueInBackground() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .build()

        val work = PeriodicWorkRequest.Builder(WorkerManager::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueue(work)
    }
}
