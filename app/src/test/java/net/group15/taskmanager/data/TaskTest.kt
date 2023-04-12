package net.group15.taskmanager.data

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.Instant
import java.util.*

class TaskTest {

    @Test
    fun `task builder matched correctly`() {
        val testDate = Date.from(Instant.now())

        val task = Task.Builder()
            .name("Test Name")
            .description("This is my description!")
            .startTime(testDate)
            .endTime(testDate)
            .build()

        assertThat(task.name).matches("Test Name")
        assertThat(task.description).matches("This is my description!")
        assertThat(task.startingTime.toString()).matches(testDate.toString())
        assertThat(task.endingTime.toString()).matches(testDate.toString())
    }

    @Test
    fun `task builder user tags match`() {
        val testDate = Date.from(Instant.now())

        val tags = UserTags.Builder()
            .name("My Schoolwork")
            .category(Category.SCHOOL)
            .build()

        val task = Task.Builder()
            .name("Test Name")
            .description("This is my description!")
            .startTime(testDate)
            .endTime(testDate)
            .tags(tags)
            .build()

        assertThat(task.name).matches("Test Name")
        assertThat(task.description).matches("This is my description!")
        assertThat(task.startingTime.toString()).matches(testDate.toString())
        assertThat(task.endingTime.toString()).matches(testDate.toString())

        assertThat(task.tags.hashCode().toString()).matches(tags.hashCode().toString())
    }
}