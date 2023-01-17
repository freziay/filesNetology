import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Main {

    static StringBuilder log = new StringBuilder();
    static Date date = new Date();

    public static void main(String[] args) {

        File res = new File("res");
        File savegames = new File("savegames");
        File src = new File("src");
        File temp = new File("temp");
        File main = new File("src/main");
        File test = new File("src/test");
        File drawables = new File("res/drawables");
        File vectors = new File("res/vectors");
        File icons = new File("res/icons");
        File mainJava = new File("src/main", "Main.java");
        File utilsJava = new File("src/main", "Utils.java");
        File tempFile = new File("temp", "temp.txt");
        creatingFolder(res);
        creatingFolder(savegames);
        creatingFolder(src);
        creatingFolder(temp);
        creatingFolder(main);
        creatingFolder(test);
        creatingFolder(drawables);
        creatingFolder(vectors);
        creatingFolder(icons);
        creatingFile(mainJava);
        creatingFile(utilsJava);
        creatingFile(tempFile);
        fileWriter();

    }

    static void creatingFolder(File file) {
        if (file.mkdir()) {
            log.append("директория ");
            log.append(file.getName());
            log.append(" создана " + date + "\n");
        } else {
            log.append("директория ");
            log.append(file.getName());
            log.append(" не создана \n");
        }
    }

    static void creatingFile(File file) {
        try {
            if (file.createNewFile()) {
                log.append("файл ");
                log.append(file.getName());
                log.append(" создан " + date + "\n");
            } else {
                log.append("файл ");
                log.append(file.getName());
                log.append(" не создан \n");
            }
        } catch (IOException e) {
            log.append(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public static void fileWriter() {
        try (FileWriter fileWriter = new FileWriter("temp/temp.txt")) {
            fileWriter.write(log.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
