import com.appmattus.crypto.Algorithm
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandGroup
import org.pushingpixels.aurora.component.model.CommandMenuContentModel

object Menus {
    fun cascadingAlgorithmMenu(onClick: (Algorithm) -> Unit) = listOf(
        Command(text = Algorithm.Adler32.algorithmName, action = { onClick(Algorithm.Adler32) }),
        Command(
            text = Algorithm.BLAKE224.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.BLAKE224.algorithmName, action = { onClick(Algorithm.BLAKE224) }),
                        Command(text = Algorithm.BLAKE256.algorithmName, action = { onClick(Algorithm.BLAKE256) }),
                        Command(text = Algorithm.BLAKE384.algorithmName, action = { onClick(Algorithm.BLAKE384) }),
                        Command(text = Algorithm.BLAKE512.algorithmName, action = { onClick(Algorithm.BLAKE512) }),
                    )
                )
            )
        ),
        Command(
            text = Algorithm.Blake2b().algorithmName.dropLast(4),
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.Blake2b_160.algorithmName, action = { onClick(Algorithm.Blake2b_160) }),
                        Command(text = Algorithm.Blake2b_256.algorithmName, action = { onClick(Algorithm.Blake2b_256) }),
                        Command(text = Algorithm.Blake2b_384.algorithmName, action = { onClick(Algorithm.Blake2b_384) }),
                        Command(text = Algorithm.Blake2b_512.algorithmName, action = { onClick(Algorithm.Blake2b_512) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.Blake2s().algorithmName.dropLast(4),
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.Blake2s_128.algorithmName, action = { onClick(Algorithm.Blake2s_128) }),
                        Command(text = Algorithm.Blake2s_160.algorithmName, action = { onClick(Algorithm.Blake2s_160) }),
                        Command(text = Algorithm.Blake2s_224.algorithmName, action = { onClick(Algorithm.Blake2s_224) }),
                        Command(text = Algorithm.Blake2s_256.algorithmName, action = { onClick(Algorithm.Blake2s_256) })
                    )
                )
            )
        ),
        Command(text = Algorithm.Blake3().algorithmName, action = { onClick(Algorithm.Blake3()) }),
        Command(
            text = Algorithm.BMW224.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.BMW224.algorithmName, action = { onClick(Algorithm.BMW224) }),
                        Command(text = Algorithm.BMW256.algorithmName, action = { onClick(Algorithm.BMW256) }),
                        Command(text = Algorithm.BMW384.algorithmName, action = { onClick(Algorithm.BMW384) }),
                        Command(text = Algorithm.BMW512.algorithmName, action = { onClick(Algorithm.BMW512) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.cSHAKE128().algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.cSHAKE128().algorithmName, action = { onClick(Algorithm.cSHAKE128()) }),
                        Command(text = Algorithm.cSHAKE256().algorithmName, action = { onClick(Algorithm.cSHAKE256()) }),
                    )
                )
            )
        ),
        Command(
            text = Algorithm.CubeHash224.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.CubeHash224.algorithmName, action = { onClick(Algorithm.CubeHash224) }),
                        Command(text = Algorithm.CubeHash256.algorithmName, action = { onClick(Algorithm.CubeHash256) }),
                        Command(text = Algorithm.CubeHash384.algorithmName, action = { onClick(Algorithm.CubeHash384) }),
                        Command(text = Algorithm.CubeHash512.algorithmName, action = { onClick(Algorithm.CubeHash512) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.ECHO224.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.ECHO224.algorithmName, action = { onClick(Algorithm.ECHO224) }),
                        Command(text = Algorithm.ECHO256.algorithmName, action = { onClick(Algorithm.ECHO256) }),
                        Command(text = Algorithm.ECHO384.algorithmName, action = { onClick(Algorithm.ECHO384) }),
                        Command(text = Algorithm.ECHO512.algorithmName, action = { onClick(Algorithm.ECHO512) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.Fugue224.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.Fugue224.algorithmName, action = { onClick(Algorithm.Fugue224) }),
                        Command(text = Algorithm.Fugue256.algorithmName, action = { onClick(Algorithm.Fugue256) }),
                        Command(text = Algorithm.Fugue384.algorithmName, action = { onClick(Algorithm.Fugue384) }),
                        Command(text = Algorithm.Fugue512.algorithmName, action = { onClick(Algorithm.Fugue512) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.GOST3411_94.algorithmName,
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.GOST3411_94.algorithmName, action = { onClick(Algorithm.GOST3411_94) }),
                        Command(text = Algorithm.GOST3411_2012_256.algorithmName, action = { onClick(Algorithm.GOST3411_2012_256) }),
                        Command(text = Algorithm.GOST3411_2012_512.algorithmName, action = { onClick(Algorithm.GOST3411_2012_512) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.Groestl224.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.Groestl224.algorithmName, action = { onClick(Algorithm.Groestl224) }),
                        Command(text = Algorithm.Groestl256.algorithmName, action = { onClick(Algorithm.Groestl256) }),
                        Command(text = Algorithm.Groestl384.algorithmName, action = { onClick(Algorithm.Groestl384) }),
                        Command(text = Algorithm.Groestl512.algorithmName, action = { onClick(Algorithm.Groestl512) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.Hamsi224.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.Hamsi224.algorithmName, action = { onClick(Algorithm.Hamsi224) }),
                        Command(text = Algorithm.Hamsi256.algorithmName, action = { onClick(Algorithm.Hamsi256) }),
                        Command(text = Algorithm.Hamsi384.algorithmName, action = { onClick(Algorithm.Hamsi384) }),
                        Command(text = Algorithm.Hamsi512.algorithmName, action = { onClick(Algorithm.Hamsi512) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.Haraka256_256.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.Haraka256_256.algorithmName, action = { onClick(Algorithm.Haraka256_256) }),
                        Command(text = Algorithm.Haraka512_256.algorithmName, action = { onClick(Algorithm.Haraka512_256) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.HAVAL_3_128.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.HAVAL_3_128.algorithmName, action = { onClick(Algorithm.HAVAL_3_128) }),
                        Command(text = Algorithm.HAVAL_3_160.algorithmName, action = { onClick(Algorithm.HAVAL_3_160) }),
                        Command(text = Algorithm.HAVAL_3_192.algorithmName, action = { onClick(Algorithm.HAVAL_3_192) }),
                        Command(text = Algorithm.HAVAL_3_224.algorithmName, action = { onClick(Algorithm.HAVAL_3_224) }),
                        Command(text = Algorithm.HAVAL_3_256.algorithmName, action = { onClick(Algorithm.HAVAL_3_256) }),
                        Command(text = Algorithm.HAVAL_4_128.algorithmName, action = { onClick(Algorithm.HAVAL_4_128) }),
                        Command(text = Algorithm.HAVAL_4_160.algorithmName, action = { onClick(Algorithm.HAVAL_4_160) }),
                        Command(text = Algorithm.HAVAL_4_192.algorithmName, action = { onClick(Algorithm.HAVAL_4_192) }),
                        Command(text = Algorithm.HAVAL_4_224.algorithmName, action = { onClick(Algorithm.HAVAL_4_224) }),
                        Command(text = Algorithm.HAVAL_5_128.algorithmName, action = { onClick(Algorithm.HAVAL_5_128) }),
                        Command(text = Algorithm.HAVAL_5_160.algorithmName, action = { onClick(Algorithm.HAVAL_5_160) }),
                        Command(text = Algorithm.HAVAL_5_192.algorithmName, action = { onClick(Algorithm.HAVAL_5_192) }),
                        Command(text = Algorithm.HAVAL_5_224.algorithmName, action = { onClick(Algorithm.HAVAL_5_224) }),
                        Command(text = Algorithm.HAVAL_5_256.algorithmName, action = { onClick(Algorithm.HAVAL_5_256) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.JH224.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.JH224.algorithmName, action = { onClick(Algorithm.JH224) }),
                        Command(text = Algorithm.JH256.algorithmName, action = { onClick(Algorithm.JH256) }),
                        Command(text = Algorithm.JH384.algorithmName, action = { onClick(Algorithm.JH384) }),
                        Command(text = Algorithm.JH512.algorithmName, action = { onClick(Algorithm.JH512) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.Keccak224.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.Keccak224.algorithmName, action = { onClick(Algorithm.Keccak224) }),
                        Command(text = Algorithm.Keccak256.algorithmName, action = { onClick(Algorithm.Keccak256) }),
                        Command(text = Algorithm.Keccak288.algorithmName, action = { onClick(Algorithm.Keccak288) }),
                        Command(text = Algorithm.Keccak384.algorithmName, action = { onClick(Algorithm.Keccak384) }),
                        Command(text = Algorithm.Keccak512.algorithmName, action = { onClick(Algorithm.Keccak512) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.Kupyna_256.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.Kupyna_256.algorithmName, action = { onClick(Algorithm.Kupyna_256) }),
                        Command(text = Algorithm.Kupyna_384.algorithmName, action = { onClick(Algorithm.Kupyna_384) }),
                        Command(text = Algorithm.Kupyna_512.algorithmName, action = { onClick(Algorithm.Kupyna_512) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.Luffa224.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.Luffa224.algorithmName, action = { onClick(Algorithm.Luffa224) }),
                        Command(text = Algorithm.Luffa256.algorithmName, action = { onClick(Algorithm.Luffa256) }),
                        Command(text = Algorithm.Luffa384.algorithmName, action = { onClick(Algorithm.Luffa384) }),
                        Command(text = Algorithm.Luffa512.algorithmName, action = { onClick(Algorithm.Luffa512) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.MD2.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.MD2.algorithmName, action = { onClick(Algorithm.MD2) }),
                        Command(text = Algorithm.MD4.algorithmName, action = { onClick(Algorithm.MD4) }),
                        Command(text = Algorithm.MD5.algorithmName, action = { onClick(Algorithm.MD5) })
                    )
                )
            )
        ),
        Command(text = Algorithm.PANAMA.algorithmName),
        Command(
            text = Algorithm.RadioGatun32.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.RadioGatun32.algorithmName, action = { onClick(Algorithm.RadioGatun32) }),
                        Command(text = Algorithm.RadioGatun64.algorithmName, action = { onClick(Algorithm.RadioGatun64) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.RipeMD.algorithmName,
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.RipeMD.algorithmName, action = { onClick(Algorithm.RipeMD) }),
                        Command(text = Algorithm.RipeMD128.algorithmName, action = { onClick(Algorithm.RipeMD128) }),
                        Command(text = Algorithm.RipeMD160.algorithmName, action = { onClick(Algorithm.RipeMD160) }),
                        Command(text = Algorithm.RipeMD256.algorithmName, action = { onClick(Algorithm.RipeMD256) }),
                        Command(text = Algorithm.RipeMD320.algorithmName, action = { onClick(Algorithm.RipeMD320) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.SHA_0.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.SHA_0.algorithmName, action = { onClick(Algorithm.SHA_0) }),
                        Command(text = Algorithm.SHA_1.algorithmName, action = { onClick(Algorithm.SHA_1) }),
                        Command(text = Algorithm.SHA_224.algorithmName, action = { onClick(Algorithm.SHA_224) }),
                        Command(text = Algorithm.SHA_384.algorithmName, action = { onClick(Algorithm.SHA_384) }),
                        Command(text = Algorithm.SHA_512.algorithmName, action = { onClick(Algorithm.SHA_512) }),
                        Command(text = Algorithm.SHA_512_224.algorithmName, action = { onClick(Algorithm.SHA_512_224) }),
                        Command(text = Algorithm.SHA_512_256.algorithmName, action = { onClick(Algorithm.SHA_512_256) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.SHA3_224.algorithmName.dropLast(4),
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.SHA3_224.algorithmName, action = { onClick(Algorithm.SHA3_224) }),
                        Command(text = Algorithm.SHA3_256.algorithmName, action = { onClick(Algorithm.SHA3_256) }),
                        Command(text = Algorithm.SHA3_384.algorithmName, action = { onClick(Algorithm.SHA3_384) }),
                        Command(text = Algorithm.SHA3_512.algorithmName, action = { onClick(Algorithm.SHA3_512) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.Shabal192.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.Shabal192.algorithmName, action = { onClick(Algorithm.Shabal192) }),
                        Command(text = Algorithm.Shabal224.algorithmName, action = { onClick(Algorithm.Shabal224) }),
                        Command(text = Algorithm.Shabal256.algorithmName, action = { onClick(Algorithm.Shabal256) }),
                        Command(text = Algorithm.Shabal384.algorithmName, action = { onClick(Algorithm.Shabal384) }),
                        Command(text = Algorithm.Shabal512.algorithmName, action = { onClick(Algorithm.Shabal512) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.SHAKE128.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.SHAKE128.algorithmName, action = { onClick(Algorithm.SHAKE128) }),
                        Command(text = Algorithm.SHAKE256.algorithmName, action = { onClick(Algorithm.SHAKE256) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.SHAvite224.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.SHAvite224.algorithmName, action = { onClick(Algorithm.SHAvite224) }),
                        Command(text = Algorithm.SHAvite256.algorithmName, action = { onClick(Algorithm.SHAvite256) }),
                        Command(text = Algorithm.SHAvite384.algorithmName, action = { onClick(Algorithm.SHAvite384) }),
                        Command(text = Algorithm.SHAvite512.algorithmName, action = { onClick(Algorithm.SHAvite512) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.SIMD224.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.SIMD224.algorithmName, action = { onClick(Algorithm.SIMD224) }),
                        Command(text = Algorithm.SIMD256.algorithmName, action = { onClick(Algorithm.SIMD256) }),
                        Command(text = Algorithm.SIMD384.algorithmName, action = { onClick(Algorithm.SIMD384) }),
                        Command(text = Algorithm.SIMD512.algorithmName, action = { onClick(Algorithm.SIMD512) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.Skein256_128.algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.Skein256_128.algorithmName, action = { onClick(Algorithm.Skein256_128) }),
                        Command(text = Algorithm.Skein256_160.algorithmName, action = { onClick(Algorithm.Skein256_160) }),
                        Command(text = Algorithm.Skein256_224.algorithmName, action = { onClick(Algorithm.Skein256_224) }),
                        Command(text = Algorithm.Skein256_256.algorithmName, action = { onClick(Algorithm.Skein256_256) }),
                        Command(text = Algorithm.Skein512_128.algorithmName, action = { onClick(Algorithm.Skein512_128) }),
                        Command(text = Algorithm.Skein512_160.algorithmName, action = { onClick(Algorithm.Skein512_160) }),
                        Command(text = Algorithm.Skein512_224.algorithmName, action = { onClick(Algorithm.Skein512_224) }),
                        Command(text = Algorithm.Skein512_256.algorithmName, action = { onClick(Algorithm.Skein512_256) }),
                        Command(text = Algorithm.Skein512_384.algorithmName, action = { onClick(Algorithm.Skein512_384) }),
                        Command(text = Algorithm.Skein512_512.algorithmName, action = { onClick(Algorithm.Skein512_512) }),
                        Command(text = Algorithm.Skein1024_384.algorithmName, action = { onClick(Algorithm.Skein1024_384) }),
                        Command(text = Algorithm.Skein1024_512.algorithmName, action = { onClick(Algorithm.Skein1024_512) }),
                        Command(text = Algorithm.Skein1024_1024.algorithmName, action = { onClick(Algorithm.Skein1024_1024) })
                    )
                )
            )
        ),
        Command(text = Algorithm.SM3.algorithmName, action = { onClick(Algorithm.SM3) }),
        Command(text = Algorithm.Tiger.algorithmName, action = { onClick(Algorithm.Tiger) }),
        Command(text = Algorithm.Tiger2.algorithmName, action = { onClick(Algorithm.Tiger2) }),
        Command(
            text = Algorithm.Whirlpool.algorithmName,
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.Whirlpool.algorithmName, action = { onClick(Algorithm.Whirlpool) }),
                        Command(text = Algorithm.Whirlpool0.algorithmName, action = { onClick(Algorithm.Whirlpool0) }),
                        Command(text = Algorithm.WhirlpoolT.algorithmName, action = { onClick(Algorithm.WhirlpoolT) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.XXHash32().algorithmName.filter { it.isLetter() },
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.XXHash32().algorithmName, action = { onClick(Algorithm.XXHash32()) }),
                        Command(text = Algorithm.XXHash64().algorithmName, action = { onClick(Algorithm.XXHash64()) })
                    )
                )
            )
        ),
        Command(
            text = Algorithm.XXH3_64().algorithmName.dropLast(3),
            secondaryContentModel = CommandMenuContentModel(
                CommandGroup(
                    commands = listOf(
                        Command(text = Algorithm.XXH3_64().algorithmName, action = { onClick(Algorithm.XXH3_64()) }),
                        Command(text = Algorithm.XXH3_128().algorithmName, action = { onClick(Algorithm.XXH3_128()) })
                    )
                )
            )
        )
    )
}