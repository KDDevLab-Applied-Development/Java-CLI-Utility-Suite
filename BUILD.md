# Build und Setup Anleitung

## Voraussetzungen

- Java 17 oder höher
- Maven 3.6+ oder Gradle 7+
- Git

## Projekt bauen

### Mit Maven
```bash
# Kompilieren
mvn compile

# Tests ausführen
mvn test

# JAR-Datei erstellen
mvn package

# Alle Dependencies herunterladen
mvn dependency:resolve
```

### Ausführbare JAR erstellen
```bash
mvn clean package
```
Dies erstellt eine ausführbare JAR-Datei: `target/cli-utility-suite.jar`

## Ausführung

### Direkt mit Java
```bash
java -jar target/cli-utility-suite.jar --help
```

### Beispiele
```bash
# Verzeichnis scannen
java -jar target/cli-utility-suite.jar scan /path/to/directory --recursive --duplicates

# Datei konvertieren
java -jar target/cli-utility-suite.jar convert input.json output.xml --from json --to xml

# API testen
java -jar target/cli-utility-suite.jar api GET https://api.example.com/users

# Backup erstellen
java -jar target/cli-utility-suite.jar backup /source/path /backup/path --compress

# Log-Datei parsen
java -jar target/cli-utility-suite.jar parse app.log --level ERROR --stats
```

## Entwicklung

### IDE Setup
- Importiere das Projekt als Maven-Projekt
- Stelle sicher, dass Java 17+ als Project SDK konfiguriert ist
- Aktiviere Annotation Processing für PicoCLI

### Tests ausführen
```bash
# Alle Tests
mvn test

# Spezifische Test-Klasse
mvn test -Dtest=FileScannerTest

# Mit Coverage Report
mvn test jacoco:report
```

### Code-Stil
- Verwende Java-Standardkonventionen
- Schreibe aussagekräftige Javadoc-Kommentare
- Halte Methoden klein und fokussiert
- Verwende finale Variablen wo möglich

## Deployment

### Standalone JAR
```bash
mvn clean package
cp target/cli-utility-suite.jar /usr/local/bin/
```

### Als System-Tool installieren (Unix/Linux)
```bash
# Erstelle ein Wrapper-Script
echo '#!/bin/bash' > /usr/local/bin/cli-suite
echo 'java -jar /usr/local/bin/cli-utility-suite.jar "$@"' >> /usr/local/bin/cli-suite
chmod +x /usr/local/bin/cli-suite
```

## Problembehandlung

### Häufige Probleme

1. **Java Version Fehler**
   ```
   Lösung: Stelle sicher, dass JAVA_HOME auf Java 17+ zeigt
   ```

2. **Abhängigkeiten nicht gefunden**
   ```
   mvn clean dependency:resolve
   ```

3. **Tests schlagen fehl**
   ```
   mvn clean test -Dmaven.test.failure.ignore=true
   ```
