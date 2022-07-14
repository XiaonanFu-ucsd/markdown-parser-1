# Part 1:
- md2 cannot finish runing
- md3 wrong result: "[more text here]"
- md4 Exception: 
```
Exception in thread "main" java.lang.StringIndexOutOfBoundsException: begin 0, end -1, length 27
        at java.base/java.lang.String.checkBoundsBeginEnd(String.java:3319)
        at java.base/java.lang.String.substring(String.java:1874)
        at MarkdownParse.getLinks(MarkdownParse.java:19)
        at MarkdownParse.main(MarkdownParse.java:30)
```

# Part 3:
```
JUnit version 4.13.2
.
Time: 0.01

OK (1 test)
```

## TEST CODE
```
import static org.junit.Assert.*;
import org.junit.*; // import JUnit
public class MarkdownParseTest {
@Test // tell the JUnit that this is a test case. 
public void addition() {
assertEquals(2, 1 + 1); // compare expected result and acutal result
}
```

# Part 4
One error: the MDParser wrongly regonize the (aaa aaa.html) as a link. 
Another error: there should not have a space between bracket and para

# Lab 6 makefile note:
show wha the makefile does for make test:
- if no .class files, complie .java files. 
- if changed MarkdownParse.java file, both file compiled, because Test.java depend on the MP.class
- if only change test file, only compile test file. 