package com.geded.adv160420008week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation

class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            val turnTxt = view.findViewById<TextView>(R.id.txtTurn)
            turnTxt.text = "$playerName's Turn"
        }
        val submitBtn = view.findViewById<Button>(R.id.btnSubmit)

        var x = (0..50).shuffled().last()
        var y = (0..50).shuffled().last()
        var result = x+y
        var score = 0
        var status = true

        val numberLbl = view.findViewById<TextView>(R.id.lblNumber)
        numberLbl.text = "$x + $y"

        submitBtn.setOnClickListener {
            val answerTxt = view.findViewById<EditText>(R.id.txtAnswer)
            val answer = answerTxt.text.toString().toInt()
            if (answer == result) {
                score++
                x = (0..50).shuffled().last()
                y = (0..50).shuffled().last()
                result = x + y
                numberLbl.text = "$x + $y"
                answerTxt.setText("")
            } else {
                status = false;
                val action = GameFragmentDirections.actionResultFragment(score)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

}