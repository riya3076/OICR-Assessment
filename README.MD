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

## Identifying Issues and How I Addressed Them

- **Handling apostrophes and hyphens:**  
  At first, words like “don’t” or “co-operate” were being split into two parts.  
  I fixed this by allowing `'` and `-` inside words but trimming them if they appear at the start or end.  
  This keeps real words intact without letting punctuation break them apart.

- **Case sensitivity:**  
  “Word” and “word” were being treated as different.  
  To fix this, I converted everything to lowercase before checking against the dictionary.

- **Extra spaces, punctuation, and invalid characters:**  
  The document could have random punctuation or multiple spaces between words.  
  I handled this by reading the file character by character and only accepting valid ASCII letters (plus `'` and `-`).

- **Duplicate misspellings:**  
  I didn’t want the same misspelled word to show up multiple times.  
  Using a `LinkedHashSet` helped keep unique words in the order they appeared.

- **Efficiency:**  
  I used a `HashSet` for dictionary lookups to make word checking fast — constant time on average.  
  This keeps the program quick even with large dictionaries.

---

## How I Would Extend This Project

If I had to turn this into a larger or more feature-rich project, here’s what I would do:

- **Add suggestions:**  
  When a word is misspelled, show the closest valid matches from the dictionary.  
  A **Trie** data structure could be used for this since it allows efficient prefix lookups and can help find possible corrections quickly.

- **Support more languages:**  
  Expand from basic ASCII to handle Unicode so it can check words with accents or in other languages.
  
- **Export results:**  
  Allow saving all misspelled words and their positions to a file (like CSV or JSON) for later review.

- **User interface or web version:**  
  Wrap the logic in a small GUI or web app where users can upload files and get instant feedback.

- **Add tests:**  
  Include unit tests for edge cases — empty files, mixed punctuation, repeated words, etc.

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

