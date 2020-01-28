package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var bender: Bender
    lateinit var question: TextView
    lateinit var picture: ImageView
    lateinit var sendIcon: ImageView
    lateinit var answer: EditText

    private val currentStateQuestion = "QUESTION"
    private val currentStateStatus= "STATUS"
    private val currentFailCounter = "FAILS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentQuestion = savedInstanceState?.getString(currentStateQuestion) ?: Bender.Question.NAME.name
        val currentStatus = savedInstanceState?.getString(currentStateStatus) ?: Bender.Status.NORMAL.name
        val currentFails = savedInstanceState?.getInt(currentFailCounter) ?: 0

        bender = Bender(Bender.Status.valueOf(currentStatus), Bender.Question.valueOf(currentQuestion), currentFails)
        question = tv_text
        question.text = bender.askQuestion()

        picture = iv_bender

        var statusColor = bender.status.color
        picture.setColorFilter(Color.rgb(statusColor.first, statusColor.second, statusColor.third), PorterDuff.Mode.MULTIPLY)

        sendIcon = iv_send
        sendIcon.setOnClickListener(this)

        answer = et_message

    }

    override fun onClick(p0: View?) {
        if (p0?.id == sendIcon.id){
            val benderResolution = bender.listenAnswer(answer.text.toString())
            answer.setText("")

            question.setText(benderResolution.first)
            val (r,g,b) = benderResolution.second
            picture.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)


        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString(currentStateQuestion, bender.question.name)
        outState?.putString(currentStateStatus, bender.status.name)
        outState?.putInt(currentFailCounter, bender.failCounter)
    }

}
