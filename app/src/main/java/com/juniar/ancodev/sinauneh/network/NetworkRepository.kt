package com.juniar.ancodev.sinauneh.network

class NetworkRepository(private val networkService: NetworkService) {

    suspend fun getAllPosts() = networkService.getAllPosts()
}