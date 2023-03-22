package net.group15.taskmanager

import java.util.*

//A category for the tag so tasks can be sorted and viewed by the similarity
enum class Category(val value: Int) {
    NONE(0), SCHOOLWORK(1), HOUSEWORK(2), JOB(3), FITNESS(4),
}

class UserTags private constructor(
    val name: String?,
    val category: Category?) {

    data class Builder(
        var name: String? = null,
        var category: Category? = null) {

        // Setters for task parameters
        fun name(name: String) = apply { this.name = name }
        fun category(category: Category) = apply { this.category = category }

        // Build and set the parameters to this task
        fun build() = UserTags(name, category)
    }

    // TODO: Save tag data on local storage
    class SaveLocally() {

    }
}