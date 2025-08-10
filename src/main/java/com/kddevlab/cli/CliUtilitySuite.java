package com.kddevlab.cli;

import com.kddevlab.cli.api.ApiCommand;
import com.kddevlab.cli.backup.BackupCommand;
import com.kddevlab.cli.converter.ConverterCommand;
import com.kddevlab.cli.parser.ParserCommand;
import com.kddevlab.cli.scanner.ScannerCommand;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * Main entry point for the Java CLI Utility Suite.
 * This class orchestrates all the different utility commands.
 */
@Command(
    name = "cli-utility-suite",
    description = "Java CLI Utility Suite - A versatile command-line tool for workplace automation",
    version = "1.0.0",
    mixinStandardHelpOptions = true,
    subcommands = {
        ScannerCommand.class,
        ParserCommand.class,
        ConverterCommand.class,
        BackupCommand.class,
        ApiCommand.class
    }
)
public class CliUtilitySuite implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "Enable verbose output")
    private boolean verbose;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new CliUtilitySuite())
            .setUsageHelpWidth(120)
            .execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println("Java CLI Utility Suite v1.0.0");
        System.out.println("Use --help to see available commands.");
        System.out.println();
        System.out.println("Available commands:");
        System.out.println("  scan      - File scanner and analysis");
        System.out.println("  parse     - Log file parser");
        System.out.println("  convert   - Data format converter");
        System.out.println("  backup    - Backup and folder organizer");
        System.out.println("  api       - API test client");
    }

    public boolean isVerbose() {
        return verbose;
    }
}
