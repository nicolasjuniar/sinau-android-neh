package com.juniar.ancodev.sinauneh.network

class NetworkRepository(private val networkService: NetworkService) {

    suspend fun getAllPosts() = networkService.getAllPosts()
    suspend fun login(email: String, password: String) = networkService.login(email, password)
    suspend fun getUsers(id: Int) = networkService.getUsers(id)
}