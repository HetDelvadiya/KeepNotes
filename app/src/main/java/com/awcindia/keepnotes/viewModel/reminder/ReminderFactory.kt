package com.awcindia.keepnotes.viewModel.reminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.awcindia.keepnotes.repository.ReminderRepository

class ReminderFactory(val repository: ReminderRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReminderViewModel(repository) as T
    }
}