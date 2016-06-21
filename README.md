# Java Huffman Encoding
 ----------------------------------
   
 ## Summary:
 ----------
 Java code that encode/decode message based on frequency table provided 
 and print the result on screen and in file provided.
 
 
 ## Usage:
 --------
 The cmd in [] are optionals. 
 ```bash
 >>> java Encoding <frequency_table_path> <file_output_path> [<action>(encode/decode) <path to message>]
```
 
 ## Example provided:
 -------------------
 Input: 
  - print HuffmanEncoding Tree and encoding table using short_freq_table.txt as frequency table
  ```bash
   >>> java Encoding input/short_freq_table.txt output/short_freq_table.txt
  ```
  - print HuffmanEncoding Tree and encoding table, encode input/custom_msg_test.txt
  ```bash
  >>> java Encoding input/freq_table.txt output/decoded_custom_msg_test.txt encode input/custom_msg_test.txt
   ```
  
  - print HuffmanEncoding Tree and encoding table, decode input/encoded_partial_adele_lyrics.txt
  ```bash
  >>> java Encoding input/freq_table.txt output/partial_adele_lyrics.txt decode input/encoded_partial_adele_lyrics.txt
  ```
  
 ## Input Format:
 ---------------
  - Frequency table: <char> - <frequency>
  	 	A - 1
  	 	B - 90
  	 	C - 45
  - Message to decode/encode: binary string  / string
  
  ## Limitation:
  -------------
  Currently only encrypts Letters, and ignore Digits/non word character.
  
 
