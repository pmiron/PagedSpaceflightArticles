package md.pavel.icehoney.pagedspaceflightarticles.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import md.pavel.icehoney.pagedspaceflightarticles.data.response.Article
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("api/v2/articles")
    suspend fun getArticles(
        @Query("_start") start: Int,
        @Query("_limit") limit: Int,
    ): Response<List<Article>>

    companion object {
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        fun getApiService() = Retrofit.Builder()
            .baseUrl("https://test.spaceflightnewsapi.net/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(APIService::class.java)
    }
}
