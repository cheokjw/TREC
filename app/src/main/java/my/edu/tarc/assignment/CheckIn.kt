package my.edu.tarc.assignment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import my.edu.tarc.assignment.databinding.FragmentCheckInBinding


class CheckIn: Fragment() {
    private lateinit var bindingCheckIn: FragmentCheckInBinding

    lateinit var counterTextView: TextView
    lateinit var builder : AlertDialog.Builder

    lateinit var database: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference

    private var checkInCount = 0
    private var counter = 0
    private var user = ""

    var gameCoinBal = 0
    val handler = android.os.Handler()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference().child("user")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingCheckIn = FragmentCheckInBinding.inflate(layoutInflater)
        return bindingCheckIn.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity: FragmentActivity?= activity
        bindingCheckIn.progressBarCheckIn.progress = 0

        //Show Balance Constantly
        handler.post(updateCounter)

        //Check In Button
        bindingCheckIn.buttonCheckIn.setOnClickListener {
            bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.checkedin)

//        if(checkInCount<1) {
//            when (counter) {
//                0 -> {
//                    //Day 1 CheckIn
//                    bindingCheckIn.progressBarCheckIn.progress = 0
//                    bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
//                    Toast.makeText(activity, "5 Coins Added!$gameCoinBalance", Toast.LENGTH_SHORT)
//                        .show()
//                    counter++
//                    checkInCount++
//                    gameCoinBalance += 5
//
//                }
//                1 -> {
//                    //Day 2 CheckIn
//                    //If Statement to Prevent User from checking in twice
//                    bindingCheckIn.progressBarCheckIn.progress =
//                        (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
//                    bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
//                    gameCoinBalance += 5
//                    Toast.makeText(activity, "5 Coins Added!$gameCoinBalance", Toast.LENGTH_SHORT)
//                        .show()
//                    counter++
//                    checkInCount++
//
//                }
//                2 -> {
//                    //Day 3 CheckIn
//                    bindingCheckIn.progressBarCheckIn.progress =
//                        (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
//                    bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
//                    gameCoinBalance += 5
//                    Toast.makeText(activity, "5 Coins Added!$gameCoinBalance", Toast.LENGTH_SHORT)
//                        .show()
//                    counter++
//                }
//                3 -> {
//                    //Day 4 CheckIn
//                    bindingCheckIn.progressBarCheckIn.progress =
//                        (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
//                    bindingCheckIn.imageViewDay4.setImageResource((R.drawable.checked_in_progress))
//                    gameCoinBalance += 5
//                    Toast.makeText(activity, "5 Coins Added!$gameCoinBalance", Toast.LENGTH_SHORT)
//                        .show()
//                    counter++
//                }
//                4 -> {
//                    //Day 5 CheckIn
//                    bindingCheckIn.progressBarCheckIn.progress =
//                        (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
//                    bindingCheckIn.imageViewDay5.setImageResource((R.drawable.checked_in_progress))
//                    gameCoinBalance += 5
//                    Toast.makeText(activity, "5 Coins Added!$gameCoinBalance", Toast.LENGTH_SHORT)
//                        .show()
//                    counter++
//                }
//                5 -> {
//                    //Day 6 CheckIn
//                    bindingCheckIn.progressBarCheckIn.progress =
//                        (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
//                    bindingCheckIn.imageViewDay6.setImageResource((R.drawable.checked_in_progress))
//                    gameCoinBalance += 5
//                    Toast.makeText(activity, "5 Coins Added!$gameCoinBalance", Toast.LENGTH_SHORT)
//                        .show()
//                    counter++
//                }
//                6 -> {
//                    //Day 7 CheckIn
//                    bindingCheckIn.progressBarCheckIn.progress = 100
//                    bindingCheckIn.imageViewDay7.setImageResource((R.drawable.checked_in_progress))
//                    gameCoinBalance += 25
//                    Toast.makeText(activity, "25 Coins Added!$gameCoinBalance", Toast.LENGTH_SHORT)
//                        .show()
//                    counter = 0
//
//                    //Show Pop To Notify User
//                    builder = AlertDialog.Builder(activity!!)
//                    builder.setTitle("Congratulations!")
//                        .setMessage("You have checked in for 7 days")
//                        .setCancelable(true)
//                        .setPositiveButton("Ok") { dialogInterface, it ->
//                            bindingCheckIn.progressBarCheckIn.progress = 0
//                            bindingCheckIn.imageViewDay1.setImageResource((R.drawable.check_in_progress))
//                            bindingCheckIn.imageViewDay2.setImageResource((R.drawable.check_in_progress))
//                            bindingCheckIn.imageViewDay3.setImageResource((R.drawable.check_in_progress))
//                            bindingCheckIn.imageViewDay4.setImageResource((R.drawable.check_in_progress))
//                            bindingCheckIn.imageViewDay5.setImageResource((R.drawable.check_in_progress))
//                            bindingCheckIn.imageViewDay6.setImageResource((R.drawable.check_in_progress))
//                            bindingCheckIn.imageViewDay7.setImageResource((R.drawable.check_in_progress))
//                            dialogInterface.cancel()
//                        }
//                        .show()
//                }
//            }
//        }else{
//            Toast.makeText(activity, "Already Checked In Today!\nPlease Try Again Tomorrow!", Toast.LENGTH_SHORT).show()
//        }
        }
        //TODO: setAlarm function and create variables to be stored as data set

        //Nav to History Page
        bindingCheckIn.buttonRewards.setOnClickListener {

            replaceFragment(Signup())
        }
    }


    //Function to Constantly Update Counter without Delay
    private val updateCounter = object : Runnable {
        override fun run() {
//            counterTextView.text = gameCoinBal.toString()
            getSess()
            displayinfo()
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

    private fun displayinfo(){
        getData(user)
    }

    private fun getData(username: String) {
        //get coin
        databaseReference.child(username).child("gameCoin").get().addOnSuccessListener {
            val coinBal = it.getValue(Int::class.java).toString()
            bindingCheckIn.textViewRegBalance.text = coinBal}
    }

    private fun getSess(){
        val preferences = requireContext().getSharedPreferences("sess_store", Context.MODE_PRIVATE)
        val sess_username = preferences.getString("username", "")
        if (sess_username != ""){
            user = sess_username!!
        } else {
            Toast.makeText(activity,"failed to retrieve username", Toast.LENGTH_SHORT).show()
        }
    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()

        // Selecting which part of the UI should be replaced by the fragment
        // in this case its the frameLayout in activity_main.xml
        fragmentTransaction?.replace(R.id.frameLayout, fragment)
        fragmentTransaction?.commit()
    }

}