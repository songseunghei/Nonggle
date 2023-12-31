package com.capstone.nongglenonggle.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.capstone.nongglenonggle.R
import com.capstone.nongglenonggle.databinding.ActivityFirstBinding
import com.capstone.nongglenonggle.presentation.view.signup.SignupActivity
import com.capstone.nongglenonggle.presentation.view.login.LoginActivity
import com.capstone.nongglenonggle.presentation.viewModel.FirstViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*

class FirstActivity : AppCompatActivity() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val activityScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    private val viewModel: FirstViewModel by viewModels()
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_first)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val loginBtn = binding.loginBtn
        val signupBtn = binding.signupBtn


        val intent = Intent(this, LoginActivity::class.java)
        val intent2 = Intent(this, SignupActivity::class.java)

        activityScope.launch {
            loginBtn.setOnClickListener {
                startActivity(intent) }

            signupBtn.setOnTouchListener{
                    view,event->
                when(event.action){
                    MotionEvent.ACTION_DOWN ->{
                        view.isSelected = !view.isSelected
                        viewModel.setButtonSelected()
                        updateTextColor()
                        startActivity(intent2)
                    }
                }
                false
            }
        }

        }


    private fun updateTextColor()
    {
        val activeText = if(viewModel.isSelected) R.color.m1 else
            R.color.g2
        binding.signupBtn.setTextColor(ContextCompat.getColor(this,activeText))
    }

    fun googleSignup(view: View){
        Toast.makeText(this,"검수 중입니다",Toast.LENGTH_SHORT).show()
    }
    fun kakaoSignup(view: View){
        Toast.makeText(this,"검수 중입니다",Toast.LENGTH_SHORT).show()
    }
    override fun onPause()
    {
        activityScope.cancel()
        super.onPause()
    }

    }
