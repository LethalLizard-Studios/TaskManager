package net.group15.taskmanager

import java.util.Date

/**
 * A Constructor for the tasks.
 * Fill out the parameters to create a new task
 * Can be left null if user doesn't fill out
 */
class Task private constructor(
    val name: String?,
    val description: String?,
    val tags: UserTags?,
    val timeOfTask: Date?) {

    data class Builder(
        var name: String? = null,
        var description: String? = null,
        var tags: UserTags? = null,
        var timeOfTask: Date? = null) {

        // Setters for task parameters
        fun name(name: String) = apply { this.name = name }
        fun description(description: String) = apply { this.description = description }
        fun tags(tags: UserTags) = apply { this.tags = tags }
        fun timeOfTask(timeOfTask: Date) = apply { this.timeOfTask = timeOfTask }

        // Build and set the parameters to this task
        fun build() = Task(name, description, tags, timeOfTask)
    }

    // TODO: Save task data on local storage
    class SaveLocally() {

    }
}