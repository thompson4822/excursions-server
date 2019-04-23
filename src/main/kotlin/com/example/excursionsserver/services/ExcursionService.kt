package com.example.excursionsserver.services

import com.example.excursionsserver.models.Excursion
import com.example.excursionsserver.models.Slug
import com.example.excursionsserver.repositories.ExcursionRepository
import org.springframework.stereotype.Service
import com.github.slugify.Slugify
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

public fun String.purify(): String =
    this.filter { it.isLetterOrDigit() || it == ' ' }

public fun String.slugged(): Slug {
    return Slug(
            timestamp = ChronoUnit.SECONDS.between(LocalDateTime.of(2019, 1, 1, 0, 0, 0), LocalDateTime.now()),
            text = Slugify().slugify(this.purify())
    )
}


@Service
class ExcursionService(private val repository: ExcursionRepository) {
    fun getAll(): List<Excursion> =
        repository.findAll()

    fun add(excursion: Excursion): Excursion {
        val slugged = excursion.copy(slug = excursion.title.slugged())
        return repository.insert(slugged)
    }

    fun findBySlug(slug: Slug): Excursion =
        repository.findBySlugTimestampAndSlugText(slug.timestamp, slug.text)

}