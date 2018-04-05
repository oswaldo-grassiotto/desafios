package idwall.desafio.string;

import idwall.desafio.Exception.StringFormatException;

import java.util.ArrayList;
import java.util.List;

public class IdwallFormatterJustified extends StringFormatter {

    public IdwallFormatterJustified(){}

    public IdwallFormatterJustified(Integer limit){
        this.limit = limit;
    }

    /**
     * Should format as described in the challenge (part 2)
     *
     * @param text
     * @return
     */
    @Override
    public String format(String text){
        StringBuilder formattedTextSb = new StringBuilder();
        StringBuilder formattedLineSb = new StringBuilder();

        int lineLength = 0;

        text = text.replaceAll("\\n", " _ ");
        String[] words = text.split("\\s");

        for(String word : words){
            int wordLength = word.length();

            if(wordLength > limit)
                throw new StringFormatException(String.format("A palavra '%s' é maior que o comprimento máximo de linha (%d caracteres)", word, limit));

            int needSpace = (int) Math.signum(lineLength);

            if(lineLength + wordLength + needSpace > limit){

                String line = justifyLine(formattedLineSb.toString());
                appendLineToBuilder(line, formattedTextSb);

                formattedLineSb = new StringBuilder();
                lineLength = 0;
                needSpace = 0;
            }

            if(word.contains("_")){
                word = word.replaceAll("_", "\n");

                String line = justifyLine(formattedLineSb.toString());

                formattedTextSb.append(line);
                formattedTextSb.append(word);

                formattedLineSb = new StringBuilder();
                lineLength = 0;
                needSpace = 0;
                continue;
            } else {
                lineLength += wordLength;
            }

            if(needSpace == 1) {
                formattedLineSb.append(" ");
                lineLength++;
            }

            formattedLineSb.append(word);
        }

        String line = justifyLine(formattedLineSb.toString());
        formattedTextSb.append(line);

        return formattedTextSb.toString();
    }

    private void appendLineToBuilder(String line, StringBuilder sb){
        sb.append(line);
        sb.append("\n");
    }

    private String justifyLine(String line){

        if(line.length() == limit || line.isEmpty())
            return line;

        String[] words = line.split("\\s");

        int spacesInLine = words.length - 1;
        int spacesToAddInLine = limit - line.length();

        int spacesPerGap = 0;
        int leftOverSpaces = spacesToAddInLine;

        if(spacesInLine > 0) {
            spacesPerGap = spacesToAddInLine / spacesInLine + 1;
            leftOverSpaces = spacesToAddInLine % spacesInLine;
        }


        StringBuilder sb = new StringBuilder();
        for(String word : words){
            if(sb.length() > 0) {
                int spacesToAdd = spacesPerGap;

                if(leftOverSpaces > 0){
                    leftOverSpaces--;
                    spacesToAdd++;
                }

                int newWordWidth = spacesToAdd + word.length();
                word = String.format("%" + newWordWidth + "s", word);
            }

            sb.append(word);
        }

        return sb.toString();
    }
}
