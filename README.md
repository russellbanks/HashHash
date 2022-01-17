<br/>
<p align="center">
  <h3 align="center">HashHash</h3>

  <p align="center">
    A Multiplatform GUI for Hashing, written in Compose for Desktop
    <br/>
    <br/>
    <a href="https://github.com/BanDev/HashHash/issues">Report Bug</a>
    .
    <a href="https://github.com/BanDev/HashHash/issues">Request Feature</a>
  </p>
</p>


![Contributors](https://img.shields.io/github/contributors/BanDev/HashHash?color=dark-green) ![Issues](https://img.shields.io/github/issues/BanDev/HashHash) ![License](https://img.shields.io/github/license/BanDev/HashHash)

## About The Project

HashHash is a Multiplatform Graphical User Interface for hashing files, written in JetBrains Compose for Desktop. It was created with the purpose of providing a clean UI that can hash files whilst remaining multiplatform. By nature of Jetpack Compose for Desktop, it supports Windows, Linux and MacOS.

## Supporting algorithms

| Name |
| --- |
| [MD2](https://en.wikipedia.org/wiki/MD2_(hash_function)) |
| [MD5](https://en.wikipedia.org/wiki/MD5) |
| [SHA-1](https://en.wikipedia.org/wiki/SHA-1) |
| [SHA-224](https://en.wikipedia.org/wiki/SHA-2) |
| [SHA-256](https://en.wikipedia.org/wiki/SHA-2) |
| [SHA-384](https://en.wikipedia.org/wiki/SHA-2) |
| [SHA-512](https://en.wikipedia.org/wiki/SHA-2) |
| [SHA3-224](https://en.wikipedia.org/wiki/SHA-3) |
| [SHA3-256](https://en.wikipedia.org/wiki/SHA-3) |
| [SHA3-384](https://en.wikipedia.org/wiki/SHA-3) |
| [SHA3-512](https://en.wikipedia.org/wiki/SHA-3) |

## Built With

* [Compose for Desktop](https://www.jetbrains.com/lp/compose-mpp/)
* [Kotlin](https://kotlinlang.org/)
* [Aurora](https://github.com/kirill-grouchnikov/aurora)
* [crypto](https://github.com/appmattus/crypto)

## Getting Started

HashHash is currently in its early stages. All base features are implemented, however these likely contain bugs. Despite this, this project is stable enough to be used by anyone. No installers are currently provided, however they will be once the project is sufficiently stable. Until then, you are free to compile the project yourself.

### Prerequisites

To build HashHash, you will need OpenJDK 17 as this project is targets the JVM. Personally, I use [Temurin](https://adoptium.net/) but any other would work. In addition to this, you will require the IntelliJ IDEA IDE.

1. Install a JDK of your choice.

2. Install [IntelliJ IDEA](https://www.jetbrains.com/idea/)

### Installation

1. Clone the repo

2. Run the project in IntelliJ IDEA. The [Compose-JB GitHub documentation](https://github.com/JetBrains/compose-jb/tree/master/tutorials/Getting_Started#running-the-project) provides a more detailed explanation on how to do this.

## Usage

Once HashHash has been ran, you can select your hashing algorithm of choice and then input a file from your operating system's file manager. The file will begin hashing straight away and output the result.

## Roadmap

- [x] Add all main hashes
- [ ] Improve UI
- [ ] Package installers for each targeted platform
- [ ] Add further hashes

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

## License
[![GNU GPLv3 Logo](https://www.gnu.org/graphics/gplv3-127x51.png)](http://www.gnu.org/licenses/gpl-3.0.en.html)

HashHash is Free Software: You can use, study share and improve it at your will. Specifically you can redistribute and/or modify it under the terms of the [GNU General Public License](http://www.gnu.org/licenses/gpl-3.0.en.html) as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

## Authors

* **Russell Banks** - *Comp Sci Student* - [Russell Banks](https://github.com/russellbanks/)
* **Jack Devey** - *Comp Sci Student* - [Jack Devey](https://github.com/jackdevey/)
