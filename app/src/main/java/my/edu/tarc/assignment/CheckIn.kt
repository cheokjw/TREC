package my.edu.tarc.assignment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import my.edu.tarc.assignment.databinding.FragmentCheckInBinding
import java.util.logging.Handler

/**
 * A simple [Fragment] subclass.
 * Use the [CheckIn.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckIn : Fragment() {
    private lateinit var bindingCheckIn: FragmentCheckInBinding
    private var counter = 0
    var coinBalance = 0
    val handler = android.os.Handler()
    private lateinit var counterTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingCheckIn = FragmentCheckInBinding.inflate(inflater)
        bindingCheckIn.progressBarCheckIn.progress = 0

        counterTextView = bindingCheckIn.textViewRegBalance
        handler.post(updateCounter)

        //Check in function
        bindingCheckIn.buttonCheckIn.setOnClickListener {
            bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.checkedin)

            when (counter) {
                0 -> {
                    bindingCheckIn.progressBarCheckIn.progress = 0
                    bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                    coinBalance += 5
                    Toast.makeText(activity, "5 Coins Added!$coinBalance", Toast.LENGTH_LONG).show()
                    counter++
                }
                1 -> {
                    bindingCheckIn.progressBarCheckIn.progress =
                        (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                    bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                    coinBalance += 5
                    Toast.makeText(activity, "5 Coins Added!$coinBalance", Toast.LENGTH_LONG).show()
                    counter++
                }
                2 -> {
                    bindingCheckIn.progressBarCheckIn.progress =
                        (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                    bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
                    coinBalance += 5
                    Toast.makeText(activity, "5 Coins Added!$coinBalance", Toast.LENGTH_LONG).show()
                    counter++
                }
                3 -> {
                    bindingCheckIn.progressBarCheckIn.progress =
                        (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                    bindingCheckIn.imageViewDay4.setImageResource((R.drawable.checked_in_progress))
                    coinBalance += 5
                    Toast.makeText(activity, "5 Coins Added!$coinBalance", Toast.LENGTH_LONG).show()
                    counter++
                }
                4 -> {
                    bindingCheckIn.progressBarCheckIn.progress =
                        (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                    bindingCheckIn.imageViewDay5.setImageResource((R.drawable.checked_in_progress))
                    coinBalance += 5
                    Toast.makeText(activity, "5 Coins Added!$coinBalance", Toast.LENGTH_LONG).show()
                    counter++
                }
                5 -> {
                    bindingCheckIn.progressBarCheckIn.progress =
                        (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                    bindingCheckIn.imageViewDay6.setImageResource((R.drawable.checked_in_progress))
                    coinBalance += 5
                    Toast.makeText(activity, "5 Coins Added!$coinBalance", Toast.LENGTH_LONG).show()
                    counter++
                }
                6 -> {
                    bindingCheckIn.progressBarCheckIn.progress = 0
                    bindingCheckIn.imageViewDay7.setImageResource((R.drawable.checked_in_progress))
                    bindingCheckIn.imageViewDay1.setImageResource((R.drawable.check_in_progress))
                    bindingCheckIn.imageViewDay2.setImageResource((R.drawable.check_in_progress))
                    bindingCheckIn.imageViewDay3.setImageResource((R.drawable.check_in_progress))
                    bindingCheckIn.imageViewDay4.setImageResource((R.drawable.check_in_progress))
                    bindingCheckIn.imageViewDay5.setImageResource((R.drawable.check_in_progress))
                    bindingCheckIn.imageViewDay6.setImageResource((R.drawable.check_in_progress))
                    bindingCheckIn.imageViewDay7.setImageResource((R.drawable.check_in_progress))
                    coinBalance += 25
                    Toast.makeText(activity, "25 Coins Added!$coinBalance", Toast.LENGTH_LONG).show()
                    counter = 0
                }
            }
        }

        //Nav to History Page
        bindingCheckIn.buttonRewards.setOnClickListener {
            replaceFragment(RewardsHistory())
        }
        return bindingCheckIn.root
    }

    private val updateCounter = object : Runnable {
        override fun run() {
            counterTextView.text = coinBalance.toString()
            handler.postDelayed(this, 0) // run instantly
        }
    }


//    private fun getSharedPreferences(s: String, modePrivate: Int): Any {
//        // First, get a reference to the SharedPreferences object
//        val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
//
//        var currentTime = System.currentTimeMillis()
//
//        var lastClickTime = sharedPreferences.getLong("last_click_time", 0)
//
//        val timeSinceLastClick = currentTime - lastClickTime
//
//
//        if (timeSinceLastClick == 86400000) {
//            // It has been at least one day, so allow the button to be clicked
//
//            // Perform the button's action here
//
//            // Save the current time as the "last_click_time" in the SharedPreferences object
//            sharedPreferences.edit().putLong("last_click_time", currentTime).apply()
//        } else {
//            // It has not been at least one day, so prevent the button from being clicked
//        }
//    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()

        // Selecting which part of the UI should be replaced by the fragment
        // in this case its the frameLayout in activity_main.xml
        fragmentTransaction?.replace(R.id.frameLayout, fragment)
        fragmentTransaction?.commit()
    }

}