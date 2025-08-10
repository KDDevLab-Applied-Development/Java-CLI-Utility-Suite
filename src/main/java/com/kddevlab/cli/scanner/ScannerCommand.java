package com.kddevlab.cli.scanner;

import java.io.File;
import java.util.concurrent.Callable;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/**
 * Command for file scanning and analysis operations.
 */
@Command(
    name = "scan",
    description = "Scan directories for files, detect duplicates, and generate statistics",
    mixinStandardHelpOptions = true
)
public class ScannerCommand implements Callable<Integer> {

    @Parameters(index = "0", description = "Directory path to scan")
    private File directory;

    @Option(names = {"-r", "--recursive"}, description = "Scan recursively through subdirectories")
    private boolean recursive = true;

    @Option(names = {"-d", "--duplicates"}, description = "Detect duplicate files")
    private boolean detectDuplicates;

    @Option(names = {"-s", "--stats"}, description = "Generate file statistics")
    private boolean generateStats = true;

    @Option(names = {"-f", "--filter"}, description = "Filter by file extension (e.g., .txt,.java)")
    private String[] extensions;

    @Override
    public Integer call() throws Exception {
        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("Error: Directory does not exist or is not a directory: " + directory.getPath());
            return 1;
        }

        System.out.println("Scanning directory: " + directory.getAbsolutePath());
        System.out.println("Recursive: " + recursive);
        System.out.println("Detect duplicates: " + detectDuplicates);
        System.out.println("Generate statistics: " + generateStats);

        FileScanner scanner = new FileScanner();
        ScanResult result = scanner.scanDirectory(directory, recursive, detectDuplicates, generateStats, extensions);

        displayResults(result);
        return 0;
    }

    private void displayResults(ScanResult result) {
        System.out.println("\n=== Scan Results ===");
        System.out.println("Total files found: " + result.getTotalFiles());
        System.out.println("Total directories: " + result.getTotalDirectories());
        System.out.println("Total size: " + formatFileSize(result.getTotalSize()));

        if (generateStats) {
            System.out.println("\n=== File Type Statistics ===");
            result.getFileTypeStats().forEach((ext, count) -> 
                System.out.println(ext + ": " + count + " files"));
        }

        if (detectDuplicates && !result.getDuplicates().isEmpty()) {
            System.out.println("\n=== Duplicate Files ===");
            result.getDuplicates().forEach((hash, files) -> {
                System.out.println("Hash: " + hash);
                files.forEach(file -> System.out.println("  " + file.getAbsolutePath()));
                System.out.println();
            });
        }
    }

    private String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024) return String.format("%.1f MB", bytes / (1024.0 * 1024));
        return String.format("%.1f GB", bytes / (1024.0 * 1024 * 1024));
    }
}
