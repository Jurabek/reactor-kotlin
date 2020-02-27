package com.jurabek.asynctask.services

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.client.WebClient

class UserPostsUnitTests {
    @Test
    fun `given specific userId it should return user posts`() {
        val mockWebServer = MockWebServer()
        val webClientBuilder: WebClient.Builder = WebClient.builder().baseUrl(mockWebServer.url("/").toString().removeSuffix("/"))
        val userPostService = UserPostService(webClientBuilder)
        mockWebServer.enqueue(MockResponse().addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Cache-Control", "no-cache").setBody(USER_POSTS))

        val userPosts = userPostService.getUserPosts(1).collectList().block()
        Assert.assertNotNull(userPosts)
        Assert.assertThat(userPosts!!.size, IsEqual.equalTo(2))
    }

    companion object {
        const val USER_POSTS = """
        [
              {
                "userId": 1,
                "id": 1,
                "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
              },
              {
                "userId": 1,
                "id": 2,
                "title": "qui est esse",
                "body": "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
              }
        ]
        """
    }
}