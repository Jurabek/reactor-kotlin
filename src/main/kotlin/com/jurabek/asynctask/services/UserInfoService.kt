package com.jurabek.asynctask.services

import com.jurabek.asynctask.dtos.UserInfoDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Service
class UserInfoService @Autowired constructor(
        private val webClientBuilder: WebClient.Builder
) {
    fun getUserInfo(userId: Int): Mono<UserInfoDto> {
        return webClientBuilder.build().get()
                .uri("/users/$userId")
                .retrieve()
                .bodyToMono()
    }
}