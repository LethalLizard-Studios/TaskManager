package net.group15.taskmanager.data

import com.google.gson.annotations.SerializedName
import java.util.Date

/**
 * A Constructor for tasks.
 * Author: Leland Carter
 */

class Task private constructor(
    val name: String?,
    val description: String?,
    // TODO: Don't know if it will be used yet, ignore for now
    @SerializedName("user_tags")
    val tags: UserTags?,
    @SerializedName("starting_time")
    val startingTime: Date?,
    @SerializedName("ending_time")
    val endingTime: Date?) {

    data class Builder(
        var name: String? = null,
        var description: String? = null,
        var tags: UserTags? = null,
        var startingTime: Date? = null,
        var endingTime: Date? = null) {

        // Setters for task parameters
        fun name(name: String) = apply { this.name = name }
        fun description(description: String) = apply { this.description = description }
        fun tags(tags: UserTags) = apply { this.tags = tags }
        fun startTime(startingTime: Date) = apply { this.startingTime = startingTime }
        fun endTime(endingTime: Date) = apply { this.endingTime = endingTime }

        // Build and set the parameters to this task
        fun build() = Task(name, description, tags, startingTime, endingTime)
    }
}