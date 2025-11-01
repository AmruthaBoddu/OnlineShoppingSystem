# Online Shopping System (Java)

A simple console-based Java project for managing an online shopping system.

## Features
- Register / Login users (file-based)
- Browse products (loaded from data/products.txt)
- Add to cart
- Checkout and bill summary

## How to Run
1. Make sure you have Java (JDK) installed.
2. From the project root, compile:
   ```bash
   javac src/*.java -d out
   ```
3. Run:
   ```bash
   java -cp out Main
   ```
Alternatively, import the `src` folder into any Java IDE (Eclipse, IntelliJ, VS Code) and run `Main`.

## Notes
- Data files are in the `data/` folder. You can edit `products.txt` to add or change products using the format: `id,name,price`.
- This project uses simple text files for ease of use and portability.

