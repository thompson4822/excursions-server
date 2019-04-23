package com.example.excursionsserver.models

import com.github.slugify.Slugify
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit

data class Address(
    val street: String,
    val city: String,
    val state: String,
    val postalCode: String
)

@Document
data class User(
    @Id val id: String? = null,
    val firstName: String,
    val lastName: String,
    val userName: String,
    val password: String,
    val email: String
)

data class ExcursionComment (
    @DBRef val commenter: User,
    val title: String,
    val body: String,
    val date: LocalDate,
    val slug: Slug? = null
)

data class ExcursionPeriod (
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime
)

data class Tag (
    val name: String,
    val description: String
)

data class Slug(
    val timestamp: Long,
    val text: String
)

@Document
data class Excursion(
        @Id val id: String? = null,
        val title: String,
        val description: String,
        val imageUrl: String,
        @DBRef val organizer: User,
        val notes: String? = null,
        val where: Address? = null,
        val startEnd: ExcursionPeriod,
        @DBRef val attendees: List<User>,
        val comments: List<ExcursionComment> = emptyList(),
        val tags: List<String> = emptyList(),
        val slug: Slug? = null
)
