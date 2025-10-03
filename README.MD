# Spell Checker (Java)

## What it does
This program checks a document against a dictionary and prints out the words that are **not found** in the dictionary.  
- Words are matched in a **case-insensitive** way.  
- Apostrophes (`'`) and hyphens (`-`) are allowed inside words.  
- Each misspelled word is printed **once**, in the order it first appears.  

---

## Classes
- **`Main`**  
  Entry point of the program. Handles command-line arguments, runs the spell checker, and prints results.  

- **`SpellChecker`**  
  Loads dictionary words into a `HashSet` and scans the document to find words not in the dictionary.  

- **`Normalizer`**  
  Cleans and standardizes words:  
  - Converts to lowercase.  
  - Removes leading/trailing `'` or `-`.  
  - Keeps letters and internal `'` or `-`.  

---

## Time Complexity

- Dictionary loading: **O(D)**  
  D = number of words in the dictionary.  
- Document scan: **O(N)**  
  N = number of characters in the document.  
- Lookup per token: **O(1)** average (HashSet lookup).  
- **Overall:** O(D + N)  

---

## Space Complexity

- Dictionary storage: **O(D)** (all words in a HashSet).  
- Temporary token buffer: **O(L)** (L = max word length).  
- Unknown words set: **O(U)** (U = number of unique unknown words).  
- **Overall:** O(D + U + L)  

---

## How to run

1. Compile - javac -d out Main.java
2. Run - java -cp out Main document.txt dictionary.txt
