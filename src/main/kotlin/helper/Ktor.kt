package helper

import io.klogging.Klogging
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

object Ktor : Klogging {
    fun createHttpClient(scope: CoroutineScope) = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    scope.launch(Dispatchers.Default) { this@Ktor.logger.info(message) }
                }
            }
            level = LogLevel.INFO
        }
    }
}
