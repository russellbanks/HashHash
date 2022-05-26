import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
fun CounterUi(counter: Counter) {
    val state by counter.state.subscribeAsState()

    Column {
        Text(text = state.count.toString())

        Button(onClick = counter::increment) {
            Text("Increment")
        }
    }
}

@Composable
fun CounterUi2(counter: Counter) {
    val state by counter.state.subscribeAsState()

    Column {
        Text(text = state.count.toString())

        Button(onClick = counter::increment) {
            Text("Inrejnfadjkafos")
        }
    }
}

@Composable
fun CounterUi3(counter: Counter) {
    val state by counter.state.subscribeAsState()

    Column {
        Text(text = state.count.toString())

        Button(onClick = counter::increment) {
            Text("fsafasfawfaw")
        }
    }
}