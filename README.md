# Krynapsis

This library serves to encrypt and decrypt text in 2 lines using the Gauss-Jacques algorithm which is one of the safest algorithms.


## Features
- Out of the box encrypt/decrypt text
- Use one of the most secure encryption algorithms
- More features coming soon..

## Usage

### Initialize the library

There are 2 ways to start the library.
1. Pass the matrix in a string

```java
Krynapsis.init("[49,66,36][57,7,65][78,13,54]");
```

2. TODO: Generate the matrix automatically and store it in the device.
```java
Krynapsis.init(context);
```

### Encrypt and decrypt text

Now you can encrypt and decrypt the text you need.

```java
String encrypted = Krynapsis.encrypt(text);
//or
String decrypted = Krynapsis.decrypt(encrypted);
```

## License

Krynapsis Library is available under the [MIT license](LICENSE.md).

## Contact
[OverCode Solutions](https://overcode.mx)


License
--------

    Copyright 2018 OverCode Solutions, Fausto Abraham Jacques García

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
