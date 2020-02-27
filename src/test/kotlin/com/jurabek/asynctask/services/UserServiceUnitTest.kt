package com.jurabek.asynctask.services

import com.jurabek.asynctask.dtos.*
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.mockito.internal.matchers.Contains
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class UserServiceUnitTest {
    @Test
    fun `when correct id it should return user info result`() {
        val userInfoService = mock<UserInfoService> {
            on { getUserInfo(1) } doReturn Mono.just(fakeUser)
        }
        val userPostService = mock<UserPostService> {
            on { getUserPosts(1) } doReturn Flux.just(fakePost)
        }
        val userService = UserService(userInfoService, userPostService)

        val result = userService.getUserInfo(1).block()

        Assert.assertThat(result!!.userInfo, IsEqual.equalTo(fakeUser))
        Assert.assertThat(result.posts[0], IsEqual.equalTo(fakePost))
    }

    companion object {
        val fakeUser = UserInfoDto(1,
                "test_name",
                "test_username",
                "email",
                Address("street1", "suite", "city", "1234", Geo("1", "2")),
                "phone",
                "site",
                Company("company_ name", "catch_phrase", "bs"))

        val fakePost = UserPostDto(1, 1, "title", "body")
    }
}