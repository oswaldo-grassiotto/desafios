package idwall.desafio.string;

import idwall.desafio.Exception.StringFormatException;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    public IdwallFormatter(){}

    public IdwallFormatter(Integer limit){
        this.limit = limit;
    }

    /**
     * Should format as described in the challenge (part 1)
     *
     * @param text
     * @return
     */
    @Override
    public String format(String text) {

        StringBuilder formattedTextSb = new StringBuilder();

        int lineLength = 0;

        text = text.replaceAll("\\n", " _ ");
        String[] words = text.split("\\s");

        for(String word : words){
            int wordLength = word.length();

            if(wordLength > limit)
                throw new StringFormatException(String.format("A palavra '%s' é maior que o comprimento máximo de linha (%d caracteres)", word, limit));

            int needSpace = (int) Math.signum(lineLength);

            if(lineLength + wordLength + needSpace > limit){
                formattedTextSb.append("\n");
                lineLength = 0;
            }

            if(lineLength > 0) {
                formattedTextSb.append(" ");
                lineLength++;
            }

            if(word.contains("_")){
                word = word.replaceAll("_", "\n");
                lineLength = 0;
            } else {
                lineLength += wordLength;
            }

            formattedTextSb.append(word);
        }

        return formattedTextSb.toString();
    }
}
