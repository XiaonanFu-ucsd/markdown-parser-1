import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MarkdownParseTest {

    @Test
    public void testFile1() throws IOException {
        String contents = Files.readString(Path.of(MarkdownParseTest.class.getResource("").getPath().replace("%20",
                " "), "test-file.md"));
        List<String> expect = List.of("https://something.com", "some-thing.html");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testFile2() throws IOException {
        // String contents= Files.readString(Path.of("./test-file2.md"));
        String contents = Files.readString(Path.of(MarkdownParseTest.class.getResource("").getPath().replace("%20",
                " "), "test-file2.md"));
        List<String> expect = List.of("https://google.com", "some-thing.html");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testLinkAtBeginning() {
        String contents = "[link title](a.com)";
        List<String> expect = List.of("a.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testSpaceInURL() {
        String contents = "[title](space in-url.com)";
        List<String> expect = List.of();
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testSpaceAfterParen() {
        String contents = "[title]( space-in-url.com)";
        List<String> expect = List.of("space-in-url.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testSpaceBeforeParen() {
        String contents = "[title] (should-not-count.com)";
        List<String> expect = List.of();
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    //@Test
    public void testNestedParens() throws IOException {
        //String contents = Files.readString(Path.of("test-parens-inside-link.md"));

        String contents = Files.readString(Path.of(MarkdownParseTest.class.getResource("").getPath().replace("%20",
                " "), "test-parens-inside-link.md"));
        List<String> expect = List.of("something.com()", "something.com((()))",
                "something.com", "boring.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testMissingCloseParen() throws IOException {
        String contents = Files.readString(Path.of(MarkdownParseTest.class.getResource("").getPath().replace("%20",
                " "), "test-missing-paren-plus-test-file2.md"));
        //String contents = Files.readString(Path.of("test-missing-paren-plus-test-file2.md"));
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

}