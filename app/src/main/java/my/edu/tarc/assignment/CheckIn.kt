package my.edu.tarc.assignment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.*
import my.edu.tarc.assignment.databinding.FragmentCheckInBinding
import java.util.*
import java.util.concurrent.TimeUnit


class CheckIn : Fragment() {
    private lateinit var bindingCheckIn: FragmentCheckInBinding

    private lateinit var counterTextView: TextView
    private lateinit var textViewGameCoin: TextView
    private lateinit var textViewTreeCoin: TextView
    private lateinit var builder : AlertDialog.Builder

    lateinit var database: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference

    private var counter = 0
    var gameCoin = 0
    var treeCoin = 0
    val handler = android.os.Handler()
    private var checkin = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingCheckIn = FragmentCheckInBinding.inflate(inflater)

        return bindingCheckIn.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity: FragmentActivity?= activity

        // Get who is the current user
        val mainActivity: MainActivity = getActivity() as MainActivity
        val sessionUser = mainActivity.getSessionUser()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("user")

        //Starting Point of the Check In Bar
        bindingCheckIn.progressBarCheckIn.progress = 0

//        //Attempt to Reset CheckInCounter
//        databaseReference.child(sessionUser).get().addOnSuccessListener {
//            if (it.exists()) {
//                var dbcheckInCounter = it.child("checkInCounter").value.toString().toInt()
//                var checkInCounter : Int
//
//                checkInCounter = if (dbcheckInCounter == null){
//                    0
//                }
//                else {
//                    dbcheckInCounter as Int
//                }
//                resetCheckInCounter(checkInCounter)
//                var checkinUpdate = hashMapOf<String, Any>(
//                    "checkInCounter" to checkInCounter,
//                )
//                databaseReference.child(sessionUser).updateChildren(checkinUpdate)
//            }else {
//                Toast.makeText(activity, "User Doesn't Exists", Toast.LENGTH_SHORT).show()
//            }
//        }

