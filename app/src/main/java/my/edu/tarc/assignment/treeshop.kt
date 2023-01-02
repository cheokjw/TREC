package my.edu.tarc.assignment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import my.edu.tarc.assignment.databinding.FragmentTreeshopBinding
import javax.mail.*
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class treeshop : Fragment() {
    private lateinit var bindingTreeShop: FragmentTreeshopBinding
    lateinit var database: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    var treecoin = 0
    var gamecoin = 0
    var username = ""
    var email = " "
    var name = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_treeshop, container, false)
        getSess()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference().child("user").child(username)
        bindingTreeShop = FragmentTreeshopBinding.inflate(inflater)

        //retrieve game coin
        databaseReference.child("gameCoin").get().addOnSuccessListener {
            gamecoin = it.value.toString().toInt()
            bindingTreeShop.textViewGameCoin.text = it.value.toString()
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }

        //retrieve tree coin
        databaseReference.child("treeCoin").get().addOnSuccessListener {
            treecoin = it.value.toString().toInt()
            bindingTreeShop.textViewTreeCoin.text = it.value.toString()
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }


        //retrieve user email
        databaseReference.child("email").get().addOnSuccessListener {
            email = it.value.toString()
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }

        //retrieve user name
        databaseReference.child("fullname").get().addOnSuccessListener {
            name = it.value.toString()
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }

        bindingTreeShop.treeGrowthButton.setOnClickListener {
            replaceFragment(Tree())
        }
        bindingTreeShop.fivevoucherprice.setOnClickListener {
            if(treecoin >= 5){
                treecoin -= 5
                Toast.makeText(activity, "Got 1 5$-CASH VOUCHER!\n VOUCHER sent to email." , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient TREE COIN!\n Go PLANT TREE!!!", Toast.LENGTH_SHORT).show()
            var gamecoinUpdate = hashMapOf<String, Any>(
                "treeCoin" to treecoin
            )
            databaseReference.updateChildren(gamecoinUpdate)
            //retrieve tree coin
            databaseReference.child("treeCoin").get().addOnSuccessListener {
                treecoin = it.value.toString().toInt()
                bindingTreeShop.textViewTreeCoin.text = it.value.toString()
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
            fivevoucherSendEmail()
        }

        bindingTreeShop.fifthyvoucherprice.setOnClickListener {
            if(treecoin >= 30){
                treecoin -= 30
                Toast.makeText(activity, "Got 1 50$-CASH VOUCHER!\n VOUCHER sent to email." , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient TREE COIN!\n Go PLANT TREE!!!", Toast.LENGTH_SHORT).show()
            var gamecoinUpdate = hashMapOf<String, Any>(
                "treeCoin" to treecoin
            )
            databaseReference.updateChildren(gamecoinUpdate)
            //retrieve tree coin
            databaseReference.child("treeCoin").get().addOnSuccessListener {
                treecoin = it.value.toString().toInt()
                bindingTreeShop.textViewTreeCoin.text = it.value.toString()
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
        }

        bindingTreeShop.treepricebutton.setOnClickListener {
            if(treecoin >= 5){
                treecoin -= 5
                Toast.makeText(activity, "Successfully donate 1 TREE!\nThank for Environmental Afforestation." , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient TREE COIN!\n Go PLANT TREE!!!", Toast.LENGTH_SHORT).show()
            var gamecoinUpdate = hashMapOf<String, Any>(
                "treeCoin" to treecoin
            )
            databaseReference.updateChildren(gamecoinUpdate)
            //retrieve tree coin
            databaseReference.child("treeCoin").get().addOnSuccessListener {
                treecoin = it.value.toString().toInt()
                bindingTreeShop.textViewTreeCoin.text = it.value.toString()
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
        }

        bindingTreeShop.foodpricebutton.setOnClickListener {
            if(treecoin >= 1){
                treecoin -= 1
                bindingTreeShop.textViewTreeCoin.text = treecoin.toString()
                Toast.makeText(activity, "Successfully donate 20$ Food Supply!\nThanks for Helping People." , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient TREE COIN!\n Go PLANT TREE!!!", Toast.LENGTH_SHORT).show()
            var gamecoinUpdate = hashMapOf<String, Any>(
                "treeCoin" to treecoin
            )
            databaseReference.updateChildren(gamecoinUpdate)
            //retrieve tree coin
            databaseReference.child("treeCoin").get().addOnSuccessListener {
                treecoin = it.value.toString().toInt()
                bindingTreeShop.textViewTreeCoin.text = it.value.toString()
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
        }

        bindingTreeShop.waterpricebutton.setOnClickListener {
            if(treecoin >= 2){
                treecoin -= 2
                bindingTreeShop.textViewTreeCoin.text = treecoin.toString()
                Toast.makeText(activity, "Donate 1 Cleaner for Water Pollution!\nThanks for helping the SEA." , Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(activity, "Insufficient TREE COIN!\n Go PLANT TREE!!!", Toast.LENGTH_SHORT).show()
            var gamecoinUpdate = hashMapOf<String, Any>(
                "treeCoin" to treecoin
            )
            databaseReference.updateChildren(gamecoinUpdate)
            //retrieve tree coin
            databaseReference.child("treeCoin").get().addOnSuccessListener {
                treecoin = it.value.toString().toInt()
                bindingTreeShop.textViewTreeCoin.text = it.value.toString()
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
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

    private fun getSess(){
        val preferences = requireContext().getSharedPreferences("sess_store", Context.MODE_PRIVATE)
        val sess_username = preferences.getString("username", "")
        if (sess_username != ""){
            username = sess_username.toString()
        }else{
            Toast.makeText(activity, "failed to retrieve username", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fivevoucherSendEmail() {
        try {
            val stringSenderEmail = "lowwc-wm21@student.tarc.edu.my"
            val stringReceiverEmail = email
            val stringPasswordSenderEmail = "020420100779"
            val stringHost = "smtp.gmail.com"
            val properties = System.getProperties()
            properties["mail.smtp.host"] = stringHost
            properties["mail.smtp.port"] = "465"
            properties["mail.smtp.ssl.enable"] = "true"
            properties["mail.smtp.auth"] = "true"
            val session = Session.getInstance(properties, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail)
                }
            })
            val mimeMessage = MimeMessage(session)
            mimeMessage.addRecipient(Message.RecipientType.TO, InternetAddress(stringReceiverEmail))
            mimeMessage.subject = "TREC 5$ Voucher"
            mimeMessage.setText("Hello " +name)
            val thread = Thread {
                try {
                    Transport.send(mimeMessage)
                } catch (e: MessagingException) {
                    e.printStackTrace()
                }
            }
            thread.start()
        } catch (e: AddressException) {
            e.printStackTrace()
        } catch (e: MessagingException) {
            e.printStackTrace()
        }
    }


}