<br/>
<p align="center">
  <img title="HashHash Logo" src="src/main/resources/logo.svg" alt="hash" width="80">
  <h3 align="center">HashHash</h3>
<p>

<p align="center">
    A Multiplatform GUI for Hashing, written in Compose for Desktop
    <br/>
    <br/>
    <a href="https://github.com/BanDev/HashHash/issues">Report Bug</a>
    .
    <a href="https://github.com/BanDev/HashHash/issues">Request Feature</a>
</p>

![Contributors](https://img.shields.io/github/contributors/BanDev/HashHash?color=dark-green) ![Issues](https://img.shields.io/github/issues/BanDev/HashHash) ![License](https://img.shields.io/github/license/BanDev/HashHash)

## About The Project

HashHash is a Multiplatform Graphical User Interface for hashing files, written in JetBrains Compose for Desktop. It was created with the purpose of providing a clean UI that can hash data whilst remaining multiplatform. It supports Windows, Linux and macOS.

![java_GmbKKGqWY1](https://user-images.githubusercontent.com/74878137/177767561-f3560a4c-638e-442d-95fd-cfe1ea609835.png)

## Supporting algorithms

HashHash uses functionality from [crypto](https://github.com/appmattus/crypto) in order to implement each individual hash. The full list of available hashes implemented by crypto can be read [here](https://github.com/appmattus/crypto/blob/main/cryptohash/README.md).

## Built With

* [Compose for Desktop](https://github.com/JetBrains/compose-multiplatform)
* [Kotlin](https://kotlinlang.org) - The Kotlin Programming Language
* [Aurora](https://github.com/kirill-grouchnikov/aurora) - A modern, responsive, and beautiful UI framework for Compose for Desktop
* [crypto](https://github.com/appmattus/crypto) - Provides the functionality for each hash
* [LWJGL](https://github.com/LWJGL/lwjgl3) - Java bindings to [Tiny File Dialogs](https://sourceforge.net/projects/tinyfiledialogs/)
* [Compose Window Styler](https://github.com/MayakaApps/ComposeWindowStyler) - Styles the window to be more native and modern

## Installation

You can get the latest copy of HashHash from GitHub releases, or via WinGet:

```
winget install RussellBanks.HashHash
```

## Usage

HashHash has three main screens: the file hashing screen, the text hashing screen and the compare files screen. You can select the hashing algorithm of choice and input the data you wish to hash.

1. On the file screen, you can input a file and press the calculate button.
2. On the text screen, you can input text, and it will begin hashing straight away.
3. On the compare files screen, you can input two files, and press the calculate button. HashHash will then tell you if the files match or do not match, according to the hashing algorithm.

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

* If you have suggestions for adding or removing projects, feel free to [open an issue](https://github.com/BanDev/HashHash/issues/new) to discuss it, or directly create a pull request after you edit the *README.md* file with necessary changes.
* Please make sure you check your spelling and grammar.
* Create individual PR for each suggestion.
* Please also read through the [Code Of Conduct](https://github.com/BanDev/HashHash/blob/main/CODE_OF_CONDUCT.md) before posting your first idea as well.

### Creating A Pull Request

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

[![GNU GPLv3 Logo](https://www.gnu.org/graphics/gplv3-127x51.png)](http://www.gnu.org/licenses/gpl-3.0.en.html)

HashHash is Free Software: You can use, study share and improve it at your will. Specifically you can redistribute and/or modify it under the terms of the [GNU General Public License](http://www.gnu.org/licenses/gpl-3.0.en.html) as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
