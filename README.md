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

HashHash is a Multiplatform Graphical User Interface for hashing files, written in JetBrains Compose for Desktop. It was created with the purpose of providing a clean UI that can hash files whilst remaining multiplatform. It supports Windows, Linux and MacOS.


![HashHashd demo gif](https://user-images.githubusercontent.com/74878137/165340395-6378e52f-9ab3-4f35-bc16-6f6bbc9953df.gif)

## Supporting algorithms

HashHash uses functionality from [crypto](https://github.com/appmattus/crypto) in order to implement each individual hash. The full list of available hashes implemented by crypto can be read [here](https://github.com/appmattus/crypto/tree/main/cryptohash).

## Built With

* [Compose for Desktop](https://github.com/JetBrains/compose-jb)
* [Kotlin](https://kotlinlang.org/)
* [Aurora](https://github.com/kirill-grouchnikov/aurora)
* [crypto](https://github.com/appmattus/crypto)
* [LWJGL](https://github.com/LWJGL/lwjgl3) - Java bindings to [Native File Dialog](https://github.com/mlabbe/nativefiledialog)

## Installation

You can get the latest copy of HashHash from GitHub releases, or via WinGet

<pre>
winget install RussellBanks.HashHash
</pre>

## Usage

Once HashHash has been ran, you can select your hashing algorithm of choice and then input a file from your operating system's file manager. Then, you can begin hashing the file by pressing the calculate button.

## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

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

## Attribution

- [Application Icon](https://www.flaticon.com/free-icon/hash_390926?term=hash&page=1&position=40&page=1&position=40&related_id=390926) - Freepik via Flaticon
- [File types icon pack](https://www.flaticon.com/packs/file-types) - Smashicons via Flaticon
- [Text Editor icon pack](https://www.flaticon.com/packs/text-editor-42) - Freepik via Flaticon
- [Book Store icon pack](https://www.flaticon.com/packs/book-store-7) - Freepik via Flaticon

## License

[![GNU GPLv3 Logo](https://www.gnu.org/graphics/gplv3-127x51.png)](http://www.gnu.org/licenses/gpl-3.0.en.html)

HashHash is Free Software: You can use, study share and improve it at your will. Specifically you can redistribute and/or modify it under the terms of the [GNU General Public License](http://www.gnu.org/licenses/gpl-3.0.en.html) as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
