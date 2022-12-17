package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import my.edu.tarc.assignment.databinding.FragmentPotionshopBinding
import my.edu.tarc.assignment.databinding.FragmentTreeBinding


class Tree : Fragment() {
    private lateinit var bindingTree:FragmentTreeBinding
    // var below need to store database
    var sprayqt = 5
    var greenqt = 5
    var goldqt = 5
    var barprocess = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingTree = FragmentTreeBinding.inflate(inflater)

        bindingTree.potionShopButton.setOnClickListener {
            replaceFragment(potionshop())
        }
        bindingTree.treeShopButton.setOnClickListener {
            replaceFragment(treeshop())
        }

        bindingTree.itemonebutton.setOnClickListener{
            if(sprayqt>=1){
                if(barprocess >=20) {
                    Toast.makeText(activity, "Collect Tree Coin Now\n Spray Balance: " + sprayqt, Toast.LENGTH_SHORT).show()
                }else{
                    sprayqt -= 1
                    barprocess += 1
                    Toast.makeText(activity, "Successfully spent 1 Spray!\n Spray Balance: "+ sprayqt , Toast.LENGTH_SHORT).show()
                    }
                replaceTree(barprocess)
                replacePhoto(barprocess)
                }else
                    Toast.makeText(activity, "Insufficient Spray Potion!\n Earn more to PLAY!!!", Toast.LENGTH_SHORT).show()
        }


        bindingTree.itemtwobutton.setOnClickListener{
            if(greenqt>=1){
                if(barprocess >=20) {
                    Toast.makeText(activity, "Collect Tree Coin Now\n Green Pot Balance: " + greenqt, Toast.LENGTH_SHORT).show()
                }else{
                    greenqt -= 1
                    barprocess += 2
                    Toast.makeText(activity, "Successfully spent 1 Green Pot!\n Green Pot Balance: "+ greenqt , Toast.LENGTH_SHORT).show()
                    }
                replaceTree(barprocess)
                replacePhoto(barprocess)
            }else
                Toast.makeText(activity, "Insufficient Green Pot!\n Earn more to PLAY!!!", Toast.LENGTH_SHORT).show()
        }

        bindingTree.itemthreebutton.setOnClickListener{
            if(goldqt>=1){
                if(barprocess >= 20) {
                    Toast.makeText(activity, "Collect Tree Coin Now\n Green Pot Balance: " + greenqt, Toast.LENGTH_SHORT).show()
                }else{
                    goldqt -= 1
                    barprocess += 4
                    Toast.makeText(activity, "Successfully spent 1 Gold Pot!\n Gold Pot Balance: "+ goldqt , Toast.LENGTH_SHORT).show()
                    }
                replaceTree(barprocess)
                replacePhoto(barprocess)
            }else
                Toast.makeText(activity, "Insufficient Gold Pot!\n Earn more to PLAY!!!", Toast.LENGTH_SHORT).show()
        }
        bindingTree.collectBtn.setOnClickListener {
            barprocess = 0
            Toast.makeText(activity, "Successfully Received 1 TREE COIN!", Toast.LENGTH_SHORT).show()
            replaceTree(barprocess)
            replacePhoto(barprocess)
            bindingTree.collectBtn.isVisible = false
        }

