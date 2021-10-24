package kata.diamond;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Diamond {
    private static final char LETTER_A = 'A';
    private final char letter;
    private final List<String> allLines = new ArrayList<>();
    public Diamond(Character letter) throws Exception {
        validateCharacter(letter);
        this.letter = letter.toString().toUpperCase().charAt(0);
    }

    public void printLines() {
        System.out.print(String.join("\n", this.createLines()));
    }

    public List<String> createLines() {
        allLines.clear();
        createTop();
        createMiddle();
        createBottom();
        return Collections.unmodifiableList(this.allLines);
    }

    private void createTop() {
        for (char character = LETTER_A; character < this.letter; character++) {
            createLine(character);
        }
    }

    private void createMiddle() {
        createLine(this.letter);
    }

    private void createBottom() {
        for (char character = (char) (this.letter - 1); character >= LETTER_A; character--) {
            createLine(character);
        }
    }

    private void createLine(char letterToAdd) {
        final StringBuilder line = new StringBuilder();
        createLeftHandSideOfLine(letterToAdd, line);
        line.append(letterToAdd == LETTER_A ? LETTER_A: " ");
        createRightHandSideOfLine(letterToAdd, line);
        this.allLines.add(line.toString());
    }

    private void createRightHandSideOfLine(char letterToAdd, StringBuilder line) {
        // Create left hand side of the line excluding letter A space, hence LETTER_A + 1
        for (char character = LETTER_A + 1; character <= letterToAdd; character++) {
            if (letterToAdd == character) {
                line.append(letterToAdd);
            } else {
                line.append(" ");
            }
        }
    }

    private void createLeftHandSideOfLine(char letterToAdd, StringBuilder line) {
        // Create left hand side of the line excluding letter A space
        for (char character = this.letter; character > LETTER_A; character--) {
            if (letterToAdd == character) {
                line.append(letterToAdd);
            } else {
                line.append(" ");
            }
        }
    }

    private void validateCharacter(Character letter) throws Exception {
        if (letter == null) {
            throw new IllegalArgumentException("Null character");
        }
        final Pattern pattern = Pattern.compile("[A-Za-z]");
        final Matcher matcher = pattern.matcher(letter.toString());
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid character: " + letter);
        }
    }
}
