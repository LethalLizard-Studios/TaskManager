package net.group15.taskmanager.data

/**
 * A Constructor for user tags & categories.
 * Author: Leland Carter
 */

//A category for the tag so tasks can be sorted and viewed by the similarity
enum class Category(val value: Int) {
    ALL(0), SCHOOL(1), HOUSEWORK(2), JOB(3), FITNESS(4), SHOPPING(5)
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
}