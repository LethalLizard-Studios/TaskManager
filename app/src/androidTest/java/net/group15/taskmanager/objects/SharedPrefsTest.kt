package net.group15.taskmanager.objects

import com.google.common.truth.Truth.assertThat
import net.group15.taskmanager.data.Task
import org.junit.Test
import java.time.Instant
import java.util.Date

class SharedPrefsTest {

    @Test
    fun addingTaskList() {
        val task = Task.Builder()
            .name("Test Name")
            .description("This is my description!")
            .startTime(Date.from(Instant.now()))
            .endTime(Date.from(Instant.now()))
            .build()

        SharedPrefs.add(task)

        assertThat(SharedPrefs.taskList.size).isEqualTo(1)
    }

    @Test
    fun addingMultipleTaskList() {
        val task = Task.Builder()
            .name("Test Name")
            .description("This is my description!")
            .startTime(Date.from(Instant.now()))
            .endTime(Date.from(Instant.now()))
            .build()

        SharedPrefs.add(task)
        SharedPrefs.add(task)
        SharedPrefs.add(task)
        SharedPrefs.add(task)
        SharedPrefs.add(task)
        SharedPrefs.add(task)

        assertThat(SharedPrefs.taskList.size).isEqualTo(6)
    }

    @Test
    fun emptyTaskList() {
        assertThat(SharedPrefs.taskList.size).isEqualTo(0)
    }

    @Test
    fun removingTaskListObject() {
        val task = Task.Builder()
            .name("Test Name")
            .description("This is my description!")
            .startTime(Date.from(Instant.now()))
            .endTime(Date.from(Instant.now()))
            .build()

        SharedPrefs.add(task)
        assertThat(SharedPrefs.taskList.size).isEqualTo(1)

        SharedPrefs.remove(task)
        assertThat(SharedPrefs.taskList.size).isEqualTo(0)
    }

    @Test
    fun removingTaskListIndex() {
        val task = Task.Builder()
            .name("Test Name")
            .description("This is my description!")
            .startTime(Date.from(Instant.now()))
            .endTime(Date.from(Instant.now()))
            .build()

        SharedPrefs.add(task)
        assertThat(SharedPrefs.taskList.size).isEqualTo(1)

        SharedPrefs.remove(0)
        assertThat(SharedPrefs.taskList.size).isEqualTo(0)
    }
}