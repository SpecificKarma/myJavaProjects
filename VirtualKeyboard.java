import java.util.ArrayList;


/*
* Created by Chashchin.dev
* */

public class VirtualKeyboard {


    public static void main(String[] args) {
        locateKeyboardButtons("START", 'Z');
    }

    static void locateKeyboardButtons(String inputWord, char startSymbol) {

        inputWord = inputWord.toUpperCase();
        startSymbol = Character.toUpperCase(startSymbol);

        boolean isWork = true;
        boolean firstIsFound = false;
        int firstX = 0, firstY = 0;

        int secondX = 0, secondY = 0;

        ArrayList<String> chars = new ArrayList<String>();

        chars.add("ABCDEF");
        chars.add("GHJKLM");
        chars.add("NOPQRS");
        chars.add("TUVWXY");
        chars.add("Z");

        for (int i = 0; i < chars.size(); i++) {
            for (int n = 0; n < chars.get(i).length(); n++) {
                if (chars.get(i).charAt(n) == startSymbol) {
                    firstX = n;
                    firstY = i;
                    firstIsFound = true;
                }
            }
        }

        int w = 0;

        while (isWork) {

            for (int y = 0; y < chars.size(); y++) {

                for (int x = 0; x < chars.get(y).length(); x++) {

                    if (chars.get(y).charAt(x) == inputWord.charAt(w)) {
                        if (firstIsFound) {
                            secondX = x;
                            secondY = y;
                            firstIsFound = false;
//                            System.out.println(secondY + " " + secondX);
                        } else {
                            firstX = x;
                            firstY = y;
                            firstIsFound = true;
//                            System.out.println(firstY + " " + firstX);
                        }

                        if (firstY > secondY) {
                            int prints = firstY - secondY;

                            for (; prints > 0; prints--) {
                                System.out.print("Up,");
                            }
                        } else {
                            int prints = secondY - firstY;

                            for (; prints > 0; prints--) {
                                System.out.print("Down,");
                            }
                        }

                        if (firstX > secondX) {
                            int prints = firstX - secondX;

                            for (; prints > 0; prints--) {
                                System.out.print("Left,");
                            }
                        } else {
                            int prints = secondX - firstX;

                            for (; prints > 0; prints--) {
                                System.out.print("Right,");
                            }
                        }

                        System.out.println("Enter");

                        if (w == inputWord.length() - 1) {
                            isWork = false;
                        } else {
                            w++;
                        }
                    }
                }
            }
        }
    }
}
