package com.jurabek.asynctask.controllers

import com.jurabek.asynctask.dtos.UserInfoResultDto
import com.jurabek.asynctask.services.UserService
import com.jurabek.asynctask.services.UserServiceUnitTest
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono

class UserInfoControllerUnitTests {

    @Test
    fun `given input controller should return user info`() {
        val userInfoResult = UserInfoResultDto(UserServiceUnitTest.fakeUser, listOf(UserServiceUnitTest.fakePost))
        val userInfoService = mock<UserService> {
            on { getUserInfo(1) } doReturn Mono.just(userInfoResult)
        }
        val controller = UserInfoController(userInfoService)

        val result = controller.getUserDetails(1).block()

        Assert.assertThat(result!!.userInfo, IsEqual.equalTo(UserServiceUnitTest.fakeUser))
        Assert.assertThat(result.posts[0], IsEqual.equalTo(UserServiceUnitTest.fakePost))
    }
}