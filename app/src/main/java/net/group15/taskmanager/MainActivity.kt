package net.group15.taskmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


// Main task manager screen
// All tasks will be viewed here
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

// add a task (save for the day)
// task title (string)
// task start time (hour)
// task estimated time to complete (hour)
class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
    }
}

// add a goal screen (save for the time defined)
// goal title
// goal start time (hour)
// goal estimated time to complete (hour)
// goal duration (weeks)
class AddGoalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_goal)
    }
}

// View a task (Might remove this screen)
class ViewTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_task)
    }
}

// About us screen
class AboutUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
    }
}


// Show this screen first time the application is downloaded
class TutorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
    }
}
