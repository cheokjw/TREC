package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import my.edu.tarc.assignment.databinding.FragmentPotionshopBinding
import my.edu.tarc.assignment.databinding.FragmentTreeshopBinding


class treeshop : Fragment() {
    private lateinit var bindingTreeShop: FragmentTreeshopBinding
    var testtreecoin = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_treeshop, container, false)
        bindingTreeShop = FragmentTreeshopBinding.inflate(inflater)
        bindingTreeShop.treeGrowthButton.setOnClickListener {
            replaceFragment(Tree())
        }
        bindingTreeShop.testtcoin.text = testtreecoin.toString()
        bindingTreeShop.fivevoucherprice.setOnClickListener {
            if(testtreecoin >= 5){
                testtreecoin -= 5
                bindingTreeShop.testtcoin.text = testtreecoin.toString()
                Toast.makeText(activity, "Got 1 5$-CASH VOUCHER!\n VOUCHER sent to email." , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient TREE COIN!\n Go PLANT TREE!!!", Toast.LENGTH_SHORT).show()
        }

        bindingTreeShop.fifthyvoucherprice.setOnClickListener {
            if(testtreecoin >= 30){
                testtreecoin -= 30
                bindingTreeShop.testtcoin.text = testtreecoin.toString()
                Toast.makeText(activity, "Got 1 50$-CASH VOUCHER!\n VOUCHER sent to email." , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient TREE COIN!\n Go PLANT TREE!!!", Toast.LENGTH_SHORT).show()
        }

        bindingTreeShop.treepricebutton.setOnClickListener {
            if(testtreecoin >= 5){
                testtreecoin -= 5
                bindingTreeShop.testtcoin.text = testtreecoin.toString()
                Toast.makeText(activity, "Successfully donate 1 TREE!\nThank for Environmental Afforestation." , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient TREE COIN!\n Go PLANT TREE!!!", Toast.LENGTH_SHORT).show()
        }

        bindingTreeShop.foodpricebutton.setOnClickListener {
            if(testtreecoin >= 1){
                testtreecoin -= 1
                bindingTreeShop.testtcoin.text = testtreecoin.toString()
                Toast.makeText(activity, "Successfully donate 20$ Food Supply!\nThanks for Helping People." , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient TREE COIN!\n Go PLANT TREE!!!", Toast.LENGTH_SHORT).show()
        }

        bindingTreeShop.waterpricebutton.setOnClickListener {
            if(testtreecoin >= 2){
                testtreecoin -= 2
                bindingTreeShop.testtcoin.text = testtreecoin.toString()
                Toast.makeText(activity, "Donate 1 Cleaner for Water Pollution!\nThanks for helping the SEA." , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient TREE COIN!\n Go PLANT TREE!!!", Toast.LENGTH_SHORT).show()
        }


        return bindingTreeShop.root
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