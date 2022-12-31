package my.edu.tarc.assignment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import my.edu.tarc.assignment.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //creating session
    /*private val sharedPref = this.getSharedPreferences("SessPref", Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_login)


        val fragment = Login()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameLayout_login, fragment)
        fragmentTransaction.commit()


    }

    fun access() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}



