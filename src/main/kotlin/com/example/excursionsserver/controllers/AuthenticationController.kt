package com.example.excursionsserver.controllers

import com.example.excursionsserver.models.User
import com.example.excursionsserver.services.AuthenticationService
import org.springframework.web.bind.annotation.*

data class Login(val userName: String, val password: String)

@RestController
@RequestMapping("/excursions/api/v1/auth")
class AuthenticationController(private val authenticationService: AuthenticationService) {

    @GetMapping("users")
    fun getUsers(): List<User> {
        return authenticationService.getUsers()
    }

    @PostMapping("users")
    fun addUser(@RequestBody user: User) =
        authenticationService.addUser(user)

    @GetMapping("users/exists/{userName}")
    fun userExists(@PathVariable(name="userName") userName: String) =
        authenticationService.userNameTaken(userName)

    @PostMapping("users/login")
    fun login(@RequestBody login: Login): User? {
        return authenticationService.findUserByUserNameAndPassword(login.userName, login.password)
    }

}