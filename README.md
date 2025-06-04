**PhraseGenerator**

PhraseGenerator is a simple Java utility that generates secure, time-based phrases using a combination of SHA-256 hashing and Base64 encoding. It is useful for creating one-time tokens, secure identifiers, or ephemeral authentication phrases.

🚀 Features:

    🔐 Uses SHA-256 for secure hashing
    🕒 Includes current UNIX epoch time for time-sensitive outputs
    🧾 Base64 encoding for compact representation
    📦 Easy to integrate into other Java applications

🛠️ Requirements:

      Java 8 or higher
      Maven (optional, if you plan to package the project)

🔧 How It Works:

      1. Accepts a password string (e.g. "YourFECPasswordHere").
      2. Hashes it using SHA-256.
      3. Base64 encodes the hash.
      4. Gets the current UNIX epoch timestamp.
      5. Concatenates the timestamp and encoded hash.
      6. Hashes the combination again using SHA-256.
      7. Encodes the final result with Base64.
      8. Returns a string: {timestamp} {finalBase64Hash}


📘 Explanation of Key Terms 

✅ SHA-256:

      What it is: A cryptographic hash function that generates a fixed 256-bit (32-byte) hash. 
      Used for: Securely hashing passwords or data in a way that can't be reversed. 
      Why it’s used here: To transform the input password into a fixed-size, secure value. 

✅ Base64 Encoding:

      What it is: A method to convert binary data (like the hash output) into a string using only readable characters (letters, digits, etc.). 
      Used for: Making binary data safe and readable for storage or transmission. 
      Why it’s used here: To convert the hashed byte array into a printable string. 

✅ Epoch Time (UNIX Timestamp):

      What it is: The number of seconds that have passed since January 1, 1970 (UTC). 
      Used for: Time-based calculations and tracking. 
      Why it’s used here: To add a time component to the phrase, making it dynamic and ever-changing. 

✅ MessageDigest:

      What it is: A Java class in java.security used to generate hash values using algorithms like SHA-256. 
      Used for: Performing cryptographic hash operations. 

✅ digest() :

      What it does: Takes input data (as bytes) and produces the hash. 
      Example: 
		      digest.digest("hello".getBytes()); 

✅ System.currentTimeMillis() / 1000 :

      What it does: Gets the current time in milliseconds and divides it by 1000 to convert it to seconds (UNIX time). 
      Used for: Creating a time-stamped component in the phrase. 
    
✅ byte[] :

      What it is: A byte array used to store binary data (like a hash). 
      Why used here: Because SHA-256 outputs binary data which is stored in a byte array before encoding. 

✅ generatePhrase(String fecPassword):

      What it does: Accepts a password, processes it through SHA-256 and Base64, appends a timestamp, and returns a unique string (phrase). 
      Why it’s useful: To generate a time-sensitive, secure, and consistent phrase for authentication or unique identification. 

✅ PHRASE:

      What it means in context: A combination of the current timestamp and a final hashed+encoded string — useful as a secure token. 

 

🙋‍♂️ Author
  Vaibhav Gupta
  GitHub: @Vaibhav8563


