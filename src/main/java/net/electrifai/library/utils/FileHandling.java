package net.electrifai.library.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileHandling {

    public static String renameReport() {

        try {

            File directoryPath = new File(ThreadLocalManager.getLocalDirectoryPath().replace("\\", "/"));

            File[] listOfFiles = directoryPath.listFiles();

            for (int i = 0; i <= listOfFiles.length - 1; i++) {
                ThreadLocalManager.setCurrentFileName(ThreadLocalManager.getLocalDirectoryPath() + listOfFiles[i].getName().trim());
                ThreadLocalManager.setExpectedReportName(ThreadLocalManager.getLocalDirectoryPath() + ThreadLocalManager.getReportName() + ".html".trim());
                System.out.println(ThreadLocalManager.getCurrentFileName());
                System.out.println(ThreadLocalManager.getExpectedReportName());

                if (ThreadLocalManager.getCurrentFileName().equals(ThreadLocalManager.getExpectedReportName())) {
                    File currentFile = listOfFiles[i];

                    if (currentFile.exists()) {
                        ThreadLocalManager.setNewFileName(
                            ThreadLocalManager.getLocalDirectoryPath() + "extent/" + LocalDate.now() + "/" + "extentReport-" + ThreadLocalManager.getSuiteName() + "-"
                                + ThreadLocalManager.getOSName() + "-" + ThreadLocalManager.getBrowserName().toString() + "[" + ThreadLocalManager.getBrowserVersion() + "]-"
                                + LocalDate.now() + "-" + String.valueOf(
                                LocalTime.now()).replaceAll(":", "_") + ".html");

                        ThreadLocalManager.setNewFile(new File(ThreadLocalManager.getNewFileName()));
                        ThreadLocalManager.getNewFile().getParentFile().mkdirs();
                        ThreadLocalManager.getNewFile().createNewFile();

                        String sCurrentLine = "";
                        try {
                            BufferedReader br = new BufferedReader(new FileReader(ThreadLocalManager.getExpectedReportName()));
                            BufferedWriter bw = new BufferedWriter(new FileWriter(ThreadLocalManager.getNewFileName()));

                            while ((sCurrentLine = br.readLine()) != null) {
                                bw.write(sCurrentLine);
                                bw.newLine();
                            }
                            br.close();
                            bw.close();

                            File org = new File(ThreadLocalManager.getExpectedReportName());
                            org.delete();
                        }
                        catch (FileNotFoundException error) {
                            error.printStackTrace();
                        }
                        catch (IOException ieo) {
                            ieo.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return ThreadLocalManager.getNewFile().getAbsolutePath();
    }

    public void zipFolder(String sourceDirPath, String zipFilePath) throws IOException {
        Path p = Files.createFile(Paths.get(zipFilePath));
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
            Path pp = Paths.get(sourceDirPath);
            Files.walk(pp)
                 .filter(path -> !Files.isDirectory(path))
                 .forEach(path -> {
                     ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                     try {
                         zs.putNextEntry(zipEntry);
                         Files.copy(path, zs);
                         zs.closeEntry();
                     }
                     catch (IOException e) {
                         System.err.println(e);
                     }
                 });
        }
    }

    public static void sortOldestFilesFirst(File[] files) {
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
    }

    public static File[] sortNewestFilesFirst(String fileDirectoryPath) throws InterruptedException {

        File folder = new File(fileDirectoryPath);
        File[] files = folder.listFiles();
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        for (int i = 0; i <= files.length - 1; i++)
        {
            System.out.println(files[i].getName());
        }

        return files;
    }




    public static File getLatestModifiedFile(String fileDirectoryPath) throws InterruptedException {

        Thread.sleep(4000);
        File folder = new File(fileDirectoryPath);
        File[] files = folder.listFiles();
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

//        for (int i = 0; i <= files.length - 1; i++)
//        {
//            System.out.println(files[i].getName());
//
//        }


        return files[0];


    }


    public static String renameLatestModifiedFile(String fileDirectoryPath, String newFileName) throws InterruptedException {

        File newFile=new File(fileDirectoryPath+File.separator+newFileName);
        getLatestModifiedFile(fileDirectoryPath).renameTo(newFile);

        return newFile.getName();
    }


    public  static void deleteDirectoryFiles(String fileDirectoryPath) throws IOException
    {

        FileUtils.cleanDirectory(new File(fileDirectoryPath));

    }


    public  static boolean createDirectoryIfNotExist(String fileDirectoryPath) throws IOException
    {
        Boolean isDirectoryExist=false;

        File directory=new File(fileDirectoryPath);
        if(!directory.exists())
        {
            directory.mkdirs();

        }else
        {
            isDirectoryExist= true;
        }

        return isDirectoryExist;
    }

}
