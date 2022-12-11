package my.edu.tarc.assignment

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import my.edu.tarc.assignment.databinding.FragmentQuizBinding


class Quiz : Fragment(), View.OnClickListener {

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var bindingQuiz : FragmentQuizBinding
    private var mCurrentPosition:Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOption: Int = 0

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

        mQuestionsList = Constants.getQuestions()
        setQuestions()
        bindingQuiz.btnAnswer1.setOnClickListener(this)
        bindingQuiz.btnAnswer2.setOnClickListener(this)
        bindingQuiz.btnAnswer3.setOnClickListener(this)
        bindingQuiz.btnAnswer4.setOnClickListener(this)

    }

    private fun setQuestions() {
        val question: Question = mQuestionsList!!.get(mCurrentPosition - 1)

        // Modifying the UI to show questions
        bindingQuiz.tvQuestionNo.text = "$mCurrentPosition " + "/" +" 10"
        bindingQuiz.tvQuestion.text = question.question
        bindingQuiz.btnAnswer1.text = question.optionOne
        bindingQuiz.btnAnswer2.text = question.optionTwo
        bindingQuiz.btnAnswer3.text = question.optionThree
        bindingQuiz.btnAnswer4.text = question.optionFour
    }

    override fun onClick(v: View) {
        val answer = mQuestionsList!!.get(mCurrentPosition - 1).correctAns
        when(v.id) {
            R.id.btnAnswer1 -> {
                mSelectedOption = 1
                if(mSelectedOption == answer){
                    mediaPlayer = MediaPlayer.create(activity, R.raw.correct)
                    mediaPlayer?.start()
                    mCurrentPosition++
                    setQuestions()
                }else {
                    mediaPlayer = MediaPlayer.create(activity, R.raw.wrong)
                    mediaPlayer?.start()
                    mCurrentPosition++
                    setQuestions()
                }
            }
            R.id.btnAnswer2 -> {
                mSelectedOption = 2
                if(mSelectedOption == answer){
                    mediaPlayer = MediaPlayer.create(activity, R.raw.correct)
                    mediaPlayer?.start()
                    mCurrentPosition++
                    setQuestions()
                }else {
                    mediaPlayer = MediaPlayer.create(activity, R.raw.wrong)
                    mediaPlayer?.start()
                    mCurrentPosition++
                    setQuestions()
                }
            }
            R.id.btnAnswer3 -> {
                mSelectedOption = 3
                if(mSelectedOption == answer){
                    mediaPlayer = MediaPlayer.create(activity, R.raw.correct)
                    mediaPlayer?.start()
                    mCurrentPosition++
                    setQuestions()
                }else {
                    mediaPlayer = MediaPlayer.create(activity, R.raw.wrong)
                    mediaPlayer?.start()
                    mCurrentPosition++
                    setQuestions()
                }
            }
            R.id.btnAnswer4 -> {
                mSelectedOption = 4
                if(mSelectedOption == answer){
                    mediaPlayer = MediaPlayer.create(activity, R.raw.correct)
                    mediaPlayer?.start()
                    mCurrentPosition++
                    setQuestions()
                }else {
                    mediaPlayer = MediaPlayer.create(activity, R.raw.wrong)
                    mediaPlayer?.start()
                    mCurrentPosition++
                    setQuestions()

                }
            }
        }
    }


}