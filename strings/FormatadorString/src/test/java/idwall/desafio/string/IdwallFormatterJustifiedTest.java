package idwall.desafio.string;

import idwall.desafio.Exception.StringFormatException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IdwallFormatterJustifiedTest {

    private StringFormatter formatter5Characters;
    private StringFormatter formatter10Characters;
    private StringFormatter formatter40Characters;
    private StringFormatter formatter100Characters;


    @Before
    public void setUp(){
        this.formatter5Characters = new IdwallFormatterJustified(5);
        this.formatter10Characters = new IdwallFormatterJustified(10);
        this.formatter40Characters = new IdwallFormatterJustified(40);
        this.formatter100Characters = new IdwallFormatterJustified(100);
    }

    @Test
    public void test10(){
        assertEquals(OUTPUT_10, formatter10Characters.format(INPUT_TEXT));
    }

    @Test
    public void test40(){
        assertEquals(OUTPUT_40, formatter40Characters.format(INPUT_TEXT));
    }

    @Test
    public void test100(){
        assertEquals(OUTPUT_100, formatter100Characters.format(INPUT_TEXT));
    }

    @Test(expected = StringFormatException.class)
    public void testWordLongerThanLimit(){
        formatter5Characters.format(INPUT_TEXT);
    }

    private final String INPUT_TEXT = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n" +
            "\n" +
            "And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";

    private final String OUTPUT_10 = "In     the\n" +
            "beginning\n" +
            "God\n" +
            "created\n" +
            "the\n" +
            "heavens\n" +
            "and    the\n" +
            "earth. Now\n" +
            "the  earth\n" +
            "was\n" +
            "formless\n" +
            "and empty,\n" +
            "darkness\n" +
            "was   over\n" +
            "the\n" +
            "surface of\n" +
            "the  deep,\n" +
            "and    the\n" +
            "Spirit  of\n" +
            "God    was\n" +
            "hovering\n" +
            "over   the\n" +
            "waters.\n" +
            "\n" +
            "And    God\n" +
            "said, \"Let\n" +
            "there   be\n" +
            "light,\"\n" +
            "and  there\n" +
            "was light.\n" +
            "God    saw\n" +
            "that   the\n" +
            "light  was\n" +
            "good,  and\n" +
            "he\n" +
            "separated\n" +
            "the  light\n" +
            "from   the\n" +
            "darkness.\n" +
            "God called\n" +
            "the  light\n" +
            "\"day,\" and\n" +
            "the\n" +
            "darkness\n" +
            "he  called\n" +
            "\"night.\"\n" +
            "And  there\n" +
            "was\n" +
            "evening,\n" +
            "and  there\n" +
            "was\n" +
            "morning  -\n" +
            "the  first\n" +
            "day.";

    private final String OUTPUT_40 = "In the beginning God created the heavens\n" +
            "and   the   earth.  Now  the  earth  was\n" +
            "formless  and  empty,  darkness was over\n" +
            "the  surface of the deep, and the Spirit\n" +
            "of  God  was  hovering  over the waters.\n" +
            "\n" +
            "And  God said, \"Let there be light,\" and\n" +
            "there  was light. God saw that the light\n" +
            "was  good,  and  he  separated the light\n" +
            "from  the darkness. God called the light\n" +
            "\"day,\"   and   the  darkness  he  called\n" +
            "\"night.\"  And  there  was  evening,  and\n" +
            "there  was  morning  -  the  first  day.";

    private final String OUTPUT_100 = "In  the  beginning  God  created  the  heavens  and the earth. Now the earth was formless and empty,\n" +
            "darkness  was  over  the  surface  of  the deep, and the Spirit of God was hovering over the waters.\n" +
            "\n" +
            "And  God  said,  \"Let  there be light,\" and there was light. God saw that the light was good, and he\n" +
            "separated  the  light  from  the  darkness.  God  called the light \"day,\" and the darkness he called\n" +
            "\"night.\"    And    there    was    evening,    and    there   was   morning   -   the   first   day.";
}
