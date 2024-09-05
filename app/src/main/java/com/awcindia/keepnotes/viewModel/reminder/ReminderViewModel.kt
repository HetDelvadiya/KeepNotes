package com.awcindia.keepnotes.viewModel.reminder

import android.os.Build
import androidx.lifecycle.ViewModel
import com.awcindia.keepnotes.repository.ReminderRepository

class ReminderViewModel(private val reminderRepository: ReminderRepository) : ViewModel() {

    fun setReminder(year: Int, month: Int, day: Int, hour: Int, minute: Int) {
        reminderRepository.setReminder(year, month, day, hour, minute)
    }

    fun checkAndRequestExactAlarmPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) { // Android 12 (API level 31) and above
            reminderRepository.checkAndRequestExactAlarmPermission()
        }
    }
}
