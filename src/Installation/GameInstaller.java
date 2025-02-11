package Installation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.nio.file.Files.createDirectory;
import static java.nio.file.Files.createFile;

public class GameInstaller {
    public static void main(String[] args) {
        // Путь к папке Games
        String gamesPath = "C:\\Games"; // Путь к папке Games
        StringBuilder log = new StringBuilder();

        // Создаем главную папку Games
        File gamesDir = new File(gamesPath);
        if (!gamesDir.exists()) {
            gamesDir.mkdir();
            log.append("Создана папка Games\n");
        }

        // Создаем папки src, res, savegames, temp
        createDirectory(gamesDir, "src", log);
        createDirectory(gamesDir, "res", log);
        createDirectory(gamesDir, "savegames", log);
        createDirectory(gamesDir, "temp", log);

        // Внутри src создаем папки main и test
        File srcDir = new File(gamesDir, "src");
        createDirectory(srcDir, "main", log);
        createDirectory(srcDir, "test", log);

        // Внутри main создаем файлы Main.java и Utils.java
        createFile(new File(srcDir + "\\main"), "Main.java", log);
        createFile(new File(srcDir + "\\main"), "Utils.java", log);

        // Внутри res создаем папки drawables, vectors, icons
        File resDir = new File(gamesDir, "res");
        createDirectory(resDir, "drawables", log);
        createDirectory(resDir, "vectors", log);
        createDirectory(resDir, "icons", log);

        // Создаем файл temp.txt внутри temp
        createFile(new File(gamesDir + "\\temp"), "temp.txt", log);

        // Записываем лог в файл temp.txt
        try (FileWriter writer = new FileWriter(new File(gamesDir + "\\temp\\temp.txt"))) {
            writer.write(log.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDirectory(File parent, String dirName, StringBuilder log) {
        File dir = new File(parent, dirName);
        if (dir.mkdir()) {
            log.append("Создана папка ").append(dirName).append("\n");
        } else {
            log.append("Не удалось создать папку ").append(dirName).append("\n");
        }
    }

    private static void createFile(File parent, String filename, StringBuilder log) {
        try {
            File file = new File(parent, filename);
            if (file.createNewFile()) {
                log.append("Создан файл ").append(filename).append("\n");
            } else {
                log.append("Не удалось создать файл ").append(filename).append("\n");
            }
        } catch (IOException e) {
            log.append("Ошибка при создании файла ")
                    .append(filename).append(":").append(e.getMessage()).append("\n");
        }
    }
}












