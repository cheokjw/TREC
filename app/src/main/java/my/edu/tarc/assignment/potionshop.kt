package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import my.edu.tarc.assignment.databinding.FragmentPotionshopBinding
import my.edu.tarc.assignment.databinding.FragmentTreeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [potionshop.newInstance] factory method to
 * create an instance of this fragment.
 */
class potionshop : Fragment() {
    private lateinit var bindingPotion: FragmentPotionshopBinding
    private lateinit var bindingTree: FragmentTreeBinding
    var test = 10000
    var sprayqtt = 0
    var greenqtt = 0
    var goldqtt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingPotion = FragmentPotionshopBinding.inflate(inflater)
        bindingPotion.testcoin.text = test.toString()

        //price
        bindingPotion.spraytagbutton.setOnClickListener{
            if (test>=500){
                    test -= 500
                    bindingPotion.testcoin.text = test.toString()
                    sprayqtt += 1
                Toast.makeText(activity, "Successfully bought 1 Spray!\n Spray Balance: "+ sprayqtt , Toast.LENGTH_SHORT).show()
                }else
                Toast.makeText(activity, "Insufficient GAME COIN!\n Spray Balance: " + sprayqtt, Toast.LENGTH_SHORT).show()
            }

        bindingPotion.greentagbutton.setOnClickListener{
            if(test>=800) {
                test -= 800
                bindingPotion.testcoin.text = test.toString()
                greenqtt += 1
                Toast.makeText(activity, "Successfully bought 1 Green Pot!\n Green Pot Balance: "+ greenqtt , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient GAME COIN!\n Green Pot Balance: " + greenqtt, Toast.LENGTH_SHORT).show()
        }
        bindingPotion.goldtagbutton.setOnClickListener{
            if(test>=1500) {
                test -= 1500
                bindingPotion.testcoin.text = test.toString()
                goldqtt += 1
                Toast.makeText(activity, "Successfully bought 1 Gold Pot!\n Gold Pot Balance: "+ goldqtt , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient GAME COIN!\n Gold Pot Balance: " + goldqtt, Toast.LENGTH_SHORT).show()
        }



        bindingPotion.treeGrowthButton.setOnClickListener {
            replaceFragment(Tree())
        }

        return bindingPotion.root
    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = getActivity()?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frameLayout, fragment)
        fragmentTransaction?.commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment potionshop.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            potionshop().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}