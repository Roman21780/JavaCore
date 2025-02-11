package Saving;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameSaver {
    public static void main(String[] args) {
        GameProgress progress1 = new GameProgress(100, 3, 1, 150.0);
        GameProgress progress2 = new GameProgress(80, 2, 2, 300.0);
        GameProgress progress3 = new GameProgress(60, 1, 3, 450.0);

        String savePath = "C:\\Games\\savegames\\";
        saveGame(savePath + "save1.dat", progress1);
        saveGame(savePath + "save2.dat", progress2);
        saveGame(savePath + "save3.dat", progress3);

        zipFiles("C:\\Games\\savegames\\archive.zip", List.of(
                savePath + "save1.dat",
                savePath + "save2.dat",
                savePath + "save3.dat"
        ));

        deleteFiles(savePath);
    }

    public static void saveGame(String path, GameProgress progress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
            System.out.println("Сохранение игры " + progress);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении игры: " + e.getMessage());
        }
    }

    public static void zipFiles(String zipPath, List<String> files) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (String filePath : files) {
                File file = new File(filePath);
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) >= 0) {
                        zos.write(buffer, 0, length);
                    }

                    zos.closeEntry();
                    System.out.println("Файл " + file.getName() + " добавлен в архив.");
                } catch (IOException e) {
                    System.out.println("Ошибка при добавлении файла в архив: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании архива: " + e.getMessage());
        }
    }

    public static void deleteFiles(String dirPath) {
        File dir = new File(dirPath);
        if (dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.getName().endsWith(".dat")) {
                    if (file.delete()) {
                        System.out.println("Файл " + file.getName() + " удалён.");
                    } else {
                        System.out.println("Не удалось удалить файл " + file.getName() + ".");
                    }
                }
            }
        } else {
            System.out.println("Указанный каталог не существует или не является папкой.");
        }
    }


}













