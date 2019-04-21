package com.example.excursionsserver.services

import com.example.excursionsserver.models.Excursion
import com.example.excursionsserver.repositories.ExcursionRepository
import org.springframework.stereotype.Service

@Service
class ExcursionService(private val repository: ExcursionRepository) {
    fun getAll(): List<Excursion> =
        repository.findAll()

}