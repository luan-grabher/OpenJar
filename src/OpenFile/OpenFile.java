package OpenFile;

import java.awt.Desktop;
import java.io.File;

public class OpenFile {

    public static void main(String[] args) {
        try {
            
            
            //text file, should be opening in default text editor
            File file = new File("./Test.jar");
            
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                System.out.println(file.getCanonicalPath());
                Process proc = Runtime.getRuntime().exec("java -jar \"" + file.getAbsolutePath() + "\" ");
            } else {
                System.out.println("Arquivo não existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

}
