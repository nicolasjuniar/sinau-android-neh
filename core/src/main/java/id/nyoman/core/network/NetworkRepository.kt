package id.nyoman.core.network

class NetworkRepository(private val networkService: NetworkService) {

    suspend fun login(email: String, password: String) = networkService.login(email, password)
    suspend fun getNowPlaying(page: Int = 1) = networkService.getNowPlaying(page = page)
}
