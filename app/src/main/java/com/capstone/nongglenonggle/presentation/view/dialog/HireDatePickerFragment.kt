package com.capstone.nongglenonggle.presentation.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.capstone.nongglenonggle.databinding.FragmentHireDatePickerBinding
import com.capstone.nongglenonggle.presentation.viewModel.farmer.FarmerNoticeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HireDatePickerFragment : BottomSheetDialogFragment() {
    private val viewModel: FarmerNoticeViewModel by activityViewModels()
    private var _binding : FragmentHireDatePickerBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHireDatePickerBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            binding.closebtn.setOnClickListener{
                dismiss()
            }

            val year = binding.yearpicker
            val month = binding.monthpicker
            val day = binding.daypicker

            var minValue = 0
            var maxValue = 0
            var monthMaxValue=0
            var monthMinValue=0
            var DayMaxValue=0
            var DayMinValue=0

                minValue = 2022
                maxValue = 2033
                monthMinValue = 1
                monthMaxValue = 12
                DayMinValue = 1
                DayMaxValue=31

                year.wrapSelectorWheel = false
                month.wrapSelectorWheel=false
                day.wrapSelectorWheel=false

                year.minValue=2022
                month.minValue = 1
                day.minValue = 1

                year.maxValue=2033
                month.maxValue = 12
                day.maxValue = 31


            val yearValue = (minValue..maxValue).map{"${it}년"}.toTypedArray()
            year.displayedValues = yearValue

            val MonthValue = (monthMinValue..monthMaxValue).map{"${it}월"}.toTypedArray()
            month.displayedValues = MonthValue

            val DateValue = (DayMinValue..DayMaxValue).map{"${it}일"}.toTypedArray()
            day.displayedValues = DateValue


            binding.confirmbtn.setOnClickListener{
                val year = year.value
                val month = month.value
                val day = day.value
                val currentList = viewModel._DateList.value ?: emptyList()
                viewModel._DateList.value = currentList + listOf(year,month,day)
                dismiss()
            }
        }


}