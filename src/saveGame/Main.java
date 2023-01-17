package saveGame;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(2, 30, 4, 200.5);
        GameProgress gameProgress2 = new GameProgress(3, 25, 5, 205.5);
        GameProgress gameProgress3 = new GameProgress(4, 21, 6, 215.5);

        ArrayList<GameProgress> listGameProgress = new ArrayList<>();
        listGameProgress.add(gameProgress1);
        listGameProgress.add(gameProgress2);
        listGameProgress.add(gameProgress3);
        String zip = "D:/Games/savegames/zip1.zip";
        String file = "D:/Games/savegames/";

        ArrayList<String> savefile = new ArrayList<>();
        for (int a = 0; a <= 2; a++) {
            savefile.add("D:/Games/savegames/save" + (a + 1) + "." + "dat");
        }
        ArrayList<String> savezip = new ArrayList<>();
        for (int u = 0; u <= 2; u++) {
            savezip.add("D:/Games/savegames/zip" + (u + 1) + "." + "zip");
        }
        saveGame(listGameProgress, savefile);
        zipFiles(savezip, savefile);
        deliteFile(savefile);
        openZip(zip, file);
        System.out.println(openProgress(file, listGameProgress.get(0)));
    }

    static void saveGame(ArrayList arrayList, ArrayList arrayList1) {
        for (int i = 0; i <= 2; i++) {
            String f = (String) arrayList1.get(i);
            try (FileOutputStream fos = new FileOutputStream(f);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(arrayList.get(i) + "\n");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    static void zipFiles(ArrayList z, ArrayList f) {
        for (int i = 0; i <= 2; i++) {
            String zip = (String) z.get(i);
            String file = (String) f.get(i);
            try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zip));
                 FileInputStream fis = new FileInputStream(file)) {
                ZipEntry entry = new ZipEntry("packed_seve" + (i + 1) + "." + "dat");
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    static void deliteFile(ArrayList file) {
        for (int i = 0; i <= 2; i++) {
            File file1 = new File((String) file.get(i));
            file1.delete();
        }
    }

    static void openZip(String zip, String file) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zip))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(file + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static GameProgress openProgress(String put, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(put + "saveGames.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try (FileInputStream fis = new FileInputStream(put + "saveGames.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return gameProgress;
    }
}




















