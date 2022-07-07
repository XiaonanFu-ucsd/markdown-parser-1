//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            if (openBracket < 0) break;
            int closeBracket = markdown.indexOf("]", openBracket);
            if (closeBracket < 0) break;
            int openParen = markdown.indexOf("(", closeBracket);
            if (closeBracket + 1 != openParen) {
                currentIndex = closeBracket + 1;
                continue;
            }
            int closeParen = markdown.indexOf(")", openParen);
            String link = markdown.substring(openParen + 1, closeParen).strip();
            if (link.indexOf(" ") > -1 || link.indexOf("\n") > -1 || link.indexOf("\t") > -1) {
                currentIndex = openParen + 1;
                continue;
            }
            toReturn.add(link);
            currentIndex = closeParen + 1;
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
