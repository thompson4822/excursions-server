package com.example.excursionsserver.services

import com.example.excursionsserver.models.User
import com.example.excursionsserver.repositories.UserRepository
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(private val userRepository: UserRepository) {

    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()

    fun findUserByUserNameAndPassword(userName: String, password: String) =
        userRepository.findByUserNameAndPassword(userName, password)

    fun getUsers(): List<User> =
        userRepository.findAll()

    fun addUser(user: User) {
        //val secure = user.copy(password = passwordEncoder.encode(user.password))
        userRepository.insert(user)
    }

    fun userNameTaken(userName: String) =
        userRepository.existsByUserName(userName)

    fun findByUserName(userName: String): User =
        userRepository.findByUserName(userName)

    fun count() = userRepository.count()
}