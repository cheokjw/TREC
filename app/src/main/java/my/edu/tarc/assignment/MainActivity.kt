package my.edu.tarc.assignment

import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import my.edu.tarc.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var sf: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // This is what users see when they first use our app (in this case: CheckIn Fragment will be shown first)
        replaceFragment(CheckIn())
        sf = getSharedPreferences("sess_store", MODE_PRIVATE)
        binding.bottomNavigationView.setOnItemSelectedListener {
            Log.i("Session", sf.getString("username", null).toString())
            when(it.itemId) {
                R.id.checkin -> replaceFragment(CheckIn())
                R.id.quiz -> replaceFragment(Quiz())
                R.id.news -> replaceFragment(News())
                R.id.tree -> replaceFragment(Tree())
                R.id.profile -> replaceFragment(Profile())

                // This is for testing commit and push purposes
            }

            true
        }

        binding.fabFAQ.setOnClickListener {
            replaceFragment(FAQ())
        }
    }


    // Allowing fragment to replace the main Body
    fun replaceFragment(fragment : Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Selecting which part of the UI should be replaced by the fragment
        // in this case its the frameLayout in activity_main.xml
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    fun logout(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        sf = getSharedPreferences("sess_store", MODE_PRIVATE)
        val sf = sf.edit()
        sf.clear()
        sf.apply()
        finish()
    }

    fun getSessionUser(): String {
        sf = getSharedPreferences("sess_store", MODE_PRIVATE)
        return sf.getString("username", null).toString()
    }

    override fun onBackPressed() {

    }
}
