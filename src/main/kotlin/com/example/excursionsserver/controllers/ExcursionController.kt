package com.example.excursionsserver.controllers

import com.example.excursionsserver.models.Excursion
import com.example.excursionsserver.models.Slug
import com.example.excursionsserver.services.ExcursionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/excursions/api/v1/excursions")
class ExcursionController(private val excursionService: ExcursionService) {
    @GetMapping
    fun getAll(): List<Excursion> =
        excursionService.getAll()

    @GetMapping("/{timestamp}/{text}")
    fun getBySlug(@PathVariable("timestamp") timestamp: Long, @PathVariable("text") text: String): Excursion =
        excursionService.findBySlug(Slug(timestamp, text))
}