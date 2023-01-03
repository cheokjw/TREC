package my.edu.tarc.assignment

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.NotificationCompat
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
    var username =""
    val handler = android.os.Handler()
    var name =""
    var dailyCheckInCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getSess()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference().child("user").child(username)
//
        bindingCheckIn = FragmentCheckInBinding.inflate(inflater)
        //retrieve username
        databaseReference.child("username").get().addOnSuccessListener {
            name = it.value.toString()
            bindingCheckIn.textViewUserName.text = it.value.toString()
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        //retrieve reminder state
        databaseReference.child("reminderToggle").get().addOnSuccessListener {
            var toggledCounter = it.value.toString().toInt()

        if(toggledCounter == 1) {
            val alarmManager =
                requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

            // Create an Intent that will be broadcast when the alarm triggers
            val intent = Intent(requireContext(), Notification::class.java)

            // Use the PendingIntent.getBroadcast() method to create a PendingIntent that will broadcast the intent
            val pendingIntent =
                PendingIntent.getBroadcast(requireContext(), 0, intent, PendingIntent.FLAG_MUTABLE)

            // Set the alarm to trigger at 4am
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 4)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)

            // Setting the alarm to trigger at the desired time
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
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

        //Update the CoinBalance
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
        counterTextView = bindingCheckIn.textViewRegBalance
        textViewGameCoin = bindingCheckIn.textViewGameCoin
        textViewTreeCoin = bindingCheckIn.textViewTreeCoin
        handler.post(updateCounter)

        //Load Saved User's Check In Progress
        databaseReference.child(sessionUser).get().addOnSuccessListener {
            if (it.exists()) {
                var dbCheckIn = it.child("checkin").value.toString().toInt()
                var dbCheckInCounter = it.child("checkInCounter").value.toString().toInt()
                var checkInCounter : Int
                var checkin : Int

                checkin = if (dbCheckIn == null){
                    0
                }
                else {
                    dbCheckIn as Int
                }
                checkInCounter = if (dbCheckInCounter == null){
                    0
                }
                else {
                    dbCheckInCounter as Int
                }
                saved(checkin,checkInCounter)
            }else {
                Toast.makeText(activity, "User Doesn't Exists", Toast.LENGTH_SHORT).show()
            }
        }

        //Set Switch to it's State According//
        databaseReference.child(sessionUser).get().addOnSuccessListener {
            if (it.exists()) {
                var dbReminderToggle = it.child("reminderToggle").value.toString().toInt()

                var toggled : Int

                toggled = if (dbReminderToggle == null){
                    0
                }
                else {
                    dbReminderToggle as Int
                }

                bindingCheckIn.switchReminder.isChecked = toggled > 0

            }else {
                Toast.makeText(activity, "User Doesn't Exists", Toast.LENGTH_SHORT).show()
            }
        }


        //Reminder Notification//
        bindingCheckIn.switchReminder.setOnCheckedChangeListener{ _, isChecked ->
            if (!isChecked) {
                databaseReference.child(sessionUser).get().addOnSuccessListener {
                    if (it.exists()) {
                        var dbReminderToggle = it.child("reminderToggle").value.toString().toInt()
                        var toggled : Int
                        toggled = if (dbReminderToggle == null){
                            0
                        }
                        else {
                            dbReminderToggle as Int
                        }
                        toggled = 0
                        var switchUpdate = hashMapOf<String, Any>(
                            "reminderToggle" to toggled
                        )
                        databaseReference.child(sessionUser).updateChildren(switchUpdate)

                    }else {
                        Toast.makeText(activity, "User Doesn't Exists", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                databaseReference.child(sessionUser).get().addOnSuccessListener {
                    if (it.exists()) {
                        var dbReminderToggle = it.child("reminderToggle").value.toString().toInt()
                        var toggled : Int
                        toggled = if (dbReminderToggle == null){
                            0
                        }
                        else {
                            dbReminderToggle as Int
                        }
                        toggled = 1
                        var switchUpdate = hashMapOf<String, Any>(
                            "reminderToggle" to toggled
                        )
                        databaseReference.child(sessionUser).updateChildren(switchUpdate)

                    }else {
                        Toast.makeText(activity, "User Doesn't Exists", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        //Check In Button
        bindingCheckIn.buttonCheckIn.setOnClickListener {
            bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.checkedin)
            databaseReference.child(sessionUser).get().addOnSuccessListener {
                if (it.exists()){
                    var dbCheckIn = it.child("checkin").value.toString().toInt()
                    var dbGameCoin = it.child("gameCoin").value.toString().toInt()
                    var dbCheckInCounter = it.child("checkInCounter").value.toString().toInt()
                    var dbCheckInDate = it.child("checkInDate").value.toString().toInt()
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

                    val c = Calendar.getInstance()
                    val day = c.get(Calendar.DAY_OF_MONTH)

                    if (dbCheckInDate != day){
                        checkInCounter = 0
                    }

                    if(checkInCounter<1) {
                        when (checkin) {
                            0 -> {
                                //Day 1 CheckIn
                                saveProgress(checkin)
                                gameCoin += 100
                                Toast.makeText(activity, "$100 Coins Added!", Toast.LENGTH_SHORT).show()
                                counter++
                                checkInCounter++
                                checkin++
                                dailyCheckInCount++

                                var coinUpdate = hashMapOf<String, Any>(
                                    "gameCoin" to gameCoin,
                                    "checkin" to checkin,
                                    "checkInCounter" to checkInCounter,
                                    "checkInDate" to day,
                                    "dailyCheckInCount" to dailyCheckInCount

                                )
                                databaseReference.child(sessionUser).updateChildren(coinUpdate)
                            }
                            1 -> {
                                //Day 2 CheckIn
                                saveProgress(checkin)
                                gameCoin += 100
                                Toast.makeText(activity, "$100 Coins Added!", Toast.LENGTH_SHORT).show()
                                counter++
                                checkInCounter++
                                checkin++
                                dailyCheckInCount++

                                var coinUpdate = hashMapOf<String, Any>(
                                    "gameCoin" to gameCoin,
                                    "checkin" to checkin,
                                    "checkInCounter" to checkInCounter,
                                    "checkInDate" to day,
                                    "dailyCheckInCount" to dailyCheckInCount
                                )
                                databaseReference.child(sessionUser).updateChildren(coinUpdate)

                            }
                            2 -> {
                                //Day 3 CheckIn
                                saveProgress(checkin)
                                gameCoin += 100
                                Toast.makeText(activity, "$100 Coins Added!", Toast.LENGTH_SHORT).show()
                                counter++
                                checkInCounter++
                                checkin++
                                dailyCheckInCount++

                                var coinUpdate = hashMapOf<String, Any>(
                                    "gameCoin" to gameCoin,
                                    "checkin" to checkin,
                                    "checkInCounter" to checkInCounter,
                                    "checkInDate" to day,
                                    "dailyCheckInCount" to dailyCheckInCount
                                )
                                databaseReference.child(sessionUser).updateChildren(coinUpdate)
                            }
                            3 -> {
                                //Day 4 CheckIn
                                saveProgress(checkin)
                                gameCoin += 100
                                Toast.makeText(activity, "$100 Coins Added!", Toast.LENGTH_SHORT).show()
                                counter++
                                checkInCounter++
                                checkin++
                                dailyCheckInCount++

                                var coinUpdate = hashMapOf<String, Any>(
                                    "gameCoin" to gameCoin,
                                    "checkin" to checkin,
                                    "checkInCounter" to checkInCounter,
                                    "checkInDate" to day,
                                    "dailyCheckInCount" to dailyCheckInCount
                                )
                                databaseReference.child(sessionUser).updateChildren(coinUpdate)
                            }
                            4 -> {
                                //Day 5 CheckIn
                                saveProgress(checkin)
                                gameCoin += 100
                                Toast.makeText(activity, "$100 Coins Added!", Toast.LENGTH_SHORT).show()
                                counter++
                                checkInCounter++
                                checkin++
                                dailyCheckInCount++

                                var coinUpdate = hashMapOf<String, Any>(
                                    "gameCoin" to gameCoin,
                                    "checkin" to checkin,
                                    "checkInCounter" to checkInCounter,
                                    "checkInDate" to day,
                                    "dailyCheckInCount" to dailyCheckInCount
                                )
                                databaseReference.child(sessionUser).updateChildren(coinUpdate)
                            }
                            5 -> {
                                //Day 6 CheckIn
                                saveProgress(checkin)
                                gameCoin += 100
                                Toast.makeText(activity, "$100 Coins Added!", Toast.LENGTH_SHORT).show()
                                counter++
                                checkInCounter++
                                checkin++
                                dailyCheckInCount++

                                var coinUpdate = hashMapOf<String, Any>(
                                    "gameCoin" to gameCoin,
                                    "checkin" to checkin,
                                    "checkInCounter" to checkInCounter,
                                    "checkInDate" to day,
                                    "dailyCheckInCount" to dailyCheckInCount
                                )
                                databaseReference.child(sessionUser).updateChildren(coinUpdate)
                            }
                            6 -> {
                                //Day 7 CheckIn
                                saveProgress(checkin)
                                gameCoin += 500
                                Toast.makeText(activity, "$500 Coins Added!", Toast.LENGTH_SHORT).show()
                                counter++
                                checkInCounter++
                                checkin = 0
                                dailyCheckInCount++

                                var coinUpdate = hashMapOf<String, Any>(
                                    "gameCoin" to gameCoin,
                                    "checkin" to checkin,
                                    "checkInCounter" to checkInCounter,
                                    "checkInDate" to day,
                                    "dailyCheckInCount" to dailyCheckInCount
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
        //Nav to Tree
        bindingCheckIn.buttonRewards.setOnClickListener {
            replaceFragment(Tree())
        }
    }

    private fun saved(checkin: Int, checkInCounter: Int){
        when(checkin){
            0 -> {
                if (checkInCounter<1){
                    bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.notcheckedin)
                }else{
                    bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.checkedin)
                }
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
                if (checkInCounter<1){
                    bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.notcheckedin)
                }else{
                    bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.checkedin)
                }
                bindingCheckIn.progressBarCheckIn.progress = 0
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
            }
            2-> {
                if (checkInCounter<1){
                    bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.notcheckedin)
                }else{
                    bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.checkedin)
                }
                bindingCheckIn.progressBarCheckIn.progress = 17
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
            }
            3-> {
                if (checkInCounter<1){
                    bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.notcheckedin)
                }else{
                    bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.checkedin)
                }
                bindingCheckIn.progressBarCheckIn.progress = 34
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
            }
            4-> {
                if (checkInCounter<1){
                    bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.notcheckedin)
                }else{
                    bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.checkedin)
                }
                bindingCheckIn.progressBarCheckIn.progress = 51
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay4.setImageResource((R.drawable.checked_in_progress))
            }
            5-> {
                if (checkInCounter<1){
                    bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.notcheckedin)
                }else{
                    bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.checkedin)
                }
                bindingCheckIn.progressBarCheckIn.progress = 68
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay4.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay5.setImageResource((R.drawable.checked_in_progress))
            }
            6-> {
                if (checkInCounter<1){
                    bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.notcheckedin)
                }else{
                    bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.checkedin)
                }
                bindingCheckIn.progressBarCheckIn.progress = 85
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay4.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay5.setImageResource((R.drawable.checked_in_progress))
                bindingCheckIn.imageViewDay6.setImageResource((R.drawable.checked_in_progress))
            }
            7-> {
                if (checkInCounter<1){
                    bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.notcheckedin)
                }else{
                    bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.checkedin)
                }
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

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()

        // Selecting which part of the UI should be replaced by the fragment
        // in this case its the frameLayout in activity_main.xml
        fragmentTransaction?.replace(R.id.frameLayout, fragment)
        fragmentTransaction?.commit()
    }

    private fun getSess(){
        val preferences = requireContext().getSharedPreferences("sess_store",Context.MODE_PRIVATE)
        val sess_username = preferences.getString("username", "")
        if (sess_username != ""){
            username = sess_username.toString()
        }else{
            Toast.makeText(activity, "failed to retrieve username", Toast.LENGTH_SHORT).show()
        }
    }
}