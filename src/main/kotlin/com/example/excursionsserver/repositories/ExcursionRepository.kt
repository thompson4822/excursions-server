package com.example.excursionsserver.repositories

import com.example.excursionsserver.models.Excursion
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ExcursionRepository : MongoRepository<Excursion, String>{
}