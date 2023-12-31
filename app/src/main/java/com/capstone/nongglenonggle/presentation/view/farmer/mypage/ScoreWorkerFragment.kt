package com.capstone.nongglenonggle.presentation.view.farmer.mypage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.capstone.nongglenonggle.R
import com.capstone.nongglenonggle.databinding.FragmentScoreWorkerBinding
import com.capstone.nongglenonggle.presentation.base.BaseFragment
import com.capstone.nongglenonggle.presentation.view.farmer.home.MainActivity
import com.capstone.nongglenonggle.presentation.viewModel.farmer.FarmerMyPageViewModel

class ScoreWorkerFragment : BaseFragment<FragmentScoreWorkerBinding>(R.layout.fragment_score_worker) {
    private val viewModel: FarmerMyPageViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= super.onCreateView(inflater, container, savedInstanceState)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        //title
        viewModel.suggestionData.observe(viewLifecycleOwner, Observer { data->
            binding.title.text = "구인자님,\n${data.userName} 일손은 어떠셨나요?"
            binding.name.text = "${data.userName} 일손"
            binding.theother.text = "${data.userGender} ∙ ${data.userYear}세"
        })

        binding.seekbar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val thumb = when(progress){
                    0 -> R.drawable.num1
                    1 -> R.drawable.num2
                    2-> R.drawable.num3
                    3 -> R.drawable.num4
                    4 -> R.drawable.num5
                    else->R.drawable.num5
                }
                seekBar?.thumb = ContextCompat.getDrawable(requireContext(), thumb)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        binding.seekbar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val thumb = when(progress){
                    0 -> R.drawable.num1
                    1 -> R.drawable.num2
                    2-> R.drawable.num3
                    3 -> R.drawable.num4
                    4 -> R.drawable.num5
                    else->R.drawable.num5
                }
                seekBar?.thumb = ContextCompat.getDrawable(requireContext(), thumb)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        binding.seekbar3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val thumb = when(progress){
                    0 -> R.drawable.num1
                    1 -> R.drawable.num2
                    2-> R.drawable.num3
                    3 -> R.drawable.num4
                    4 -> R.drawable.num5
                    else->R.drawable.num5
                }
                seekBar?.thumb = ContextCompat.getDrawable(requireContext(), thumb)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        binding.seekbar4.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val thumb = when(progress){
                    0 -> R.drawable.num1
                    1 -> R.drawable.num2
                    2-> R.drawable.num3
                    3 -> R.drawable.num4
                    4 -> R.drawable.num5
                    else->R.drawable.num5
                }
                seekBar?.thumb = ContextCompat.getDrawable(requireContext(), thumb)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        binding.edit.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val color = ContextCompat.getColor(requireContext(),R.color.m1)
                binding.complete.setBackgroundColor(color)

        }
    })

        binding.complete.setOnClickListener{
            Toast.makeText(context, "일손평가를 완료했습니다!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.farmerMypageFragment)
        }

}

    override fun onDestroyView() {
        super.onDestroyView()
    }
}