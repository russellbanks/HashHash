package components.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.appmattus.crypto.Algorithm

interface ParentInterface {
    var algorithm: Algorithm
}

open class ParentComponent : ParentInterface {
    override var algorithm: Algorithm by mutableStateOf(Algorithm.MD5)
}
