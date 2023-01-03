package my.edu.tarc.assignment

import android.app.AlertDialog
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
import java.util.Random


class treeshop : Fragment() {
    private lateinit var bindingTreeShop: FragmentTreeshopBinding
    lateinit var database: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    var treecoin = 0
    var gamecoin = 0
    var username = ""
    var email = " "
    var name = " "
    var random = getRandomNumber()
    var nname = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getSess()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference().child("user").child(username)
        bindingTreeShop = FragmentTreeshopBinding.inflate(inflater)
        //retrieve username
        databaseReference.child("username").get().addOnSuccessListener {
            nname = it.value.toString()
            bindingTreeShop.textViewUserName.text = it.value.toString()
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
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
                alertDialog("Got 1 5$-CASH VOUCHER!\n VOUCHER sent to email.", treecoin - 5)
                fivevoucherSendEmail()
            }else
                Toast.makeText(activity, "Insufficient TREE COIN!\n Go PLANT TREE!!!", Toast.LENGTH_SHORT).show()
        }

        bindingTreeShop.fifthyvoucherprice.setOnClickListener {
            if(treecoin >= 30){
                alertDialog("Got 1 50$-CASH VOUCHER!\n VOUCHER sent to email.", treecoin - 30)
                fifthvoucherSendEmail()
            }else
                Toast.makeText(activity, "Insufficient TREE COIN!\n Go PLANT TREE!!!", Toast.LENGTH_SHORT).show()
        }

        bindingTreeShop.treepricebutton.setOnClickListener {
            if(treecoin >= 5){
                alertDialog("Successfully donate 1 TREE!\nThank for Environmental Afforestation.", treecoin - 5)
                treeSendEmail()
            }else
                Toast.makeText(activity, "Insufficient TREE COIN!\n Go PLANT TREE!!!", Toast.LENGTH_SHORT).show()
        }

        bindingTreeShop.foodpricebutton.setOnClickListener {
            if(treecoin >= 1){
                alertDialog("Successfully donate 20$ Food Supply!\nThanks for Helping People.", treecoin - 1)
                foodSendEmail()
            }else
                Toast.makeText(activity, "Insufficient TREE COIN!\n Go PLANT TREE!!!", Toast.LENGTH_SHORT).show()

        }

        bindingTreeShop.waterpricebutton.setOnClickListener {
            if(treecoin >= 2){
                alertDialog("Donate 1 Cleaner for Water Pollution!\nThanks for helping the SEA.", treecoin - 2)
                pollutionSendEmail()
            }else
                Toast.makeText(activity, "Insufficient TREE COIN!\n Go PLANT TREE!!!", Toast.LENGTH_SHORT).show()
        }


        return bindingTreeShop.root
    }

    private fun alertDialog(text : String, tCoin: Int){
        var buyDialog = AlertDialog.Builder(activity)
            .setTitle("Confirmation")
            .setMessage("Are you sure to purchase?")
            .setPositiveButton("Yes"){_,_->
                Toast.makeText(activity, text , Toast.LENGTH_SHORT).show()
                var sprayUpdate = hashMapOf<String, Any>(
                    "treeCoin" to tCoin
                )
                databaseReference.updateChildren(sprayUpdate)
                databaseReference.child("treeCoin").get().addOnSuccessListener {
                    treecoin = it.value.toString().toInt()
                    bindingTreeShop.textViewTreeCoin.text = it.value.toString()
                }.addOnFailureListener {
                    Log.e("firebase", "Error getting data", it)
                }
            }
            .setNegativeButton("No"){_,_ ->
                Toast.makeText(activity, "Please purchase again", Toast.LENGTH_SHORT).show()
            }.create().show()
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

    fun getRandomNumber(): Int {
        val r = Random()
        return (10000000000 + r.nextInt(900000000)).toInt()
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
            mimeMessage.setText("Hello " +name+ ", \n\nCongratulation!!\n\nYou have successfully received 5$ Cash Voucher.\n\nRedemption Location:" +
                    "\nAeon Big Ampang\nLot 613, 47636.47638, Taman Dagang Permai, 68000 Ampang.\nAEON BiG Batu Pahat\nNo 1B, Jalan Persiaran Flora Utama, Taman Flora Utama, 83000 Batu Pahat, Johor Darul Takzim" +
                    "\nAEON BiG Bukit Minyak\nNo 1,Tingkat Bukit Minyak 9, Taman Bukit Minyak, 14000 Bukit Mertajam, Penang.\n" +
                    "AEON BiG Danau Kota\nLot PT 9834, Jln. Langkawi, Tmn. Danau Kota, Mukim Setapak, 53000 K.L.\n\n\n Voucher code: V" + random)
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

    private fun fifthvoucherSendEmail() {
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
            mimeMessage.subject = "TREC 50$ Voucher"
            mimeMessage.setText("Hello " +name+ ", \n\nCongratulation!!\n\nYou have successfully received 50$ Cash Voucher.\n\nRedemption Location:" +
                    "\nAeon Big Ampang\nLot 613, 47636.47638, Taman Dagang Permai, 68000 Ampang.\nAEON BiG Batu Pahat\nNo 1B, Jalan Persiaran Flora Utama, Taman Flora Utama, 83000 Batu Pahat, Johor Darul Takzim" +
                    "\nAEON BiG Bukit Minyak\nNo 1,Tingkat Bukit Minyak 9, Taman Bukit Minyak, 14000 Bukit Mertajam, Penang.\n" +
                    "AEON BiG Danau Kota\nLot PT 9834, Jln. Langkawi, Tmn. Danau Kota, Mukim Setapak, 53000 K.L.\n\n\n Voucher code: V" + random)
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

    private fun treeSendEmail() {
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
            mimeMessage.subject = "TREC Green House"
            mimeMessage.setText("Hello " +name+ ", \n\nThank you for your kindness!!\n\nYou have successfully Donated 1 tree. \n\nThe tree will be plant in very soon." +
                    "\n\nYou can click the link below to know more about Global Environment Centre.\n https://www.gec.org.my")
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

    private fun foodSendEmail() {
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
            mimeMessage.subject = "TREC Food Supply"
            mimeMessage.setText("Hello " +name+ ", \n\nThank you for your kindness!!\n\nYou have successfully Donated 20$ worth of supply. \n\nSoon, the supply will be delivered." +
                    "\n\nYou can click the link below to know more about Charity Right.\n https://www.charityright.my")
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

    private fun pollutionSendEmail() {
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
            mimeMessage.subject = "TREC Water Pollution"
            mimeMessage.setText("Hello " +name+ ", \n\nThank you for your kindness!!\n\nYou have successfully Donated 1 Cleaner." +
                    "\n\nYou can click the link below to know more about World Health Organization.\n https://www.who.int")
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