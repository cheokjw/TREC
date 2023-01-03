package my.edu.tarc.assignment
import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import my.edu.tarc.assignment.databinding.FragmentQuizBinding
import java.util.*
import kotlin.collections.ArrayList


class Quiz : Fragment(), View.OnClickListener {


    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var bindingQuiz : FragmentQuizBinding
    private var mCurrentPosition:Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOption: Int = 0
    private var mCorrectTotal: Int = 0
    private var mCorrectPercentage: Double = 0.0
    // TODO: Change to Gamecoin from database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingQuiz = FragmentQuizBinding.inflate(layoutInflater)
        return bindingQuiz.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity: FragmentActivity? = activity
        // Get data from database for current question position
        val mainActivity: MainActivity = getActivity() as MainActivity
        val sessionUser = mainActivity.getSessionUser()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("user")
        mQuestionsList = Constants.getQuestions()
        databaseReference.child(sessionUser).get().addOnSuccessListener {

            if (it.exists()) {
                mCurrentPosition = it.child("currentPosition").value.toString().toInt()
                val c = Calendar.getInstance()
                val day = c.get(Calendar.DAY_OF_MONTH)
                val dbQuestionDate = it.child("questionDate").value.toString().toInt()

                if (dbQuestionDate != day) {
                    val random = Random()
                    val set = random.nextInt(2) + 1

                    if(set == 1){
                        mQuestionsList = ArrayList(mQuestionsList!!.drop(10))
                    }else if(set == 2){
                        mQuestionsList = ArrayList(mQuestionsList!!.dropLast(10))
                    }
                }
                setQuestions()
            }
        }
        bindingQuiz.btnAnswer1.setOnClickListener(this)
        bindingQuiz.btnAnswer2.setOnClickListener(this)
        bindingQuiz.btnAnswer3.setOnClickListener(this)
        bindingQuiz.btnAnswer4.setOnClickListener(this)

    }


    fun setQuestions() {
        val question: Question = mQuestionsList!!.get(mCurrentPosition - 1)

        // Modifying the UI to show questions
        bindingQuiz.tvQuestionNo.text = "$mCurrentPosition " + "/" +" 10"
        bindingQuiz.tvQuestion.text = question.question
        bindingQuiz.btnAnswer1.text = question.optionOne
        bindingQuiz.btnAnswer2.text = question.optionTwo
        bindingQuiz.btnAnswer3.text = question.optionThree
        bindingQuiz.btnAnswer4.text = question.optionFour

        if (mCurrentPosition > 1){
            bindingQuiz.tvHint.text = question.hint
        }
    }


    override fun onClick(v: View) {
        val answer = mQuestionsList!!.get(mCurrentPosition - 1).correctAns
        val mainActivity: MainActivity = getActivity() as MainActivity
        val sessionUser = mainActivity.getSessionUser()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("user")

        when(v.id) {
            // First Answer
            R.id.btnAnswer1 -> {
                mSelectedOption = 1

                if(mCurrentPosition < 10) {
                    if(mSelectedOption == answer) {
                        mediaPlayer = MediaPlayer.create(activity, R.raw.correct)
                        mediaPlayer?.start()
                        mCurrentPosition++
                        mCorrectTotal++
                        setQuestions()
                        mCorrectPercentage = mCorrectTotal.toDouble() / mCurrentPosition
                        Log.i("End", mCorrectPercentage.toString())

                        databaseReference.child(sessionUser).get().addOnSuccessListener {

                            if (it.exists()) {

                                var dbGameCoin = it.child("gameCoin").value.toString().toInt()

                                var gameCoin = if (dbGameCoin == null){
                                    0
                                }else {
                                    dbGameCoin
                                }

                                gameCoin += 5
                                val c = Calendar.getInstance()
                                val day = c.get(Calendar.DAY_OF_MONTH)

                                databaseReference.child(sessionUser).child("questionDate").setValue(day)
                                databaseReference.child(sessionUser).child("quizCorrect").setValue(mCorrectPercentage)
                                databaseReference.child(sessionUser).child("gameCoin").setValue(gameCoin)
                                databaseReference.child(sessionUser).child("currentPosition").setValue(mCurrentPosition)
                            }

                        }
                    }else {
                        mediaPlayer = MediaPlayer.create(activity, R.raw.wrong)
                        mediaPlayer?.start()
                        mCurrentPosition++
                        setQuestions()
                        mCorrectPercentage = mCorrectTotal.toDouble() / mCurrentPosition
                        Log.i("End", mCorrectPercentage.toString())

                        databaseReference.child(sessionUser).get().addOnSuccessListener {
                            if (it.exists()) {
                                val c = Calendar.getInstance()
                                val day = c.get(Calendar.DAY_OF_MONTH)

                                databaseReference.child(sessionUser).child("questionDate").setValue(day)
                                databaseReference.child(sessionUser).child("quizCorrect").setValue(mCorrectPercentage)
                                databaseReference.child(sessionUser).child("currentPosition").setValue(mCurrentPosition)
                            }
                        }
                    }
                }else {
                    Log.i("Test","else triggered")
                    Toast.makeText(activity, "Maximum Quiz achieved! Please come back tomorrow for more quiz!", Toast.LENGTH_SHORT).show()

                }
            }

            // Second Answer
            R.id.btnAnswer2 -> {
                mSelectedOption = 2
                if(mCurrentPosition < 10) {
                    if(mSelectedOption == answer) {
                        mediaPlayer = MediaPlayer.create(activity, R.raw.correct)
                        mediaPlayer?.start()
                        mCurrentPosition++
                        mCorrectTotal++
                        setQuestions()
                        mCorrectPercentage = mCorrectTotal.toDouble() / mCurrentPosition
                        Log.i("End", mCorrectPercentage.toString())

                        databaseReference.child(sessionUser).get().addOnSuccessListener {

                            if (it.exists()) {

                                var dbGameCoin = it.child("gameCoin").value.toString().toInt()

                                var gameCoin = if (dbGameCoin == null){
                                    0
                                }else {
                                    dbGameCoin
                                }

                                gameCoin += 5
                                val c = Calendar.getInstance()
                                val day = c.get(Calendar.DAY_OF_MONTH)
                                databaseReference.child(sessionUser).child("questionDate").setValue(day)
                                databaseReference.child(sessionUser).child("quizCorrect").setValue(mCorrectPercentage)
                                databaseReference.child(sessionUser).child("gameCoin").setValue(gameCoin)
                                databaseReference.child(sessionUser).child("currentPosition").setValue(mCurrentPosition)
                            }

                        }

                    }else {
                        mediaPlayer = MediaPlayer.create(activity, R.raw.wrong)
                        mediaPlayer?.start()
                        mCurrentPosition++
                        setQuestions()
                        mCorrectPercentage = mCorrectTotal.toDouble() / mCurrentPosition
                        Log.i("End", mCorrectPercentage.toString())
                        databaseReference.child(sessionUser).get().addOnSuccessListener {
                            if (it.exists()) {
                                val c = Calendar.getInstance()
                                val day = c.get(Calendar.DAY_OF_MONTH)
                                databaseReference.child(sessionUser).child("questionDate").setValue(day)
                                databaseReference.child(sessionUser).child("quizCorrect").setValue(mCorrectPercentage)
                                databaseReference.child(sessionUser).child("currentPosition").setValue(mCurrentPosition)
                            }
                        }
                    }
                }else {
                    Log.i("Test","else triggered")
                    Toast.makeText(activity, "Maximum Quiz achieved! Please come back tomorrow for more quiz!", Toast.LENGTH_SHORT).show()
                }

            }

            // Third Answer
            R.id.btnAnswer3 -> {
                mSelectedOption = 3
                if(mCurrentPosition < 10) {
                    if(mSelectedOption == answer) {
                        mediaPlayer = MediaPlayer.create(activity, R.raw.correct)
                        mediaPlayer?.start()
                        mCurrentPosition++
                        mCorrectTotal++
                        setQuestions()
                        mCorrectPercentage = mCorrectTotal.toDouble() / mCurrentPosition
                        Log.i("End", mCorrectPercentage.toString())

                        databaseReference.child(sessionUser).get().addOnSuccessListener {

                            if (it.exists()) {

                                var dbGameCoin = it.child("gameCoin").value.toString().toInt()

                                var gameCoin = if (dbGameCoin == null){
                                    0
                                }else {
                                    dbGameCoin
                                }

                                gameCoin += 5
                                val c = Calendar.getInstance()
                                val day = c.get(Calendar.DAY_OF_MONTH)
                                databaseReference.child(sessionUser).child("questionDate").setValue(day)
                                databaseReference.child(sessionUser).child("quizCorrect").setValue(mCorrectPercentage)
                                databaseReference.child(sessionUser).child("gameCoin").setValue(gameCoin)
                                databaseReference.child(sessionUser).child("currentPosition").setValue(mCurrentPosition)
                            }

                        }

                    }else {
                        mediaPlayer = MediaPlayer.create(activity, R.raw.wrong)
                        mediaPlayer?.start()
                        mCurrentPosition++
                        setQuestions()
                        mCorrectPercentage = mCorrectTotal.toDouble() / mCurrentPosition
                        Log.i("End", mCorrectPercentage.toString())
                        databaseReference.child(sessionUser).get().addOnSuccessListener {
                            if (it.exists()) {
                                val c = Calendar.getInstance()
                                val day = c.get(Calendar.DAY_OF_MONTH)
                                databaseReference.child(sessionUser).child("questionDate").setValue(day)
                                databaseReference.child(sessionUser).child("quizCorrect").setValue(mCorrectPercentage)
                                databaseReference.child(sessionUser).child("currentPosition").setValue(mCurrentPosition)
                            }
                        }
                    }
                }else {
                    Log.i("Test","else triggered")
                    Toast.makeText(activity, "Maximum Quiz achieved! Please come back tomorrow for more quiz!", Toast.LENGTH_SHORT).show()
                }
            }

            // Fourth Answer
            R.id.btnAnswer4 -> {
                mSelectedOption = 4
                if(mCurrentPosition < 10) {
                    if(mSelectedOption == answer) {
                        mediaPlayer = MediaPlayer.create(activity, R.raw.correct)
                        mediaPlayer?.start()
                        mCurrentPosition++
                        mCorrectTotal++
                        setQuestions()
                        mCorrectPercentage = mCorrectTotal.toDouble() / mCurrentPosition
                        Log.i("End", mCorrectPercentage.toString())

                        databaseReference.child(sessionUser).get().addOnSuccessListener {

                            if (it.exists()) {

                                var dbGameCoin = it.child("gameCoin").value.toString().toInt()

                                var gameCoin = if (dbGameCoin == null){
                                    0
                                }else {
                                    dbGameCoin
                                }

                                gameCoin += 5
                                val c = Calendar.getInstance()
                                val day = c.get(Calendar.DAY_OF_MONTH)
                                databaseReference.child(sessionUser).child("questionDate").setValue(day)
                                databaseReference.child(sessionUser).child("quizCorrect").setValue(mCorrectPercentage)
                                databaseReference.child(sessionUser).child("gameCoin").setValue(gameCoin)
                                databaseReference.child(sessionUser).child("currentPosition").setValue(mCurrentPosition)
                            }

                        }

                    }else {
                        mediaPlayer = MediaPlayer.create(activity, R.raw.wrong)
                        mediaPlayer?.start()
                        mCurrentPosition++
                        setQuestions()
                        mCorrectPercentage = mCorrectTotal.toDouble() / mCurrentPosition
                        Log.i("End", mCorrectPercentage.toString())
                        databaseReference.child(sessionUser).get().addOnSuccessListener {
                            if (it.exists()) {
                                val c = Calendar.getInstance()
                                val day = c.get(Calendar.DAY_OF_MONTH)
                                databaseReference.child(sessionUser).child("questionDate").setValue(day)
                                databaseReference.child(sessionUser).child("quizCorrect").setValue(mCorrectPercentage)
                                databaseReference.child(sessionUser).child("currentPosition").setValue(mCurrentPosition)
                            }
                        }
                    }
                }else {
                    Log.i("Test","else triggered")
                    Toast.makeText(activity, "Maximum Quiz achieved! Please come back tomorrow for more quiz!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
