import javax.swing.*;
import java.io.*;

public class Main {

    //path del file chooser
    public static final String PATH = System.getProperty("user.home") + "/Desktop";


    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(PATH));
        int result = fileChooser.showOpenDialog(new JFrame());

        if (result == JFileChooser.APPROVE_OPTION) {

            String htmlDocument;

            //BufferedReader br = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            //new FileInputStream(fileChooser.getSelectedFile()), "Cp1252"));
                            new FileInputStream(fileChooser.getSelectedFile()), "UTF8"));

            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }

                htmlDocument = sb.toString();


            } finally {
                br.close();
            }

            htmlDocument = htmlDocument.replaceAll("(?s)<span.+?>(?-s)", "")
                    .replace("</span>", "")
                    .replaceAll("(?s)<p(\\s[^>]*)?>", "<p>")
                    .replaceAll("(?s)<img.+?>","")
                    .replaceAll("(?s)class=.+?>",">")
                    .replaceAll("(?s)class=.+?","")
                    .replaceAll("(?s)style=.+?>",">")
                    .replaceAll("(?s)style=.+? ","")
                    .replace(" >", ">")
                    .replace("Ð", "-")
                    .replace("á", "-")
                    .replace("Ó", "\"")
                    .replace("Ò", "\"")
                    .replace("Û.", "&euro;")
                    .replace("Û", "&euro;")
                    .replace("Û.", "&euro;")
                    .replace("Õ", "'")
                    .replace("¼", "&deg;")
                    .replace("¡", "&deg;")
                    .replace("ˆ", "&agrave;")
                    .replace("Ž", "&egrave;")
                    .replace("\u008F", "&egrave;")
                    .replace("˜", "&ograve;")
                    .replace("\u009D", "&ugrave;")

                    .replace("\t", "")
                    .replace("\n     ", " ")
                    .replace("\r\n", " ")
                    .replace("\n", " ")
                    /*
                    .replace("</h1>", "\n</h1>")
                    .replace("</h2>", "\n</h2>")
                    .replace("</h3>", "\n</h3>")
                    .replace("</h4>", "\n</h4>")
                    .replace("</div>", "\n</div>")
                    */
                    .replace("<p>", "\n<p>")
                    .replace("<table", "\n<table")
                    .replace("</table", "\n</table")
                    .replace("<tr", "\n<tr")
                    .replace("</tr", "\n</tr")
                    .replace("<td", "\n<td")
                    .replace("</td", "\n</td")
                    .replaceAll("<td width=.*>", "<td>")
                    .replace("<table>", "<table class=\"table\">")
                    .replace("<table", "<div class=\"table-responsive\"><table")
                    .replace("</table>","</table></div>")
                    .replace("<h1>", "\n<h1>")
                    .replace("<h2>", "\n<h2>")
                    .replace("<h3>", "\n<h3>")
                    .replace("<h4>", "\n<h4>")
                    .replace("<div>", "\n<div>")
                    .replaceAll("<p>( |\t|\n|&nbsp;|<i>|<b>|</i>|</b>)*</p>","<br>")
                    .replaceAll("<h1>( |\n|&nbsp;|<i>|<b>|</i>|</b>)*</h1>","<br>")
                    .replaceAll("<h2>( |\n|&nbsp;|<i>|<b>|</i>|</b>)*</h2>","<br>")
                    .replaceAll("<h3>( |\n|&nbsp;|<i>|<b>|</i>|</b>)*</h3>","<br>")
                    .replace("&nbsp;", "")
                    .replace(" ", " ")
                    .replace("> ", ">")
                    .replace("> ", ">")
                    .replace("    ", " ")
                    .replace("   ", " ")
                    .replace("  ", " ")
                    .replace("  ", " ")
            ;


            //System.out.println(htmlDocument);
            PrintWriter writer = new PrintWriter("output.html", "UTF8");
            writer.print(htmlDocument);
            writer.close();

            System.out.println("Done!");

            char[] chars = new char[50];



        }

        System.exit(0);

    }
}
