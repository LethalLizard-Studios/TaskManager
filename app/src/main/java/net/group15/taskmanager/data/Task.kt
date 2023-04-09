package net.group15.taskmanager.data

import com.google.gson.annotations.SerializedName
import java.util.Date

/**
 * A Constructor for tasks.
 * Author: Leland Carter
 */

class Task private constructor(
    val id: String?,
    val name: String?,
    val description: String?,
    // TODO: Don't know if it will be used yet, ignore for now
    @SerializedName("user_tags")
    val tags: UserTags?,
    @SerializedName("starting_time")
    val startingTime: Date?,
    @SerializedName("ending_time")
    val endingTime: Date?):java.io.Serializable {

    constructor():this(null,null,null,null,null,null)
    data class Builder(
        var id: String? = null,
        var name: String? = null,
        var description: String? = null,
        var tags: UserTags? = null,
        var startingTime: Date? = null,
        var endingTime: Date? = null) {

        // Setters for task parameters
        fun name(name: String) = apply { this.name = name }
        fun id(id: String) = apply { this.id = id }
        fun description(description: String) = apply { this.description = description }
        fun tags(tags: UserTags) = apply { this.tags = tags }
        fun startTime(startingTime: Date) = apply { this.startingTime = startingTime }
        fun endTime(endingTime: Date) = apply { this.endingTime = endingTime }

        // Build and set the parameters to this task
        fun build() = Task(id,name, description, tags, startingTime, endingTime)
    }
}