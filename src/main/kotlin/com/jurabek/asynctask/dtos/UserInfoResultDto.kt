package com.jurabek.asynctask.dtos

data class UserInfoResultDto(
        val userInfo: UserInfoDto?,
        val posts: List<UserPostDto>
)