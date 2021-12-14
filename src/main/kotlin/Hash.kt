import java.security.MessageDigest

fun ByteArray.hash(algorithm: String): String {
    return MessageDigest.getInstance(algorithm)
        .digest(this)
        .fold("") { str, it -> str + "%02x".format(it) }
}