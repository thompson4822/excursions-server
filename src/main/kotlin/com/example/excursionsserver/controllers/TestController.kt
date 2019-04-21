package com.example.excursionsserver.controllers

import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

data class Time(val dateTime: LocalDateTime)

@RestController
@RequestMapping("/")
class TestController() {

    @GetMapping("time")
    fun getTime(): Time {
        return Time(dateTime = LocalDateTime.now())
    }

    @PostMapping("time")
    fun postTime(@RequestBody time: Time): String {
        val localDate = time.dateTime
        return "${localDate.year}/${localDate.monthValue}/${localDate.dayOfMonth} ${localDate.hour}:${localDate.minute}"
    }
}