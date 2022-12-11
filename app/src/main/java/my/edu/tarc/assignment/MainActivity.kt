package my.edu.tarc.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import my.edu.tarc.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // This is what users see when they first use our app (in this case: CheckIn Fragment will be shown first)
        replaceFragment(CheckIn())

        binding.bottomNavigationView.setOnItemSelectedListener {
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
    }


    // Allowing fragment to replace the main Body
    private fun replaceFragment(fragment : Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Selecting which part of the UI should be replaced by the fragment
        // in this case its the frameLayout in activity_main.xml
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}
