package helper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import io.klogging.Klogging
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

object Ktor : Klogging {

    @Composable
    fun createHttpClient(): HttpClient {
        val scope = rememberCoroutineScope()
        return HttpClient(CIO) {
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
}
