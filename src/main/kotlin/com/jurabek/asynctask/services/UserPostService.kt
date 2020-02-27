package com.jurabek.asynctask.services

import com.jurabek.asynctask.dtos.UserPostDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import reactor.core.publisher.Flux

@Service
class UserPostService(@Autowired private val webClientBuilder: WebClient.Builder) {
    fun getUserPosts(id: Int): Flux<UserPostDto> {
        return webClientBuilder.build().get()
                .uri("/posts?userId=${id}")
                .exchange()
                .flatMapMany { it.bodyToFlux<UserPostDto>() }
    }
}