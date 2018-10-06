# Krynapsis

This library serves to encrypt and decrypt text in 2 lines using the Gauss-Jacques algorithm which is one of the safest algorithms.

To learn more about the Gauss-Jacques algorithm read [this article](https://www.uaq.mx/investigacion/revista_ciencia@uaq/ArchivosPDF/v11-n1/art14_numerada-VF.pdf)
 
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

2. Generate the matrix automatically and store it in the device.
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
[OverCode Solutions](https://overcode.mx) and 
[Fausto Abraham Jacques García](mailto:jacques@uaq.edu.mx)