        val updateCounter = object : Runnable {
            override fun run() {
                databaseReference.child(sessionUser).get().addOnSuccessListener {
                    if (it.exists()) {
                        var dbGameCoin = it.child("gameCoin").value.toString().toInt()
                        var dbTreeCoin = it.child("treeCoin").value.toString().toInt()

                        gameCoin = if (dbGameCoin == null) {
                            0
                        } else {
                            dbGameCoin as Int
                        }

                        treeCoin = if (dbTreeCoin == null) {
                            0
                        } else {
                            dbTreeCoin as Int
                        }
                        counterTextView.text = gameCoin.toString()
                        textViewGameCoin.text = gameCoin.toString()
                        textViewTreeCoin.text = treeCoin.toString()
                        handler.postDelayed(this, 0) // run instantly
                    }else {
                        Toast.makeText(activity, "User Doesn't Exists", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        //Load Saved User's Check In Progress
        databaseReference.child(sessionUser).get().addOnSuccessListener {
            if (it.exists()) {
                var dbCheckIn = it.child("checkin").value.toString().toInt()
                var checkin : Int

                checkin = if (dbCheckIn == null){
                    0
                }
                else {
                    dbCheckIn as Int
                }
                saved(checkin)
            }else {
                Toast.makeText(activity, "User Doesn't Exists", Toast.LENGTH_SHORT).show()
            }
        }
        counterTextView = bindingCheckIn.textViewRegBalance
        textViewGameCoin = bindingCheckIn.textViewGameCoin
        textViewTreeCoin = bindingCheckIn.textViewTreeCoin
        handler.post(updateCounter)

        //Check In Button
        bindingCheckIn.buttonCheckIn.setOnClickListener {
            bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.checkedin)

            databaseReference.child(sessionUser).get().addOnSuccessListener {
                if (it.exists()){
                    var dbCheckIn = it.child("checkin").value.toString().toInt()
                    var dbGameCoin = it.child("gameCoin").value.toString().toInt()
                    var dbCheckInCounter = it.child("checkInCounter").value.toString().toInt()
                    Log.i("SessionValue", dbCheckIn.toString())

                    var checkin : Int
                    var checkInCounter : Int

                    checkin = if (dbCheckIn == null){
                        0
                    }
                    else {
                        dbCheckIn as Int
                    }

                    gameCoin = if (dbGameCoin == null){
                        0
                    }else {
                        dbGameCoin as Int
                    }

                    checkInCounter = if (dbCheckInCounter == null){
                        0
                    }else {
                        dbCheckInCounter as Int
                    }

                    if(checkInCounter<100) {
                        when (checkin) {
                            0 -> {
                                //Day 1 CheckIn
                                saveProgress(checkin)
                                gameCoin += 100
                                Toast.makeText(activity, "5 Coins Added!$gameCoin", Toast.LENGTH_SHORT).show()
                                counter++
                                checkInCounter++
                                checkin++

                                var coinUpdate = hashMapOf<String, Any>(
                                    "gameCoin" to gameCoin,
                                    "checkin" to checkin,
                                    "checkInCounter" to checkInCounter
                                )
                                databaseReference.child(sessionUser).updateChildren(coinUpdate)
                            }
                            1 -> {
                                //Day 2 CheckIn
                                saveProgress(checkin)
                                gameCoin += 100
                                Toast.makeText(activity, "5 Coins Added!$gameCoin", Toast.LENGTH_SHORT).show()
                                counter++
                                checkInCounter++
                                checkin++

                                var coinUpdate = hashMapOf<String, Any>(
                                    "gameCoin" to gameCoin,
                                    "checkin" to checkin,
                                    "checkInCounter" to checkInCounter
                                )
                                databaseReference.child(sessionUser).updateChildren(coinUpdate)

                            }
                            2 -> {
                                //Day 3 CheckIn
                                saveProgress(checkin)
                                gameCoin += 100
                                Toast.makeText(activity, "5 Coins Added!$gameCoin", Toast.LENGTH_SHORT).show()
                                counter++
                                checkInCounter++
                                checkin++

                                var coinUpdate = hashMapOf<String, Any>(
                                    "gameCoin" to gameCoin,
                                    "checkin" to checkin,
                                    "checkInCounter" to checkInCounter
                                )
                                databaseReference.child(sessionUser).updateChildren(coinUpdate)
                            }
                            3 -> {
                                //Day 4 CheckIn
                                saveProgress(checkin)
                                gameCoin += 100
                                Toast.makeText(activity, "5 Coins Added!$gameCoin", Toast.LENGTH_SHORT).show()
                                counter++
                                checkInCounter++
                                checkin++

                                var coinUpdate = hashMapOf<String, Any>(
                                    "gameCoin" to gameCoin,
                                    "checkin" to checkin,
                                    "checkInCounter" to checkInCounter
                                )
                                databaseReference.child(sessionUser).updateChildren(coinUpdate)
                            }
                            4 -> {
                                //Day 5 CheckIn
                                saveProgress(checkin)
                                gameCoin += 100
                                Toast.makeText(activity, "5 Coins Added!$gameCoin", Toast.LENGTH_SHORT).show()
                                counter++
                                checkInCounter++
                                checkin++

                                var coinUpdate = hashMapOf<String, Any>(
                                    "gameCoin" to gameCoin,
                                    "checkin" to checkin,
                                    "checkInCounter" to checkInCounter
                                )
                                databaseReference.child(sessionUser).updateChildren(coinUpdate)
                            }
                            5 -> {
                                //Day 6 CheckIn
                                saveProgress(checkin)
                                gameCoin += 100
                                Toast.makeText(activity, "5 Coins Added!$gameCoin", Toast.LENGTH_SHORT).show()
                                counter++
                                checkInCounter++
                                checkin++

                                var coinUpdate = hashMapOf<String, Any>(
                                    "gameCoin" to gameCoin,
                                    "checkin" to checkin,
                                    "checkInCounter" to checkInCounter
                                )
                                databaseReference.child(sessionUser).updateChildren(coinUpdate)
                            }
                            6 -> {
                                //Day 7 CheckIn
                                saveProgress(checkin)
                                gameCoin += 500
                                Toast.makeText(activity, "5 Coins Added!$gameCoin", Toast.LENGTH_SHORT).show()
                                counter++
                                checkInCounter++
                                checkin = 0

                                var coinUpdate = hashMapOf<String, Any>(
                                    "gameCoin" to gameCoin,
                                    "checkin" to checkin,
                                    "checkInCounter" to checkInCounter
                                )
                                databaseReference.child(sessionUser).updateChildren(coinUpdate)

                                //Show Pop To Notify User
                                builder = AlertDialog.Builder(activity!!)
                                builder.setTitle("Congratulations!")
                                    .setMessage("You have checked in for 7 days")
                                    .setCancelable(true)
                                    .setPositiveButton("Ok") { dialogInterface, it ->
                                        bindingCheckIn.progressBarCheckIn.progress = 0
                                        bindingCheckIn.imageViewDay1.setImageResource((R.drawable.check_in_progress))
                                        bindingCheckIn.imageViewDay2.setImageResource((R.drawable.check_in_progress))
                                        bindingCheckIn.imageViewDay3.setImageResource((R.drawable.check_in_progress))
                                        bindingCheckIn.imageViewDay4.setImageResource((R.drawable.check_in_progress))
                                        bindingCheckIn.imageViewDay5.setImageResource((R.drawable.check_in_progress))
                                        bindingCheckIn.imageViewDay6.setImageResource((R.drawable.check_in_progress))
                                        bindingCheckIn.imageViewDay7.setImageResource((R.drawable.check_in_progress))
                                        dialogInterface.cancel()
                                    }
                                    .show()
                            }
                        }
                    }else{
                        Toast.makeText(activity, "Already Checked In Today!\nPlease Try Again Tomorrow!", Toast.LENGTH_SHORT).show()
                    }


                }else {
                    Toast.makeText(activity, "User Doesn't Exists", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener{
                Toast.makeText(activity, "Failed to Retrieve User", Toast.LENGTH_SHORT).show()
            }
        }


        //TODO: setAlarm function and create variables to be stored as data set

        //Nav to History Page
        bindingCheckIn.buttonRewards.setOnClickListener {

            replaceFragment(Signup())
        }
    }
    private fun saved(checkin: Int){
        when(checkin){
            0 -> {
                bindingCheckIn.progressBarCheckIn.progress = 0
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.check_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.check_in_progress))
                bindingCheckIn.imageViewDay3.setImageResource((R.drawable.check_in_progress))
                bindingCheckIn.imageViewDay4.setImageResource((R.drawable.check_in_progress))
                bindingCheckIn.imageViewDay5.setImageResource((R.drawable.check_in_progress))
                bindingCheckIn.imageViewDay6.setImageResource((R.drawable.check_in_progress))
                bindingCheckIn.imageViewDay7.setImageResource((R.drawable.check_in_progress))
            }
            1 -> {
                bindingCheckIn.progressBarCheckIn.progress = 0
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
            }
            2-> {
                bindingCheckIn.progressBarCheckIn.progress = 17
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
            }
            3-> {
                bindingCheckIn.progressBarCheckIn.progress = 34
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
            }
            4-> {
                bindingCheckIn.progressBarCheckIn.progress = 51
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay4.setImageResource((R.drawable.checked_in_progress))
            }
            5-> {
                bindingCheckIn.progressBarCheckIn.progress = 68
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay4.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay5.setImageResource((R.drawable.checked_in_progress))
            }
            6-> {
                bindingCheckIn.progressBarCheckIn.progress = 85
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay4.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay5.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay6.setImageResource((R.drawable.checked_in_progress))
            }
            7-> {
                bindingCheckIn.progressBarCheckIn.progress = 100%100
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay4.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay5.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay6.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay7.setImageResource((R.drawable.checked_in_progress))

            }
        }
    }


    private fun saveProgress(checkin: Int){
        when(checkin){
            0 -> {
                bindingCheckIn.progressBarCheckIn.progress = 0
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
            }
            1-> {
                bindingCheckIn.progressBarCheckIn.progress = 17
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
            }
            2-> {
                bindingCheckIn.progressBarCheckIn.progress = 34
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
            }
            3-> {
                bindingCheckIn.progressBarCheckIn.progress = 51
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay4.setImageResource((R.drawable.checked_in_progress))
            }
            4-> {
                bindingCheckIn.progressBarCheckIn.progress = 68
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay4.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay5.setImageResource((R.drawable.checked_in_progress))
            }
            5-> {
                bindingCheckIn.progressBarCheckIn.progress = 85
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay4.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay5.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay6.setImageResource((R.drawable.checked_in_progress))
            }
            6-> {
                bindingCheckIn.progressBarCheckIn.progress = 100%100
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay4.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay5.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay6.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay7.setImageResource((R.drawable.checked_in_progress))

            }
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

    private fun resetCheckInCounter(checkInCounter: Int){
        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                // your code here
                var checkInCounter = 0
            }
        }

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 2) // 12 PM
        calendar.set(Calendar.MINUTE, 0) // 0 minutes
        calendar.set(Calendar.SECOND, 0) // 0 seconds

        timer.scheduleAtFixedRate(task, calendar.time, TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) // repeat every day
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