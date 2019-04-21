package com.example.excursionsserver

import com.example.excursionsserver.models.*
import com.example.excursionsserver.repositories.ExcursionRepository
import com.example.excursionsserver.repositories.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.LocalDateTime

// The exclusion is to turn off security for now. It can be safely removed at a later point
@SpringBootApplication(exclude = [
    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration::class,
    org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration::class
])
class ExcursionsServerApplication(private val userRepository: UserRepository, private val excursionRepository: ExcursionRepository) {
    @Bean
    fun initialize()= CommandLineRunner {
        //userRepository.deleteAll()
        if(userRepository.count() == 0L) {
            println("Adding Users:")
            val defaultUsers = listOf(
                User(firstName = "Jon", lastName = "Snow", userName = "jsnow", email="jsnow@nightswatch.gov", password = "mormont"),
                User(firstName = "Aria", lastName = "Stark", userName = "dangermouse", email="astark@winterfell.gov", password = "needle"),
                User(firstName = "Brandon", lastName = "Stark", userName = "wargboy", email="bstark@winterfell.gov", password = "hodor"),
                User(firstName = "Sansa", lastName = "Stark", userName = "sstark", email="sstark@winterfell.gov", password = "password"),
                User(firstName = "Theon", lastName = "Greyjoy", userName = "reek", email="theon@pike.gov", password = "whatami"),
                User(firstName = "Walder", lastName = "Frey", userName = "wfrey", email="walder.frey@thetwins.gov", password = "neverlate"),
                User(firstName = "Robyn", lastName = "Arryn", userName = "rdog", email="rarryn.frey@aery.gov", password = "girlz"),
                User(firstName = "Dani", lastName = "Targaryen", userName = "dragonrider", email="dragon.rider@ruler.gov", password = "dracarys"),
                User(firstName = "Pete", lastName = "Baelish", userName = "littlefinger", email="pbaelish@harrenhal.gov", password = "chaosisaladder")
            )
            defaultUsers.forEach { userRepository.insert(it) }

            println("Adding Excursions:")
            val defaultExcursions = listOf<Excursion>(
                Excursion(title = "The Red Wedding",
                        organizer = findUser("wfrey"),
                        description = "Come join the fun at Frey's Place as we celebrate the wedding of the young wolf, Bobby Stark, by hosting a surprise party! We'll have a killer band on hand (Castamere) and plenty to eat and drink. Families welcome!",
                        attendees = listOf(findUser("jsnow"), findUser("dangermouse"), findUser("wargboy")),
                        where = Address("1221 North College", "Fort Collins", "CO", "80808"),
                        startEnd = ExcursionPeriod(
                                startDateTime = LocalDateTime.of(2019, 5, 12, 14, 0,0),
                                endDateTime = LocalDateTime.of(2019, 5, 12, 14, 0,0)
                        ),
                        tags = listOf("party", "wedding", "live music", "surprise", "family friendly"),
                        imageUrl = "https://images.unsplash.com/photo-1527529482837-4698179dc6ce"
                ),
                Excursion(title = "Sky Diving For Beginners",
                        organizer = findUser("rdog"),
                        description = "You may not learn how to fly, but hopefully you will learn successful sky diving techniques. All ages welcome!",
                        attendees = listOf(findUser("jsnow"), findUser("dangermouse"), findUser("wargboy")),
                        where = Address("1283 North College", "Fort Collins", "CO", "80808"),
                        startEnd = ExcursionPeriod(
                                startDateTime = LocalDateTime.of(2019, 5, 14, 14, 0,0),
                                endDateTime = LocalDateTime.of(2019, 5, 16, 14, 0,0)
                        ),
                        tags = listOf("outdoor", "sky diving", "training", "all ages", "sports"),
                        imageUrl = "https://images.unsplash.com/photo-1474623809196-26c1d33457cc"
                ),
                Excursion(title = "Horseback Riding Down The Old Dothraki Trail",
                        organizer = findUser("dragonrider"),
                        description = "Come join me for a day of horseback riding down the Old Dothraki Trail as I retrace the path of the mighty Khals of yore. No previous horseback experience necessary.",
                        attendees = listOf(findUser("jsnow"), findUser("dangermouse"), findUser("wargboy")),
                        where = Address("901 Mountain Avenue", "Fort Collins", "CO", "80808"),
                        startEnd = ExcursionPeriod(
                                startDateTime = LocalDateTime.of(2019, 5, 21, 10, 0,0),
                                endDateTime = LocalDateTime.of(2019, 5, 21, 14, 0,0)
                        ),
                        tags = listOf("outdoor", "horseback", "all ages", "sports", "mountains"),
                        imageUrl = "https://images.unsplash.com/photo-1550785330-003a9afa3bd9"
                ),
                Excursion(title = "Pete's Wine Tasting Soiree",
                        organizer = findUser("littlefinger"),
                        description = "Want to sample the best wine Colorado has to offer? If so, head over to the House of Baelish for an evening of fun and entertainment. Adults only of course!",
                        attendees = listOf(findUser("jsnow"), findUser("dangermouse"), findUser("wargboy")),
                        where = Address("157 Baelish Blvd", "Fort Collins", "CO", "80808"),
                        startEnd = ExcursionPeriod(
                                startDateTime = LocalDateTime.of(2019, 5, 22, 18, 0,0),
                                endDateTime = LocalDateTime.of(2019, 5, 23, 2, 0,0)
                        ),
                        tags = listOf("wine tasting", "party", "adult"),
                        imageUrl = "https://images.unsplash.com/photo-1533120629284-cf5e53d76665"
                )

            )
            defaultExcursions.forEach { excursionRepository.insert(it) }

        }
        println("Database ready!")
    }

    fun findUser(userName: String): User {
        return userRepository.findByUserName(userName)
    }

}

fun main(args: Array<String>) {
    runApplication<ExcursionsServerApplication>(*args)
}
