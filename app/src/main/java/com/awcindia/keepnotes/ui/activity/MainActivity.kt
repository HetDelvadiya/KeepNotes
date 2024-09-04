package com.awcindia.keepnotes.ui.activity

import android.animation.AnimatorInflater
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.awcindia.keepnotes.R
import com.awcindia.keepnotes.databinding.ActivityMainBinding
import com.awcindia.keepnotes.ui.fragment.bottomBarFragments.CheckListFragment
import com.awcindia.keepnotes.ui.fragment.bottomBarFragments.DrawFragment
import com.awcindia.keepnotes.ui.fragment.bottomBarFragments.ImageFragment
import com.awcindia.keepnotes.ui.fragment.bottomBarFragments.MicSpeakFragment
import com.awcindia.keepnotes.ui.fragment.toggleFragment.ArchiveFragment
import com.awcindia.keepnotes.ui.fragment.toggleFragment.DeletedFragment
import com.awcindia.keepnotes.ui.fragment.toggleFragment.NotesFragment
import com.awcindia.keepnotes.ui.fragment.toggleFragment.ReminderFragment
import com.awcindia.keepnotes.ui.fragment.toggleFragment.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val animator = AnimatorInflater.loadAnimator(this, R.animator.fab_animation)
        animator.setTarget(binding.fab)
        animator.start()

        // Set up the toolbar as the ActionBar
        setSupportActionBar(binding.topAppBar)

        // Set up ActionBarDrawerToggle
        toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.topAppBar,
            R.string.navigation_drawer_open, // String resource for accessibility
            R.string.navigation_drawer_close // String resource for accessibility
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState() // Synchronize the state of the drawer with the toolbar

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NotesFragment())
                .commit()
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this, AddNotesActivity::class.java)
            val options = ActivityOptions.makeCustomAnimation(
                this,
                R.anim.enter_from_right,
                R.anim.exit_to_left
            )
            // Start the new activity with the transition
            startActivity(intent, options.toBundle())

        }

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.notes -> {
                    loadFragmentForToggle(NotesFragment())
                    true
                }

                R.id.reminders -> {
                    loadFragmentForToggle(ReminderFragment())
                    true
                }

                R.id.archive -> {
                    loadFragmentForToggle(ArchiveFragment())
                    true
                }

                R.id.deleted -> {
                    loadFragmentForToggle(DeletedFragment())
                    true
                }

                R.id.setting -> {
                    loadFragmentForToggle(SettingsFragment())
                    true
                }

                else -> {
                    false
                }
            }.also {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
        }

        // Handle navigation icon click (optional if toggle is correctly set)
        binding.topAppBar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.bottomAppBar.setOnMenuItemClickListener {

            when (it.itemId) {

                R.id.check -> {
                    loadFragmentForBottom(CheckListFragment())
                    true
                }

                R.id.draw -> {
                    loadFragmentForBottom(DrawFragment())
                    true
                }

                R.id.mic -> {
                    loadFragmentForBottom(MicSpeakFragment())
                    true
                }

                R.id.image -> {
                    loadFragmentForBottom(ImageFragment())
                    true
                }

                else -> {
                    false
                }
            }
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                when {
                    // If the navigation drawer is open, close it
                    binding.drawerLayout.isDrawerOpen(GravityCompat.START) -> {
                        binding.drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    // If current fragment is not the main (NotesFragment), go back to the main fragment
                    supportFragmentManager.findFragmentById(R.id.fragment_container) !is NotesFragment -> {
                        loadFragmentForToggle(NotesFragment())
                    }
                    // If bottom fragment is visible, hide it
                    supportFragmentManager.findFragmentById(R.id.bottomFragmentContainer) != null -> {
                        supportFragmentManager.popBackStack()
                        binding.bottomFragmentContainer.visibility = View.GONE
                    }
                    // Otherwise, finish the activity
                    else -> {
                        finish()
                    }
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadFragmentForToggle(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment).addToBackStack(null)
            .commit()
    }

    private fun loadFragmentForBottom(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.bottomFragmentContainer, fragment).addToBackStack(null)
            .commit()
        binding.bottomFragmentContainer.visibility = View.VISIBLE
    }

}