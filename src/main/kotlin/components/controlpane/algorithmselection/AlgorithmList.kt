/**

HashHash
Copyright (C) 2024 Russell Banks

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

 */

package components.controlpane.algorithmselection

import com.appmattus.crypto.Algorithm
import components.controlpane.NestedAlgorithm
import preferences.mode.Mode
import preferences.mode.ModeHandler

object AlgorithmList {

    private val simple = listOf(
        Algorithm.MD5, Algorithm.SHA_1, Algorithm.SHA3_256, Algorithm.SHA_256, Algorithm.SHA_512, Algorithm.XXHash64(),
        Algorithm.Blake2b_256, Algorithm.Blake3(), Algorithm.CRC32
    )

    private val advanced = listOf(
        Algorithm.Adler32,
        NestedAlgorithm(
            name = Algorithm.BLAKE224.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.BLAKE224, Algorithm.BLAKE256, Algorithm.BLAKE384, Algorithm.BLAKE512)
        ),
        NestedAlgorithm(
            name = Algorithm.Blake2s().algorithmName.split('-').first(),
            listOfAlgorithms = listOf(
                Algorithm.Blake2s_128, Algorithm.Blake2s_160, Algorithm.Blake2s_224, Algorithm.Blake2s_256
            )
        ),
        NestedAlgorithm(
            name = Algorithm.Blake2b().algorithmName.split('-').first(),
            listOfAlgorithms = listOf(
                Algorithm.Blake2b_160, Algorithm.Blake2b_256, Algorithm.Blake2b_384, Algorithm.Blake2b_512
            )
        ),
        Algorithm.Blake3(),
        NestedAlgorithm(
            name = Algorithm.BMW224.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.BMW224, Algorithm.BMW256, Algorithm.BMW384, Algorithm.BMW512)
        ),
        NestedAlgorithm(
            name = Algorithm.CityHash32.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(
                Algorithm.CityHash32, Algorithm.CityHash64(), Algorithm.CityHash128(), Algorithm.CityHashCrc256
            )
        ),
        NestedAlgorithm(
            name = Algorithm.CRC32.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.CRC32, Algorithm.CRC32B, Algorithm.CRC32C)
        ),
        NestedAlgorithm(
            name = Algorithm.cSHAKE128().algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.cSHAKE128(), Algorithm.cSHAKE256())
        ),
        NestedAlgorithm(
            name = Algorithm.CubeHash224.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(
                Algorithm.CubeHash224, Algorithm.CubeHash256, Algorithm.CubeHash384, Algorithm.CubeHash512
            )
        ),
        NestedAlgorithm(
            name = Algorithm.ECHO224.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.ECHO224, Algorithm.ECHO256, Algorithm.ECHO384, Algorithm.ECHO512)
        ),
        NestedAlgorithm(
            name = Algorithm.FarmHash32().algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.FarmHash32(), Algorithm.FarmHash64(), Algorithm.FarmHash128())
        ),
        NestedAlgorithm(
            name = Algorithm.Fugue224.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.Fugue224, Algorithm.Fugue256, Algorithm.Fugue384, Algorithm.Fugue512)
        ),
        NestedAlgorithm(
            name = Algorithm.GOST3411_94.algorithmName,
            listOfAlgorithms = listOf(Algorithm.GOST3411_94, Algorithm.GOST3411_2012_256, Algorithm.GOST3411_2012_512)
        ),
        NestedAlgorithm(
            name = Algorithm.Groestl224.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(
                Algorithm.Groestl224, Algorithm.Groestl256, Algorithm.Groestl384, Algorithm.Groestl512
            )
        ),
        NestedAlgorithm(
            name = Algorithm.Hamsi224.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.Hamsi224, Algorithm.Hamsi256, Algorithm.Hamsi384, Algorithm.Hamsi512)
        ),
        NestedAlgorithm(
            name = Algorithm.Haraka256_256.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.Haraka256_256, Algorithm.Haraka512_256)
        ),
        NestedAlgorithm(
            name = Algorithm.HAVAL_3_128.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(
                Algorithm.HAVAL_3_128, Algorithm.HAVAL_3_160, Algorithm.HAVAL_3_192, Algorithm.HAVAL_3_224,
                Algorithm.HAVAL_3_256, Algorithm.HAVAL_4_128, Algorithm.HAVAL_4_160, Algorithm.HAVAL_4_192,
                Algorithm.HAVAL_4_224, Algorithm.HAVAL_4_256, Algorithm.HAVAL_5_128, Algorithm.HAVAL_5_160,
                Algorithm.HAVAL_5_192, Algorithm.HAVAL_5_224, Algorithm.HAVAL_5_256
            )
        ),
        NestedAlgorithm(
            name = Algorithm.JH224.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.JH224, Algorithm.JH256, Algorithm.JH384, Algorithm.JH512)
        ),
        NestedAlgorithm(
            name = Algorithm.Keccak224.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(
                Algorithm.Keccak224, Algorithm.Keccak256, Algorithm.Keccak288, Algorithm.Keccak384, Algorithm.Keccak512
            )
        ),
        NestedAlgorithm(
            name = Algorithm.Kupyna_256.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.Kupyna_256, Algorithm.Kupyna_384, Algorithm.Kupyna_512)
        ),
        NestedAlgorithm(
            name = Algorithm.Luffa224.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.Luffa224, Algorithm.Luffa256, Algorithm.Luffa512)
        ),
        NestedAlgorithm(
            name = Algorithm.MetroHash64().algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.MetroHash64(), Algorithm.MetroHash128())
        ),
        NestedAlgorithm(
            name = Algorithm.MD2.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.MD2, Algorithm.MD4, Algorithm.MD5)
        ),
        Algorithm.MurmurHash1(),
        NestedAlgorithm(
            name = Algorithm.MurmurHash2().algorithmName,
            listOfAlgorithms = listOf(
                Algorithm.MurmurHash2(), Algorithm.MurmurHash64A(), Algorithm.MurmurHash64B(), Algorithm.MurmurHash2A()
            )
        ),
        NestedAlgorithm(
            name = Algorithm.MurmurHash3_X86_32().algorithmName.split('-').first(),
            listOfAlgorithms = listOf(
                Algorithm.MurmurHash3_X86_32(), Algorithm.MurmurHash3_X86_128(), Algorithm.MurmurHash3_X64_128()
            )
        ),
        Algorithm.PANAMA,
        NestedAlgorithm(
            name = Algorithm.RadioGatun32.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.RadioGatun32, Algorithm.RadioGatun64)
        ),
        NestedAlgorithm(
            name = Algorithm.RipeMD.algorithmName,
            listOfAlgorithms = listOf(
                Algorithm.RipeMD, Algorithm.RipeMD128, Algorithm.RipeMD160, Algorithm.RipeMD256, Algorithm.RipeMD320
            )
        ),
        NestedAlgorithm(
            name = Algorithm.SHA_0.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(
                Algorithm.SHA_0, Algorithm.SHA_1, Algorithm.SHA_224, Algorithm.SHA_256, Algorithm.SHA_384,
                Algorithm.SHA_512, Algorithm.SHA_512_224, Algorithm.SHA_512_256
            )
        ),
        NestedAlgorithm(
            name = Algorithm.SHA3_224.algorithmName.split('-').first(),
            listOfAlgorithms = listOf(Algorithm.SHA3_224, Algorithm.SHA3_256, Algorithm.SHA3_384)
        ),
        NestedAlgorithm(
            name = Algorithm.Shabal192.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(
                Algorithm.Shabal192, Algorithm.Shabal224, Algorithm.Shabal256, Algorithm.Shabal384, Algorithm.Shabal512
            )
        ),
        NestedAlgorithm(
            name = Algorithm.SHAKE128.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.SHAKE128, Algorithm.SHAKE256)
        ),
        NestedAlgorithm(
            name = Algorithm.SHAvite224.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(
                Algorithm.SHAvite224, Algorithm.SHAvite256, Algorithm.SHAvite384, Algorithm.SHAvite512
            )
        ),
        NestedAlgorithm(
            name = Algorithm.SIMD224.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.SIMD224, Algorithm.SIMD256, Algorithm.SIMD384, Algorithm.SIMD512)
        ),
        NestedAlgorithm(
            name = Algorithm.Skein256_128.algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(
                Algorithm.Skein256_128, Algorithm.Skein256_160, Algorithm.Skein256_256, Algorithm.Skein512_128,
                Algorithm.Skein512_160, Algorithm.Skein512_224, Algorithm.Skein512_256, Algorithm.Skein512_512,
                Algorithm.Skein1024_384, Algorithm.Skein1024_512, Algorithm.Skein1024_1024
            )
        ),
        Algorithm.SM3,
        Algorithm.T1ha0_32le(),
        Algorithm.T1ha1_le(),
        NestedAlgorithm(
            name = Algorithm.T1ha2_AtOnce().algorithmName.split('-').first(),
            listOfAlgorithms = listOf(
                Algorithm.T1ha2_AtOnce(), Algorithm.T1ha2_AtOnce128(), Algorithm.T1ha2_Stream(),
                Algorithm.T1ha2_Stream128()
            )
        ),
        NestedAlgorithm(
            name = Algorithm.Tiger.algorithmName,
            listOfAlgorithms = listOf(Algorithm.Tiger, Algorithm.Tiger2)
        ),
        NestedAlgorithm(
            name = Algorithm.Whirlpool.algorithmName,
            listOfAlgorithms = listOf(Algorithm.Whirlpool, Algorithm.Whirlpool0, Algorithm.WhirlpoolT)
        ),
        NestedAlgorithm(
            name = Algorithm.Wyhash().algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.Wyhash32(), Algorithm.Wyhash())
        ),
        NestedAlgorithm(
            name = Algorithm.XXHash32().algorithmName.filter(Char::isLetter),
            listOfAlgorithms = listOf(Algorithm.XXHash32(), Algorithm.XXHash64())
        ),
        NestedAlgorithm(
            name = Algorithm.XXH3_64().algorithmName.split('-').first(),
            listOfAlgorithms = listOf(Algorithm.XXH3_64(), Algorithm.XXH3_128())
        )
    )

    fun getAlgorithmList() = if (ModeHandler.selectedMode == Mode.SIMPLE) simple else advanced
}
