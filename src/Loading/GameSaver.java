package Loading;

import Saving.GameProgress;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GameSaver {

    public static void openZip(String zipFilePath, String destDir) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                File newFile = new File(destDir, zipEntry.getName());
                // Создаем все необходимые родительские директории
                new File(newFile.getParent()).mkdirs();

                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }
                zis.closeEntry();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при распаковке архива: " + e.getMessage());
        }
    }

    public static GameProgress openProgress(String progressFilePath) {
        GameProgress gameProgress = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(progressFilePath))) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return gameProgress;
    }

    public static void main(String[] args) {
        String zipFilePath = "C:\\Games\\savegames\\archive.zip";
        String destDir = "C:\\Games\\savegames";
        openZip(zipFilePath, destDir);

        String progressFilePath = "C:\\Games\\savegames\\save2.dat";
        GameProgress gameProgress = openProgress(progressFilePath);

        if (gameProgress != null) {
            System.out.println(gameProgress);
        }
    }
}

