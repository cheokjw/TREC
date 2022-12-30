package my.edu.tarc.assignment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import my.edu.tarc.assignment.databinding.FragmentNewsBinding


class News : Fragment() {

    private lateinit var bindingNews: FragmentNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingNews = FragmentNewsBinding.inflate(layoutInflater)
        return bindingNews.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity: FragmentActivity? = activity

        bindingNews.cvWho.setOnClickListener{
            Toast.makeText(activity, "Guiding you to WHO website", Toast.LENGTH_LONG).show()
            val url = "https://www.who.int"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        bindingNews.cvGec.setOnClickListener{
            Toast.makeText(activity, "Guiding you to GEC website", Toast.LENGTH_LONG).show()
            val url = "https://www.gec.org.my"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        bindingNews.cvCr.setOnClickListener{
            Toast.makeText(activity, "Guiding you to GEC website", Toast.LENGTH_LONG).show()
            val url = "https://www.charityright.my"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }


    }


}