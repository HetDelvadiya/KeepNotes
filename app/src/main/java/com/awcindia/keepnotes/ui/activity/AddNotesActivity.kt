package com.awcindia.keepnotes.ui.activity


import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.awcindia.keepnotes.R
import com.awcindia.keepnotes.database.NotesDatabase
import com.awcindia.keepnotes.databinding.ActivityAddNotesBinding
import com.awcindia.keepnotes.model.NotesModel
import com.awcindia.keepnotes.repository.NotesRepository
import com.awcindia.keepnotes.repository.ReminderRepository
import com.awcindia.keepnotes.ui.fragment.buttomSheet.BackgroundFragment
import com.awcindia.keepnotes.ui.fragment.buttomSheet.InsertBottomSheet
import com.awcindia.keepnotes.ui.fragment.dateAndTime.DatePickerFragment
import com.awcindia.keepnotes.ui.fragment.dateAndTime.TimePickerFragment
import com.awcindia.keepnotes.viewModel.notes.NotesFactory
import com.awcindia.keepnotes.viewModel.notes.NotesViewModel
import com.awcindia.keepnotes.viewModel.reminder.ReminderFactory
import com.awcindia.keepnotes.viewModel.reminder.ReminderViewModel
import java.util.Date

class AddNotesActivity : AppCompatActivity(), InsertBottomSheet.OnImageSelectedListener {

    private lateinit var binding: ActivityAddNotesBinding
    private lateinit var reminderViewModel: ReminderViewModel
    private lateinit var notesViewModel: NotesViewModel
    private var isPinned = false
    private var isReminder = false
    private var backgroundImage: Int? = null
    private var imageUri: Uri? = null

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

        // Reminder ViewModel
        val reminderRepository = ReminderRepository(this)
        reminderViewModel = ViewModelProvider(
            this, ReminderFactory(reminderRepository)
        )[ReminderViewModel::class.java]
        reminderViewModel.checkAndRequestExactAlarmPermission()

        // Notes ViewModel
        val notesDao = NotesDatabase.getDatabase(this).notesDao()
        val notesRepository = NotesRepository(notesDao)
        notesViewModel =
            ViewModelProvider(this, NotesFactory(notesRepository))[NotesViewModel::class.java]

        allClickListener()
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
        reminderViewModel.setReminder(year, month, day, hour, minute)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.notes_bottom_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.deleteNotes -> {
                true
            }

            R.id.copyNotes -> {
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun allClickListener() {
        binding.notesTitle.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                saveNote() // Save note when focus is lost
            }
        }

        binding.notesToolbar.setNavigationOnClickListener {
            finish()
        }

        binding.notesDiscription.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                saveNote() // Save note when focus is lost
            }
        }

        binding.pin.setOnClickListener {
            isPinned = !isPinned
            val imageRes = if (isPinned) R.drawable.push_pin else R.drawable.ic_pin
            binding.pin.setImageResource(imageRes)
        }

        binding.reminders.setOnClickListener {
            showDatePicker()
            isReminder = true
        }

        binding.insertMenu.setOnClickListener {
            val bottomSheet = InsertBottomSheet()
            bottomSheet.setOnImageSelectedListener(this)
            bottomSheet.show(supportFragmentManager, "com.awcindia.keepnotes.ui.fragment.buttomSheet.InsertBottomSheet")
        }

        binding.background.setOnClickListener {
            val backgroundSheet = BackgroundFragment()
            backgroundSheet.onColorSelected = { selectedImageRes ->
                backgroundImage = selectedImageRes
                binding.main.setBackgroundResource(backgroundImage!!)
            }
            backgroundSheet.show(supportFragmentManager, "BackgroundSheet")
        }
    }

    override fun onImageSelected(uri: Uri) {
        imageUri = uri
        binding.imageNote.setImageURI(uri) // Display the image in the ImageView
        binding.imageNote.visibility = View.VISIBLE
        saveNote()
    }

    private fun saveNote() {
        val title = binding.notesTitle.text.toString()
        val description = binding.notesDiscription.text.toString()
        val backgroundImage = backgroundImage // Set this if applicable
        val images = imageUri // Handle image URIs if any
        val reminder = isReminder // Assuming you have a method to get the reminder date

        if (title.isNotBlank() && description.isNotBlank()) {
            val note = NotesModel(
                title = title,
                description = description,
                images = images.toString(),
                backgroundImage = backgroundImage,
                isPinned = isPinned,
                isArchive = false, // Default value
                reminder = reminder,
                createdAt = Date(),
                updatedAt = Date()
            )
            notesViewModel.insertNotes(note) // Insert note into database
            finish() // Close activity after saving
        }
    }

    override fun onPause() {
        super.onPause()
        saveNote() // Save note when the activity or fragment goes into the background
    }
}
