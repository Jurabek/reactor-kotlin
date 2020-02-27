package com.jurabek.asynctask.controllers

import com.jurabek.asynctask.constants.ApiKeys
import com.jurabek.asynctask.dtos.UserInfoResultDto
import com.jurabek.asynctask.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping(path = [ApiKeys.API_PREFIX])
class UserInfoController @Autowired constructor(private val userService: UserService) {

    @GetMapping("/{userId}/details")
    fun getUserDetails(@PathVariable userId: Int): Mono<UserInfoResultDto> {
        return userService.getUserInfo(userId)
    }
}