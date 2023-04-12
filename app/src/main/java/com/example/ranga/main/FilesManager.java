package com.example.ranga.main;

import android.content.Context;
import android.net.Uri;

import androidx.documentfile.provider.DocumentFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FilesManager
{
    public static File copyDirectoryToAppFilesDir(Context context, Uri sourceDirUri, File destinationFolder)
    {
        return copyDirectoryToAppFilesDir(context, DocumentFile.fromTreeUri(context, sourceDirUri), destinationFolder);
    }

    private static File copyDirectoryToAppFilesDir(Context context, DocumentFile sourceDocumentFile, File destinationFolder) {
        if (sourceDocumentFile != null && sourceDocumentFile.exists() && sourceDocumentFile.isDirectory()) {
            File destinationDir = new File(destinationFolder.getPath(), sourceDocumentFile.getName());
            if (!destinationDir.exists()) {
                destinationDir.mkdir();
            }

            DocumentFile[] sourceFiles = sourceDocumentFile.listFiles();
            for (DocumentFile sourceFile : sourceFiles) {
                if (sourceFile.isDirectory()) {
                    copyDirectoryToAppFilesDir(context, sourceFile, destinationDir);
                } else {
                    copy(context, sourceFile.getUri(), destinationDir, sourceFile.getName());
                }
            }
            return destinationDir;
        }
        return null;
    }

    public static void copyFileToFolder(Context context, Uri fileUri, File destinationFolder, String fileName)
    {
        copy(context, fileUri, destinationFolder, fileName);
    }

    private static void copy(Context context, Uri sourceUri, File destinationDir, String fileName)
    {
        if (!destinationDir.exists()) {
            destinationDir.mkdir();
        }
        File destinationFile = new File(destinationDir, fileName);
        try
        {
            InputStream inputStream = context.getContentResolver().openInputStream(sourceUri);
            OutputStream outputStream = new FileOutputStream(destinationFile);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
