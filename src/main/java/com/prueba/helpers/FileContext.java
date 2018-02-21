package com.prueba.helpers;

import java.io.*;

public class FileContext {

    /**
     *
     * [EN]
     * Save the given text to the given filename.
     *
     * [ES]
     * Guarda el texto dado en el nombre de archivo indicado.
     *
     * @param canonicalFilename String
     * @param text String
     * @throws IOException exception
     */
    public static void writeFile(String canonicalFilename, String text) throws IOException
    {
        File file = new File (canonicalFilename);
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(text);
        out.close();
    }


    /**
     *
     * [EN]
     * Read file from path.
     *
     * [ES]
     * Lee un archivo desde una ruta.
     *
     * @param canonicalFilename String
     * @return
     */
    public static String readFile(String canonicalFilename){

        String contentFile = "";
        BufferedReader br = null;
        try {

            br = new BufferedReader(new FileReader(canonicalFilename));
            String line;
            while ((line = br.readLine()) != null) { contentFile = contentFile.concat("\n").concat(line); }

        } catch (IOException e) {

            contentFile = null;

        } finally {
            try {
                if (br != null) { br.close(); }
            } catch (IOException ex) {
                contentFile = null;
            }
        }

        return contentFile;
    }

}
