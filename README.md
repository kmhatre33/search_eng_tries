# search_eng_tries
Basic Search Engine using pattern matching(tries). 

Introduction :
It is a simple application that can be used to search within text or phrase on the URLs found. A compressed trie structure is used to index the occurrence of text or phrase.
Programming Language : JAVA
Libraries used : JAVA Standard Library

Project Specifics: -
Following is a summary of the requirements from the Requirements specification.
•	Text Search: Search for a given keyword and return the set of URLs containing the keyword. Rank the query results based on how frequently the keyword has appeared in the URLs.
•	Case-insensitivity: Keyword searches should be case insensitive.
•	Pharse matching: Pharses that are enclosed in quotes should match exactly.
•	Stemming: An asterisk (*) at the end of a keyword should match all endings of the word.
There are three files
1.	Main- This has the main class function and is used to call the methods and classes from the two files.
2.	Trie- This is a compressed trie structure which is used store keyword and its index which references to an array which stores a list of URLs in which the keyword is matched.
3.	Output – The output file is used to display the output in a uniform way showing the URLs in the manner such that the page with highest number of keyword hits is displayed first and so on.
