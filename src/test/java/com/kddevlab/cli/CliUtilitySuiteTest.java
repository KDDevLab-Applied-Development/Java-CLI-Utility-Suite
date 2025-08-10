package com.kddevlab.cli;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;

/**
 * Test class for the main CliUtilitySuite application.
 */
class CliUtilitySuiteTest {

    private CommandLine commandLine;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        commandLine = new CommandLine(new CliUtilitySuite());
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testMainCommandWithoutArguments() {
        int exitCode = commandLine.execute();
        assertEquals(0, exitCode);
        
        String output = outputStream.toString();
        assertTrue(output.contains("Java CLI Utility Suite"));
        assertTrue(output.contains("scan"));
        assertTrue(output.contains("parse"));
        assertTrue(output.contains("convert"));
        assertTrue(output.contains("backup"));
        assertTrue(output.contains("api"));
    }

    @Test
    void testHelpOption() {
        int exitCode = commandLine.execute("--help");
        assertEquals(0, exitCode);
        
        String output = outputStream.toString();
        assertTrue(output.contains("Usage:"));
        assertTrue(output.contains("cli-utility-suite"));
    }

    @Test
    void testVersionOption() {
        int exitCode = commandLine.execute("--version");
        assertEquals(0, exitCode);
        
        String output = outputStream.toString();
        assertTrue(output.contains("1.0.0"));
    }

    @Test
    void testScanCommandHelp() {
        int exitCode = commandLine.execute("scan", "--help");
        assertEquals(0, exitCode);
        
        String output = outputStream.toString();
        assertTrue(output.contains("Scan directories"));
    }

    @Test
    void testInvalidCommand() {
        int exitCode = commandLine.execute("invalid-command");
        assertNotEquals(0, exitCode);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}
