package com.example.excursionsserver.controllers

import com.example.excursionsserver.models.Excursion
import com.example.excursionsserver.services.ExcursionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/excursions/api/v1")
class ExcursionController(private val excursionService: ExcursionService) {
    @GetMapping("/excursions")
    fun getAll(): List<Excursion> =
        excursionService.getAll()
}