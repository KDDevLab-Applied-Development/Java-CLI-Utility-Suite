package com.kddevlab.cli.scanner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 * Service class for scanning directories and analyzing files.
 */
public class FileScanner {

    /**
     * Scans a directory and returns analysis results.
     *
     * @param directory        The directory to scan
     * @param recursive        Whether to scan recursively
     * @param detectDuplicates Whether to detect duplicate files
     * @param generateStats    Whether to generate file type statistics
     * @param extensions       File extensions to filter (null for all files)
     * @return ScanResult containing the analysis results
     */
    public ScanResult scanDirectory(File directory, boolean recursive, boolean detectDuplicates, 
                                  boolean generateStats, String[] extensions) throws IOException {
        
        ScanResult result = new ScanResult();
        Collection<File> files = getFiles(directory, recursive, extensions);
        
        // Basic counts
        result.setTotalFiles(files.size());
        result.setTotalDirectories(countDirectories(directory, recursive));
        
        // Calculate total size and collect file info
        long totalSize = 0;
        Map<String, Integer> fileTypeStats = new HashMap<>();
        Map<String, List<File>> duplicates = new HashMap<>();
        
        for (File file : files) {
            if (file.isFile()) {
                totalSize += file.length();
                
                // File type statistics
                if (generateStats) {
                    String extension = getFileExtension(file);
                    fileTypeStats.merge(extension, 1, Integer::sum);
                }
                
                // Duplicate detection
                if (detectDuplicates) {
                    try {
                        String hash = calculateFileHash(file);
                        duplicates.computeIfAbsent(hash, k -> new ArrayList<>()).add(file);
                    } catch (Exception e) {
                        System.err.println("Warning: Could not calculate hash for " + file.getPath() + ": " + e.getMessage());
                    }
                }
            }
        }
        
        result.setTotalSize(totalSize);
        result.setFileTypeStats(fileTypeStats);
        
        // Only keep duplicates that actually have multiple files
        Map<String, List<File>> actualDuplicates = duplicates.entrySet().stream()
            .filter(entry -> entry.getValue().size() > 1)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        result.setDuplicates(actualDuplicates);
        
        return result;
    }
    
    private Collection<File> getFiles(File directory, boolean recursive, String[] extensions) {
        if (extensions != null && extensions.length > 0) {
            // Create wildcard patterns for extensions
            String[] patterns = Arrays.stream(extensions)
                .map(ext -> ext.startsWith(".") ? "*" + ext : "*." + ext)
                .toArray(String[]::new);
            
            WildcardFileFilter fileFilter = new WildcardFileFilter(patterns);
            return FileUtils.listFiles(directory, fileFilter, recursive ? TrueFileFilter.INSTANCE : null);
        } else {
            return FileUtils.listFiles(directory, TrueFileFilter.INSTANCE, recursive ? TrueFileFilter.INSTANCE : null);
        }
    }
    
    private int countDirectories(File directory, boolean recursive) {
        if (recursive) {
            return (int) FileUtils.listFilesAndDirs(directory, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE)
                .stream()
                .filter(File::isDirectory)
                .count() - 1; // Subtract 1 for the root directory
        } else {
            return (int) Arrays.stream(directory.listFiles())
                .filter(File::isDirectory)
                .count();
        }
    }
    
    private String getFileExtension(File file) {
        String name = file.getName();
        int lastDot = name.lastIndexOf('.');
        if (lastDot > 0 && lastDot < name.length() - 1) {
            return name.substring(lastDot);
        }
        return "(no extension)";
    }
    
    private String calculateFileHash(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        byte[] hash = md.digest(fileBytes);
        
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
