<br/>
<p align="center">
  <img title="HashHash Logo" src="src/main/resources/logo.svg" alt="hash" width="80">
  <h3 align="center">HashHash</h3>
<p>

<p align="center">
    A Sleek, Multiplatform GUI for File Hashing
    <br/>
    <br/>
    <a href="https://github.com/russellbanks/HashHash/issues">Report Bug</a>
    .
    <a href="https://github.com/russellbanks/HashHash/issues">Request Feature</a>
</p>

![Contributors](https://img.shields.io/github/contributors/BanDev/HashHash?color=dark-green) ![Issues](https://img.shields.io/github/issues/BanDev/HashHash) ![License](https://img.shields.io/github/license/BanDev/HashHash)

## About The Project

HashHash is a multiplatform GUI for hashing files and text, built using Compose for Desktop.

Light             |
:-------------------------:
![HashHash in light mode](https://user-images.githubusercontent.com/74878137/236931570-806dca0c-f736-4a62-8a62-5a41b186ea58.png)  |
Dark             |
![HashHash in dark mode](https://user-images.githubusercontent.com/74878137/236931606-777be9f6-fc65-47da-868f-b3bc616b69ec.png)   |

## Supporting algorithms

HashHash uses [crypto](https://github.com/appmattus/crypto) for each hashing algorithm. For a complete list of the available hashing algorithms, please refer to their [ReadMe](https://github.com/appmattus/crypto/blob/main/cryptohash/README.md).

## Built With

* [Compose for Desktop](https://github.com/JetBrains/compose-multiplatform)
* [Kotlin](https://kotlinlang.org) - The Kotlin Programming Language
* [Aurora](https://github.com/kirill-grouchnikov/aurora) - A modern, responsive, and beautiful UI framework for Compose for Desktop
* [crypto](https://github.com/appmattus/crypto) - Provides the functionality for each hash
* [LWJGL](https://github.com/LWJGL/lwjgl3) - Bindings to [Tiny File Dialogs](https://sourceforge.net/projects/tinyfiledialogs/)
* [Compose Window Styler](https://github.com/MayakaApps/ComposeWindowStyler) - Styles the window to be more native and modern

## Installation

You can get the latest copy of HashHash from GitHub releases, or via WinGet:

```
winget install hashhash
```

## Usage

HashHash has three main screens: the file hashing screen, the text hashing screen and the compare files screen. You can select the hashing algorithm of choice and input the data you wish to hash.

1. On the file screen, you can input a file and press the calculate button.
2. On the text screen, you can input text, and it will begin hashing straight away.
3. On the compare files screen, you can input two files, and press the calculate button. HashHash will then tell you if the files match or do not match, according to the hashing algorithm.

## License

[![GNU GPLv3 Logo](https://www.gnu.org/graphics/gplv3-127x51.png)](http://www.gnu.org/licenses/gpl-3.0.en.html)

HashHash is Free Software: You can use, study share and improve it at your will. Specifically you can redistribute and/or modify it under the terms of the [GNU General Public License](http://www.gnu.org/licenses/gpl-3.0.en.html) as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
