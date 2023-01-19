# Exercise 1.1 - ✂️ Cut

## 🎯 Objectives

- **Read** input from a file using `Scanner`.
- **Write** output to a file using `PrintWriter`.
- **Parse** and **manipulate** the input to give the desired output.

## 🔨 Setup

1. Clone the repo (or download the zip) for this exercise, which you can find [here](https://github.com/JAC-CS-Programming-4-W23/E1.1-Streams).
2. Start IntelliJ, go to `File -> Open...`, and select the cloned/downloaded folder.
3. If at the top it says "Project JDK is not defined", click "Setup JDK" on the top right, and select the JDK version you have installed on your machine.

   ![Setup JDK](./images/Setup-JDK.png)

## 🔍 Context

The unix command `cut` is a useful tool to extract "columns"/"fields" from an input file, for example a tab or comma-delimited data file (i.e. `.csv`).

### Example

Input:

```txt
Bulbasaur,Charmander,Squirtle
Ivysaur,Charmeleon,Wartortle
Venusaur,Charizard,Blastoise
```

Command:

```text
> cut -d ',' -f 2 in1.txt > out1.txt
```

This is saying: Cut the 2nd column of `in1.txt` using comma as the delimiter and output the result to `out1.txt`.

Output:

```txt
Charmander
Charmeleon
Charizard
```

## 🚦 Let's Go

Implement the following static method to recreate the Unix `cut` command in Java:

```java
/**
 * Extract specified fields from the input data structured in columns by a delimiter.
 * @param inputFileName The file to read the data from, if blank then read from standard input.
 * @param outputFileName The file to write the data to, if blank then write to standard output.
 * @param format Determines the format of the output file. Comma delimited numbers and/or number
 *               range corresponding to the columns in the input data. Ex: "1,3,2" would output
 *               columns 1 and 3 and 2 in the output (comma delimited). You can specify ranges "1-3"
 *               or open-ended ranges "-5" or "2-". Columns can be repeated in the output.
 * @param delimiter The character delimiting the columns.
 * @return The number of lines read.
 * @throws IOException If there is a problem reading or writing the files.
 */
public static int cut(String inputFileName, String outputFileName, String format, char delimiter)
```

### Requirements

- Read the data token by token and not line by line, i.e.: use the scanner method `next()`.
- Assume that both the field and output delimiter is always a comma `,`, but that the input data delimiter can be any character. You can set a scanner's delimiter with the `useDelimiter()` method.
- You can assume a maximum number of columns in the input file (ex: 100).

### Advice

Break this down into phases:

1. Read the input data token by token and print everything to the output.
   - A "token" simply means the individual pieces of text we're reading. For the input that lives in `data/in1.txt`, the tokens are words.
2. Determine where each line ends and count the number of lines. Check out Java's [String](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html) methods!
3. Parse the field numbers from the `format`.
4. Output the columns in the order given in `format`.
5. Once all this is working, then focus on implementing **ranges** (see below).

### Optional

- Read from standard in and/or standard out if the filename is blank.
- Implement the open-ended range from below. For example "-3" is the same as "1,2,3".
- Implement the open-ended range from above. For example "2-" is the same as "2,3,4,5", assuming that there is a maximum of 5 columns in the input data.

### Usage

Input:

```txt
Bulbasaur,Charmander,Squirtle
Ivysaur,Charmeleon,Wartortle
Venusaur,Charizard,Blastoise
```

Command:

```java
cut("data/in1.txt", "data/out.txt", "2", ',');
```

Output:

```txt
Charmander
Charmeleon
Charizard
```

Command:

```java
cut("data/in1.txt", "data/out.txt", "2,3,1", ',');
```

Output:

```txt
Charmander, Squirtle, Bulbasaur
Charmeleon, Wartortle, Ivysaur
Charizard, Blastoise, Venusaur
```

Command:

```java
cut("data/in1.txt", "data/out.txt", "1,2,2,3", ',');
```

Output:

```txt
Bulbasaur,Charmander,Charmander,Squirtle
Ivysaur,Charmeleon,Charmeleon,Wartortle
Venusaur,Charizard,Charizard,Blastoise
```

---

[![Comic](./images/Comic.png)](https://onsizzle.com/t/pokemon-that-can-use-cut)
