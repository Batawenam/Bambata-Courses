package com.example.root.bambata

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_start2.*
import kotlinx.android.synthetic.main.app_bar_start.*
import kotlinx.android.synthetic.main.content_start.*
import kotlinx.android.synthetic.main.nav_header_start.*

class StartActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var mUser: FirebaseUser
    var userProfilName = findViewById<ImageView>(R.id.user_profil_image)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start2)
        setSupportActionBar(toolbar)

        val StandardCourses = findViewById<CardView>(R.id.standard_courses)
        StandardCourses.setOnClickListener{
            val StandanrtActivity = Intent(this, MainActivity::class.java)
            startActivity(StandanrtActivity)
        }

        val VipCourses = findViewById<CardView>(R.id.vipCourses)
        VipCourses.setOnClickListener{
            val VipActivity = Intent(this, MainActivity::class.java)
            startActivity(VipActivity)
        }
        val restaurants = findViewById<CardView>(R.id.restarants)
        restaurants.setOnClickListener{
            val restaurantsActivity = Intent(this, MainActivity::class.java)
            startActivity(restaurantsActivity)
        }
        val supermarches = findViewById<CardView>(R.id.supermarches)
        supermarches.setOnClickListener{
            val supermarchesActivity = Intent(this, MainActivity::class.java)
            startActivity(supermarchesActivity)
        }
        val amazon = findViewById<CardView>(R.id.amazon)
        amazon.setOnClickListener {
            val amazonActivity = Intent(this, MainActivity::class.java)
            startActivity(amazonActivity)
        }
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        userProfilName.setImageURI(mUser.photoUrl)
        user_fullname.text = mUser.displayName
        user_mail.text = mUser.phoneNumber

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.start, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                val ConnectionActivity = Intent(this, SignInActivity::class.java)
                startActivity(ConnectionActivity)
            }
            R.id.nav_profil -> {

            }
            R.id.nav_standard -> {

            }
            R.id.nav_vip -> {

            }
            R.id.nav_restaurant -> {

            }
            R.id.nav_supermarche -> {

            }
            R.id.nav_amazon -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_connection -> {

            }


        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
