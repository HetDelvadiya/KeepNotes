package com.awcindia.keepnotes.ui.activity

import DatePickerFragment
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.awcindia.keepnotes.R
import com.awcindia.keepnotes.databinding.ActivityAddNotesBinding
import com.awcindia.keepnotes.repository.ReminderRepository
import com.awcindia.keepnotes.ui.fragment.dateAndTime.TimePickerFragment
import com.awcindia.keepnotes.viewModel.reminder.ReminderFactory
import com.awcindia.keepnotes.viewModel.reminder.ReminderViewModel

class AddNotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNotesBinding
    private lateinit var viewModel: ReminderViewModel


    private var isPinned = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val repository = ReminderRepository(this)
        viewModel =
            ViewModelProvider(this, ReminderFactory(repository)).get(ReminderViewModel::class.java)

        binding.pin.setOnClickListener {
            isPinned = !isPinned
            val imageRes = if (isPinned) R.drawable.push_pin else R.drawable.ic_pin
            binding.pin.setImageResource(imageRes)
        }

        binding.reminders.setOnClickListener {
            showDatePicker()
        }

        viewModel.checkAndRequestExactAlarmPermission()
    }

    private fun showDatePicker() {
        val datePicker = DatePickerFragment { year, month, day ->
            showTimePicker(year, month, day)
        }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun showTimePicker(year: Int, month: Int, day: Int) {
        val timePicker = TimePickerFragment { hour, minute ->
            displaySelectedDateTime(year, month, day, hour, minute)
        }
        timePicker.show(supportFragmentManager, "timePicker")
    }

    private fun displaySelectedDateTime(year: Int, month: Int, day: Int, hour: Int, minute: Int) {
        val selectedDateTime = "$year-${month} $day $hour:$minute"
        //  binding.selectedDateTimeTextView.text = "Selected Date and Time: $selectedDateTime"
        viewModel.setReminder(year, month, day, hour, minute)
    }
}
