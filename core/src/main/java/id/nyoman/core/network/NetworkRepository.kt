package id.nyoman.core.network

class NetworkRepository(private val networkService: NetworkService) {

    suspend fun login(email: String, password: String) = networkService.login(email, password)
    suspend fun getNowPlaying() = networkService.getNowPlaying()
}