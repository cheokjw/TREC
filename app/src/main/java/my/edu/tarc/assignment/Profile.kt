package my.edu.tarc.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import my.edu.tarc.assignment.databinding.FragmentProfileBinding
import kotlin.math.E

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
        bindingProfile.buttonEditInfo.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.cvAvatar -> {
                val showPopUpAvatar = Avatar()
                showPopUpAvatar.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
            }

            R.id.buttonEditInfo -> {
                val showPopUpEdit = EditProfile()
                showPopUpEdit.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
            }

            /*R.id.buttonLogout -> {

            }*/
        }

    }


}


