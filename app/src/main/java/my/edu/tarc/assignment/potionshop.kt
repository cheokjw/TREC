package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import my.edu.tarc.assignment.databinding.FragmentPotionshopBinding
import my.edu.tarc.assignment.databinding.FragmentTreeBinding


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


}