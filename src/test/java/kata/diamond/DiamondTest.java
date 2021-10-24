package kata.diamond;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DiamondTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    private static final String B_OUTPUT = " A\n" +
                                           "B B\n" +
                                           " A";

    private static final String C_OUTPUT =  "  A\n" +
                                            " B B\n" +
                                            "C   C\n" +
                                            " B B\n" +
                                            "  A";
    private static final String Z_OUTPUT = "                         A\n" +
                                "                        B B\n" +
                                "                       C   C\n" +
                                "                      D     D\n" +
                                "                     E       E\n" +
                                "                    F         F\n" +
                                "                   G           G\n" +
                                "                  H             H\n" +
                                "                 I               I\n" +
                                "                J                 J\n" +
                                "               K                   K\n" +
                                "              L                     L\n" +
                                "             M                       M\n" +
                                "            N                         N\n" +
                                "           O                           O\n" +
                                "          P                             P\n" +
                                "         Q                               Q\n" +
                                "        R                                 R\n" +
                                "       S                                   S\n" +
                                "      T                                     T\n" +
                                "     U                                       U\n" +
                                "    V                                         V\n" +
                                "   W                                           W\n" +
                                "  X                                             X\n" +
                                " Y                                               Y\n" +
                                "Z                                                 Z\n" +
                                " Y                                               Y\n" +
                                "  X                                             X\n" +
                                "   W                                           W\n" +
                                "    V                                         V\n" +
                                "     U                                       U\n" +
                                "      T                                     T\n" +
                                "       S                                   S\n" +
                                "        R                                 R\n" +
                                "         Q                               Q\n" +
                                "          P                             P\n" +
                                "           O                           O\n" +
                                "            N                         N\n" +
                                "             M                       M\n" +
                                "              L                     L\n" +
                                "               K                   K\n" +
                                "                J                 J\n" +
                                "                 I               I\n" +
                                "                  H             H\n" +
                                "                   G           G\n" +
                                "                    F         F\n" +
                                "                     E       E\n" +
                                "                      D     D\n" +
                                "                       C   C\n" +
                                "                        B B\n" +
                                "                         A";
    @DisplayName("should print diamond for")
    @MethodSource("testValues")
    @ParameterizedTest(name = "{0}")
    public void shouldPrintDiamond(String description, Character character, List<String> lines, String output) throws Exception {
        final Diamond diamond = new Diamond(character);
        final List<String> actualLines = diamond.createLines();
        diamond.printLines();
        assertArrayEquals(lines.toArray(new String[0]), actualLines.toArray(new String[0]));
        assertEquals(output, outputStreamCaptor.toString().stripTrailing());
    }

    @Test
    public void shouldGetExceptionWhenCharacterNotProvided() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Diamond(null);
        });
        assertEquals("Null character", exception.getMessage());
    }

    @Test
    public void shouldGetExceptionWhenCharacterIsNotLetter() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Diamond('£');
        });
        assertEquals("Invalid character: £", exception.getMessage());
    }

    static Stream<Arguments> testValues() {
        return Stream.of(
                Arguments.of("Letter A", 'A', Arrays.asList("A"), "A"),
                Arguments.of("Letter a", 'a', Arrays.asList("A"), "A"),
                Arguments.of("Letter B", 'B', Arrays.asList(" A", "B B", " A"), B_OUTPUT),
                Arguments.of("Letter c", 'C', Arrays.asList("  A", " B B", "C   C", " B B", "  A"), C_OUTPUT),
                Arguments.of("Letter Z", 'Z', Arrays.asList(
                        "                         A",
                        "                        B B",
                        "                       C   C",
                        "                      D     D",
                        "                     E       E",
                        "                    F         F",
                        "                   G           G",
                        "                  H             H",
                        "                 I               I",
                        "                J                 J",
                        "               K                   K",
                        "              L                     L",
                        "             M                       M",
                        "            N                         N",
                        "           O                           O",
                        "          P                             P",
                        "         Q                               Q",
                        "        R                                 R",
                        "       S                                   S",
                        "      T                                     T",
                        "     U                                       U",
                        "    V                                         V",
                        "   W                                           W",
                        "  X                                             X",
                        " Y                                               Y",
                        "Z                                                 Z",
                        " Y                                               Y",
                        "  X                                             X",
                        "   W                                           W",
                        "    V                                         V",
                        "     U                                       U",
                        "      T                                     T",
                        "       S                                   S",
                        "        R                                 R",
                        "         Q                               Q",
                        "          P                             P",
                        "           O                           O",
                        "            N                         N",
                        "             M                       M",
                        "              L                     L",
                        "               K                   K",
                        "                J                 J",
                        "                 I               I",
                        "                  H             H",
                        "                   G           G",
                        "                    F         F",
                        "                     E       E",
                        "                      D     D",
                        "                       C   C",
                        "                        B B",
                        "                         A"),
                        Z_OUTPUT)
        );
    }
}
