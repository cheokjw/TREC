package my.edu.tarc.assignment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import my.edu.tarc.assignment.databinding.FragmentCheckInBinding

/**
 * A simple [Fragment] subclass.
 * Use the [CheckIn.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckIn : Fragment() {
    private lateinit var bindingCheckIn: FragmentCheckInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingCheckIn = FragmentCheckInBinding.inflate(inflater)
        bindingCheckIn.progressBarCheckIn.progress = 0
        var progress = bindingCheckIn.progressBarCheckIn.progress

        bindingCheckIn.buttonCheckIn.setOnClickListener {
            bindingCheckIn.notCheckedInStatus.setImageResource(R.drawable.checkedin)

            //TODO: Solve Logic for Progress Bar (getSharedPreference)
            if(progress == 0) {
                bindingCheckIn.progressBarCheckIn.progress =
                    (bindingCheckIn.progressBarCheckIn.progress + 1) % 100
                bindingCheckIn.imageViewDay1.setImageResource((R.drawable.checked_in_progress))
                progress++
            }else{
                bindingCheckIn.progressBarCheckIn.progress =
                    (bindingCheckIn.progressBarCheckIn.progress + 15) % 100
                if(progress >= 16){
                    bindingCheckIn.imageViewDay2.setImageResource((R.drawable.checked_in_progress))
                }else if(progress >= 31){
                    bindingCheckIn.imageViewDay3.setImageResource((R.drawable.checked_in_progress))
                }else if(progress >= 46){
                    bindingCheckIn.imageViewDay4.setImageResource((R.drawable.checked_in_progress))
                }else if(progress >= 61){
                    bindingCheckIn.imageViewDay5.setImageResource((R.drawable.checked_in_progress))
                }else if(progress >= 76){
                    bindingCheckIn.imageViewDay6.setImageResource((R.drawable.checked_in_progress))
                }
            }
        }
        bindingCheckIn.buttonRewards.setOnClickListener {
            replaceFragment(RewardsHistory())
        }
        return bindingCheckIn.root
    }

//    private fun checkProgress(){
//        if
//    }


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

        val fragmentManager = getActivity()?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()

        // Selecting which part of the UI should be replaced by the fragment
        // in this case its the frameLayout in activity_main.xml
        fragmentTransaction?.replace(R.id.frameLayout, fragment)
        fragmentTransaction?.commit()
    }

}