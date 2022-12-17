package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import my.edu.tarc.assignment.databinding.FragmentProfileBinding

class Profile : Fragment(), View.OnClickListener {

    private lateinit var bindingProfile:FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingProfile = FragmentProfileBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return bindingProfile.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingProfile.cvAvatar.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.cvAvatar -> {
                val showPopUp = Avatar()
                showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
            }
        }

    }


}


