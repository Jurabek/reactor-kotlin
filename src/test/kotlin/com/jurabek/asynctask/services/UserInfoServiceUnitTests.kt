package com.jurabek.asynctask.services

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.client.WebClient

class UserInfoServiceUnitTests {
    @Test
    fun `when correct id it getUserInfo should return user info`() {
        val mockWebServer = MockWebServer()
        val webClientBuilder: WebClient.Builder = WebClient.builder().baseUrl(mockWebServer.url("/").toString().removeSuffix("/"))
        val userInfoService = UserInfoService(webClientBuilder)
        mockWebServer.enqueue(MockResponse().addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Cache-Control", "no-cache").setBody(USER_INFO_RESPONSE))
        val userInfo = userInfoService.getUserInfo(1).block()

        Assert.assertNotNull(userInfo)
        Assert.assertThat(userInfo!!.id, IsEqual.equalTo(1.toLong()))
        Assert.assertThat(userInfo.name, IsEqual.equalTo("Leanne Graham"))
        Assert.assertThat(userInfo.email, IsEqual.equalTo("Sincere@april.biz"))
    }

    companion object {
        const val USER_INFO_RESPONSE = """
        {
          "id": 1,
          "name": "Leanne Graham",
          "username": "Bret",
          "email": "Sincere@april.biz",
          "address": {
            "street": "Kulas Light",
            "suite": "Apt. 556",
            "city": "Gwenborough",
            "zipcode": "92998-3874",
            "geo": {
              "lat": "-37.3159",
              "lng": "81.1496"
            }
          },
          "phone": "1-770-736-8031 x56442",
          "website": "hildegard.org",
          "company": {
            "name": "Romaguera-Crona",
            "catchPhrase": "Multi-layered client-server neural-net",
            "bs": "harness real-time e-markets"
          }
        }
        """
    }
}