package com.example.excursionsserver.repositories

import com.example.excursionsserver.models.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String> {
    fun findByUserNameAndPassword(userName: String, password: String): User?

    fun existsByUserName(userName: String): Boolean
    fun findByUserName(userName: String): User
}