        return bindingTree.root

    }
    // to represent tree in different states
    private fun replaceTree(barprocess: Number){
        if (barprocess == 0)
            bindingTree.treeone.setImageResource(R.drawable.treeone)
        else if(barprocess == 1)
            bindingTree.treeone.setImageResource(R.drawable.treeone)
        else if(barprocess == 2)
            bindingTree.treeone.setImageResource(R.drawable.treeone)
        else if(barprocess == 3)
            bindingTree.treeone.setImageResource(R.drawable.treeone)
        else if(barprocess == 4)
            bindingTree.treeone.setImageResource(R.drawable.treeone)
        else if(barprocess == 5)
            bindingTree.treeone.setImageResource(R.drawable.treeone)
        else if(barprocess == 6)
            bindingTree.treeone.setImageResource(R.drawable.treeone)
        else if(barprocess == 7)
            bindingTree.treeone.setImageResource(R.drawable.treeone)
        else if(barprocess == 8)
            bindingTree.treeone.setImageResource(R.drawable.treeone)
        else if(barprocess == 9)
            bindingTree.treeone.setImageResource(R.drawable.treeone)
        else if(barprocess == 10)
            bindingTree.treeone.setImageResource(R.drawable.treetwo)
        else if(barprocess == 11)
            bindingTree.treeone.setImageResource(R.drawable.treetwo)
        else if(barprocess == 12)
            bindingTree.treeone.setImageResource(R.drawable.treetwo)
        else if(barprocess == 13)
            bindingTree.treeone.setImageResource(R.drawable.treetwo)
        else if(barprocess == 14)
            bindingTree.treeone.setImageResource(R.drawable.treetwo)
        else if(barprocess == 15)
            bindingTree.treeone.setImageResource(R.drawable.treethree)
        else if(barprocess == 16)
            bindingTree.treeone.setImageResource(R.drawable.treethree)
        else if(barprocess == 17)
            bindingTree.treeone.setImageResource(R.drawable.treethree)
        else if(barprocess == 18)
            bindingTree.treeone.setImageResource(R.drawable.treethree)
        else if(barprocess == 19)
            bindingTree.treeone.setImageResource(R.drawable.treethree)
        else if(barprocess == 20)
            bindingTree.treeone.setImageResource(R.drawable.treefour)
        else
            bindingTree.treeone.setImageResource(R.drawable.treefour)
    }

    // replace the progress bar
    private fun replacePhoto(barprocess: Number){
        if(barprocess == 0)
            bindingTree.finishbar.setImageResource(R.drawable.emptybar)
        else if(barprocess == 1)
            bindingTree.finishbar.setImageResource(R.drawable.onebar)
        else if(barprocess == 2)
            bindingTree.finishbar.setImageResource(R.drawable.twobar)
        else if(barprocess == 3)
            bindingTree.finishbar.setImageResource(R.drawable.threebar)
        else if(barprocess == 4)
            bindingTree.finishbar.setImageResource(R.drawable.fourbar)
        else if(barprocess == 5)
            bindingTree.finishbar.setImageResource(R.drawable.fivebar)
        else if(barprocess == 6)
            bindingTree.finishbar.setImageResource(R.drawable.sixbar)
        else if(barprocess == 7)
            bindingTree.finishbar.setImageResource(R.drawable.sevenbar)
        else if(barprocess == 8)
            bindingTree.finishbar.setImageResource(R.drawable.eightbar)
        else if(barprocess == 9)
            bindingTree.finishbar.setImageResource(R.drawable.ninebar)
        else if(barprocess == 10)
            bindingTree.finishbar.setImageResource(R.drawable.tenbar)
        else if(barprocess == 11)
            bindingTree.finishbar.setImageResource(R.drawable.elevenbar)
        else if(barprocess == 12)
            bindingTree.finishbar.setImageResource(R.drawable.twelvebar)
        else if(barprocess == 13)
            bindingTree.finishbar.setImageResource(R.drawable.thirdbar)
        else if(barprocess == 14)
            bindingTree.finishbar.setImageResource(R.drawable.fourthbar)
        else if(barprocess == 15)
            bindingTree.finishbar.setImageResource(R.drawable.fivethbar)
        else if(barprocess == 16)
            bindingTree.finishbar.setImageResource(R.drawable.sixthbar)
        else if(barprocess == 17)
            bindingTree.finishbar.setImageResource(R.drawable.seventhbar)
        else if(barprocess == 18)
            bindingTree.finishbar.setImageResource(R.drawable.eightthbar)
        else if(barprocess == 19)
            bindingTree.finishbar.setImageResource(R.drawable.ninethbar)
        else {
            bindingTree.finishbar.setImageResource(R.drawable.tenthbar)
            bindingTree.collectBtn.isVisible = true
        }
    }
    //     Allowing fragment to replace the main Body
    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = getActivity()?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()

        // Selecting which part of the UI should be replaced by the fragment
        // in this case its the frameLayout in activity_main.xml
        fragmentTransaction?.replace(R.id.frameLayout, fragment)
        fragmentTransaction?.commit()
    }


}