package my.edu.tarc.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import my.edu.tarc.assignment.databinding.FragmentPotionshopBinding
import my.edu.tarc.assignment.databinding.FragmentRewardsHistoryBinding

class RewardsHistory : Fragment() {

    private lateinit var bindingRewards: FragmentRewardsHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingRewards = FragmentRewardsHistoryBinding.inflate(inflater)
        bindingRewards.imageButtonRewardHistoryBack.setOnClickListener() {
            replaceFragment(CheckIn())
        }
        return bindingRewards.root
    }


    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = getActivity()?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()

        // Selecting which part of the UI should be replaced by the fragment
        // in this case its the frameLayout in activity_main.xml
        fragmentTransaction?.replace(R.id.frameLayout, fragment)
        fragmentTransaction?.commit()
    }
}