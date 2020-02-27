package com.jurabek.asynctask.services

import com.jurabek.asynctask.dtos.UserInfoResultDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService @Autowired constructor(
        private val userInfoService: UserInfoService,
        private val userPostService: UserPostService) {

    fun getUserInfo(id: Int): Mono<UserInfoResultDto> {
        return userInfoService.getUserInfo(id)
                .zipWith(userPostService.getUserPosts(id).collectList()).map { UserInfoResultDto(it.t1, it.t2) }
    }
}
