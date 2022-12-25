package my.edu.tarc.assignment

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.alpha
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import my.edu.tarc.assignment.databinding.FragmentAvatarBinding

class Avatar : DialogFragment() {

    private lateinit var bindingAvatar: FragmentAvatarBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingAvatar = FragmentAvatarBinding.inflate(layoutInflater)
        return bindingAvatar.root
    }


    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //select avatar function
        bindingAvatar.imageViewAvatar1.setOnClickListener {
            bindingAvatar.imageViewAvatar1.alpha = 1F
            bindingAvatar.imageViewAvatar2.alpha = 0.5F
            bindingAvatar.imageViewAvatar3.alpha = 0.5F
            bindingAvatar.imageViewAvatar4.alpha = 0.5F
            bindingAvatar.imageViewAvatar5.alpha = 0.5F
            bindingAvatar.imageViewAvatar6.alpha = 0.5F
        }

        bindingAvatar.imageViewAvatar2.setOnClickListener {
            bindingAvatar.imageViewAvatar1.alpha = 0.5F
            bindingAvatar.imageViewAvatar2.alpha = 1F
            bindingAvatar.imageViewAvatar3.alpha = 0.5F
            bindingAvatar.imageViewAvatar4.alpha = 0.5F
            bindingAvatar.imageViewAvatar5.alpha = 0.5F
            bindingAvatar.imageViewAvatar6.alpha = 0.5F
        }

        bindingAvatar.imageViewAvatar3.setOnClickListener {
            bindingAvatar.imageViewAvatar1.alpha = 0.5F
            bindingAvatar.imageViewAvatar2.alpha = 0.5F
            bindingAvatar.imageViewAvatar3.alpha = 1F
            bindingAvatar.imageViewAvatar4.alpha = 0.5F
            bindingAvatar.imageViewAvatar5.alpha = 0.5F
            bindingAvatar.imageViewAvatar6.alpha = 0.5F
        }

        bindingAvatar.imageViewAvatar4.setOnClickListener {
            bindingAvatar.imageViewAvatar1.alpha = 0.5F
            bindingAvatar.imageViewAvatar2.alpha = 0.5F
            bindingAvatar.imageViewAvatar3.alpha = 0.5F
            bindingAvatar.imageViewAvatar4.alpha = 1F
            bindingAvatar.imageViewAvatar5.alpha = 0.5F
            bindingAvatar.imageViewAvatar6.alpha = 0.5F
        }

        bindingAvatar.imageViewAvatar5.setOnClickListener {
            bindingAvatar.imageViewAvatar1.alpha = 0.5F
            bindingAvatar.imageViewAvatar2.alpha = 0.5F
            bindingAvatar.imageViewAvatar3.alpha = 0.5F
            bindingAvatar.imageViewAvatar4.alpha = 0.5F
            bindingAvatar.imageViewAvatar5.alpha = 1F
            bindingAvatar.imageViewAvatar6.alpha = 0.5F
        }

        bindingAvatar.imageViewAvatar6.setOnClickListener {
            bindingAvatar.imageViewAvatar1.alpha = 0.5F
            bindingAvatar.imageViewAvatar2.alpha = 0.5F
            bindingAvatar.imageViewAvatar3.alpha = 0.5F
            bindingAvatar.imageViewAvatar4.alpha = 0.5F
            bindingAvatar.imageViewAvatar5.alpha = 0.5F
            bindingAvatar.imageViewAvatar6.alpha = 1F
        }



        bindingAvatar.buttonSelectAvatar.setOnClickListener{
            Toast.makeText(activity, "Avatar Changed", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        bindingAvatar.imageButtonQuit.setOnClickListener {
            dismiss()
        }

    }


}