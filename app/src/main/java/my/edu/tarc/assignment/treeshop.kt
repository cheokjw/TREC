package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import my.edu.tarc.assignment.databinding.FragmentPotionshopBinding
import my.edu.tarc.assignment.databinding.FragmentTreeshopBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [treeshop.newInstance] factory method to
 * create an instance of this fragment.
 */
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment treeshop.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            treeshop().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}