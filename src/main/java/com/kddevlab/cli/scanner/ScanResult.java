package com.kddevlab.cli.scanner;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Result object containing the results of a directory scan operation.
 */
public class ScanResult {
    
    private int totalFiles;
    private int totalDirectories;
    private long totalSize;
    private Map<String, Integer> fileTypeStats = new HashMap<>();
    private Map<String, List<File>> duplicates = new HashMap<>();
    
    public ScanResult() {
        // Default constructor
    }
    
    // Getters and Setters
    public int getTotalFiles() {
        return totalFiles;
    }
    
    public void setTotalFiles(int totalFiles) {
        this.totalFiles = totalFiles;
    }
    
    public int getTotalDirectories() {
        return totalDirectories;
    }
    
    public void setTotalDirectories(int totalDirectories) {
        this.totalDirectories = totalDirectories;
    }
    
    public long getTotalSize() {
        return totalSize;
    }
    
    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }
    
    public Map<String, Integer> getFileTypeStats() {
        return fileTypeStats;
    }
    
    public void setFileTypeStats(Map<String, Integer> fileTypeStats) {
        this.fileTypeStats = fileTypeStats != null ? fileTypeStats : new HashMap<>();
    }
    
    public Map<String, List<File>> getDuplicates() {
        return duplicates;
    }
    
    public void setDuplicates(Map<String, List<File>> duplicates) {
        this.duplicates = duplicates != null ? duplicates : new HashMap<>();
    }
    
    @Override
    public String toString() {
        return "ScanResult{" +
                "totalFiles=" + totalFiles +
                ", totalDirectories=" + totalDirectories +
                ", totalSize=" + totalSize +
                ", fileTypeStats=" + fileTypeStats.size() + " types" +
                ", duplicates=" + duplicates.size() + " groups" +
                '}';
    }
}
