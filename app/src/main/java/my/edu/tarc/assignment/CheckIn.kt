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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import my.edu.tarc.assignment.databinding.FragmentCheckInBinding

class CheckIn : Fragment() {
    private lateinit var bindingCheckIn: FragmentCheckInBinding
    private lateinit var counterTextView: TextView
    private lateinit var builder: AlertDialog.Builder
    private var counter = 0
    lateinit var user: String
    var coinBalance = 0
    val handler = android.os.Handler()
    var username = ""
    private var checkInCount = 0


    lateinit var database: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference

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

        getSess()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference().child("user").child(username)


        //Starting Point of the Check In Bar
        bindingCheckIn.progressBarCheckIn.progress = 0

        //Show Balance Constantly
        counterTextView = bindingCheckIn.textViewRegBalance
        handler.post(updateCounter)

        //Check In Button
        bindingCheckIn.buttonCheckIn.setOnClickListener {
            bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.checkedin)

            if(checkInCount<1) {
                when (counter) {
                    0 -> {
                        //Day 1 CheckIn
                        bindingCheckIn.progressBarCheckIn.progress = 0
                        bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                        coinBalance += 5
                        Toast.makeText(activity, "5 Coins Added!$coinBalance", Toast.LENGTH_SHORT)
                            .show()
                        counter++
                        checkInCount++
                        var coinBalanceUpdate = hashMapOf<String, Any>(
                            "gameCoin" to coinBalance
                        )
                        databaseReference.updateChildren(coinBalanceUpdate)
                    }
                    1 -> {
                        //Day 2 CheckIn
                        //If Statement to Prevent User from checking in twice
                        bindingCheckIn.progressBarCheckIn.progress =
                            (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                        bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                        coinBalance += 5
                        Toast.makeText(activity, "5 Coins Added!$coinBalance", Toast.LENGTH_SHORT)
                            .show()
                        counter++
                        checkInCount++

                    }
                    2 -> {
                        //Day 3 CheckIn
                        bindingCheckIn.progressBarCheckIn.progress =
                            (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                        bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
                        coinBalance += 5
                        Toast.makeText(activity, "5 Coins Added!$coinBalance", Toast.LENGTH_SHORT)
                            .show()
                        counter++
                    }
                    3 -> {
                        //Day 4 CheckIn
                        bindingCheckIn.progressBarCheckIn.progress =
                            (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                        bindingCheckIn.imageViewDay4.setImageResource((R.drawable.checked_in_progress))
                        coinBalance += 5
                        Toast.makeText(activity, "5 Coins Added!$coinBalance", Toast.LENGTH_SHORT)
                            .show()
                        counter++
                    }
                    4 -> {
                        //Day 5 CheckIn
                        bindingCheckIn.progressBarCheckIn.progress =
                            (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                        bindingCheckIn.imageViewDay5.setImageResource((R.drawable.checked_in_progress))
                        coinBalance += 5
                        Toast.makeText(activity, "5 Coins Added!$coinBalance", Toast.LENGTH_SHORT)
                            .show()
                        counter++
                    }
                    5 -> {
                        //Day 6 CheckIn
                        bindingCheckIn.progressBarCheckIn.progress =
                            (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                        bindingCheckIn.imageViewDay6.setImageResource((R.drawable.checked_in_progress))
                        coinBalance += 5
                        Toast.makeText(activity, "5 Coins Added!$coinBalance", Toast.LENGTH_SHORT)
                            .show()
                        counter++
                    }
                    6 -> {
                        //Day 7 CheckIn
                        bindingCheckIn.progressBarCheckIn.progress = 100
                        bindingCheckIn.imageViewDay7.setImageResource((R.drawable.checked_in_progress))
                        coinBalance += 25
                        Toast.makeText(activity, "25 Coins Added!$coinBalance", Toast.LENGTH_SHORT)
                            .show()
                        counter = 0

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
            }else {
                Toast.makeText(
                    activity,
                    "Already Checked In Today!\nPlease Try Again Tomorrow!",
                    Toast.LENGTH_SHORT
                ).show()
            }
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
            getSess()
            displayInfo()
            handler.postDelayed(this, 0) // run instantly
        }
    }

    private fun displayInfo(){
        getData(user)
    }

    //Get specific data from user
    private fun getData(username: String) {
        databaseReference.child(username).child("gameCoin").get().addOnSuccessListener {
            var coinBalance = it.getValue(Int::class.java)
            bindingCheckIn.textViewRegBalance.text = coinBalance.toString()
        }
    }


    //TODO: Solve Issue where I can't use the retrieved coinBalance from database
    private fun getData2(username: String) {
        databaseReference.child("gameCoin").get().addOnSuccessListener {
            coinBalance = it.value.toString().toInt()
        }
        .addOnFailureListener {
        Log.e("firebase", "Error getting data", it)
        }
    }


    //Get Current username
    private fun getSess() {
        val preferences = requireContext().getSharedPreferences("sess_store", Context.MODE_PRIVATE)
        val sessUsername = preferences.getString("username", "")

        if (sessUsername != ""){
            user = sessUsername.toString()
        } else {
            Toast.makeText(activity,"failed to retrieve username", Toast.LENGTH_SHORT).show()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()

        // Selecting which part of the UI should be replaced by the fragment
        // in this case its the frameLayout in activity_main.xml
        fragmentTransaction?.replace(R.id.frameLayout, fragment)
        fragmentTransaction?.commit()
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

//    }
}