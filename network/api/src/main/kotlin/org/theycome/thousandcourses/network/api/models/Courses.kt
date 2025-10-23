package org.theycome.thousandcourses.network.api.models

import com.squareup.moshi.JsonClass

/**
 * Created by Ivan Yakushev on 22.10.2025
 */
@JsonClass(generateAdapter = true)
data class Courses(
    val courses: List<Course>,
)

@JsonClass(generateAdapter = true)
data class Course(
    val hasLike: Boolean,
    val id: Int,
    val price: String,
    val publishDate: String,
    val rate: String,
    val startDate: String,
    val text: String,
    val title: String,
)
