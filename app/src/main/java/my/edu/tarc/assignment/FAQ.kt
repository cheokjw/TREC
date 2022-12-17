package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import my.edu.tarc.assignment.databinding.FragmentFAQBinding

// Need to use View interface to implement onClick function
class FAQ : Fragment(), View.OnClickListener {

    private lateinit var bindingFAQ: FragmentFAQBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingFAQ = FragmentFAQBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return bindingFAQ.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingFAQ.cvQ1.setOnClickListener(this)
        bindingFAQ.cvQ2.setOnClickListener(this)
        bindingFAQ.cvQ3.setOnClickListener(this)
        bindingFAQ.cvQ4.setOnClickListener(this)

        bindingFAQ.btnEmail.setOnClickListener {

        }

    }

    override fun onClick(v:View){

        when(v.id){
            R.id.cvQ1 -> {
                val showPopUp = popUpQ1()
                showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
            }

            R.id.cvQ2 -> {
                val showPopUp = popUpQ2()
                showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
            }

            R.id.cvQ3 -> {
                val showPopUp = popUpQ3()
                showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
            }

            R.id.cvQ4 -> {
                val showPopUp = popUpQ4()
                showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
            }
        }

    }

}


