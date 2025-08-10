package com.kddevlab.cli.scanner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Test class for FileScanner functionality.
 */
class FileScannerTest {

    private FileScanner fileScanner;

    @BeforeEach
    void setUp() {
        fileScanner = new FileScanner();
    }

    @Test
    void testScanEmptyDirectory(@TempDir Path tempDir) throws IOException {
        File directory = tempDir.toFile();
        
        ScanResult result = fileScanner.scanDirectory(directory, false, false, false, null);
        
        assertEquals(0, result.getTotalFiles());
        assertEquals(0, result.getTotalDirectories());
        assertEquals(0, result.getTotalSize());
    }

    @Test
    void testScanDirectoryWithFiles(@TempDir Path tempDir) throws IOException {
        // Create test files
        Path file1 = tempDir.resolve("test1.txt");
        Path file2 = tempDir.resolve("test2.java");
        Files.writeString(file1, "Hello World");
        Files.writeString(file2, "public class Test {}");
        
        File directory = tempDir.toFile();
        ScanResult result = fileScanner.scanDirectory(directory, false, false, true, null);
        
        assertEquals(2, result.getTotalFiles());
        assertTrue(result.getTotalSize() > 0);
        assertEquals(2, result.getFileTypeStats().size());
    }

    @Test
    void testScanWithExtensionFilter(@TempDir Path tempDir) throws IOException {
        // Create test files with different extensions
        Files.writeString(tempDir.resolve("test.txt"), "text file");
        Files.writeString(tempDir.resolve("test.java"), "java file");
        Files.writeString(tempDir.resolve("test.xml"), "xml file");
        
        File directory = tempDir.toFile();
        String[] extensions = {".txt", ".java"};
        ScanResult result = fileScanner.scanDirectory(directory, false, false, true, extensions);
        
        assertEquals(2, result.getTotalFiles());
        assertTrue(result.getFileTypeStats().containsKey(".txt"));
        assertTrue(result.getFileTypeStats().containsKey(".java"));
        assertFalse(result.getFileTypeStats().containsKey(".xml"));
    }

    @Test
    void testDuplicateDetection(@TempDir Path tempDir) throws IOException {
        // Create identical files
        String content = "This is identical content";
        Files.writeString(tempDir.resolve("file1.txt"), content);
        Files.writeString(tempDir.resolve("file2.txt"), content);
        Files.writeString(tempDir.resolve("file3.txt"), "Different content");
        
        File directory = tempDir.toFile();
        ScanResult result = fileScanner.scanDirectory(directory, false, true, false, null);
        
        assertEquals(3, result.getTotalFiles());
        assertEquals(1, result.getDuplicates().size()); // One group of duplicates
    }

    @Test
    void testRecursiveScanning(@TempDir Path tempDir) throws IOException {
        // Create subdirectory with files
        Path subDir = tempDir.resolve("subdir");
        Files.createDirectory(subDir);
        Files.writeString(tempDir.resolve("root.txt"), "root file");
        Files.writeString(subDir.resolve("sub.txt"), "sub file");
        
        File directory = tempDir.toFile();
        
        // Non-recursive scan
        ScanResult nonRecursive = fileScanner.scanDirectory(directory, false, false, false, null);
        assertEquals(1, nonRecursive.getTotalFiles());
        
        // Recursive scan
        ScanResult recursive = fileScanner.scanDirectory(directory, true, false, false, null);
        assertEquals(2, recursive.getTotalFiles());
        assertEquals(1, recursive.getTotalDirectories());
    }
}
