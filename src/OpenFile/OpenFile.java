package OpenFile;

import fileManager.FileManager;
import java.awt.Desktop;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class OpenFile {

    private static String folder = "\\\\heimerdinger\\docs\\Informatica\\Programas\\Moresco\\02 - Arquivos de Programas\\";

    public static void main(String[] args) {
        try {
            if (args != null && args.length > 0) {
                List<String> listArgs = new ArrayList<>(Arrays.asList(args));
                String program = listArgs.get(0);
                //Remove o primeiro
                listArgs.remove(0);

                //text file, should be opening in default text editor
                File file = new File(folder + program + ".jar");

                //Se o arquivo existir
                if (file.exists()) {
                    Calendar cal = Calendar.getInstance();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                    String text = "";
                    String date = df.format(cal.getTime());
                    String user = System.getProperty("user.name");

                    File usages = new File(folder + "programas_usados.csv");
                    if (usages.exists()) {
                        text = FileManager.getText(usages);
                    }

                    text = date + ";" + user + ";" + program + "\r\n" + text;
                    if (FileManager.save(usages, text)) {
                        Process proc = Runtime.getRuntime().exec("java -jar \"" + file.getAbsolutePath() + "\"" + (listArgs.size() == 0 ? "" : " " + String.join(" ", listArgs)));
                    }

                } else {

                    System.out.println("Arquivo não existe");
                }
            } else {
                System.out.println("Sem argumentos");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

}
