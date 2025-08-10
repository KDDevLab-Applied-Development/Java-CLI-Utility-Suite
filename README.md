# Java CLI Utility Suite

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/KDDevLab-Applied-Development/Java-CLI-Utility-Suite)
[![Java](https://img.shields.io/badge/Java-17%2B-orange)](https://openjdk.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9.5-blue)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-Custom-red)](./LICENSE.md)

## 🚀 Projektübersicht

Die **Java CLI Utility Suite** ist ein vielseitiges, eigenständiges Kommandozeilen-Tool, das häufige Aufgaben aus dem Berufsalltag automatisiert und vereinfacht. Das Tool bietet Funktionen wie Datei-Scans, Logfile-Parsing, Datenkonvertierung, Backup-Management und API-Tests direkt aus der Konsole.

**Status**: ✅ **Funktionsfähig** - Scanner-Modul vollständig implementiert, andere Module als Grundgerüst verfügbar.

## ⚡ Quick Start

```bash
# 1. Repository klonen
git clone https://github.com/KDDevLab-Applied-Development/Java-CLI-Utility-Suite.git
cd Java-CLI-Utility-Suite

# 2. Bauen (Maven erforderlich)
mvn clean package -DskipTests

# 3. Ausführen
java -jar target/cli-utility-suite.jar --help

# 4. Beispiel: Verzeichnis scannen
java -jar target/cli-utility-suite.jar scan . --recursive --stats --duplicates
```

## 🔧 Features

### ✅ **Datei-Scanner & Analyse** (Implementiert)
- Rekursive Verzeichnisdurchsuchung
- Duplikat-Erkennung (MD5-Hash basiert)
- Statistiken zu Dateitypen und -größen
- Filter nach Dateierweiterungen

### 🔄 **Logfile-Parser** (Grundgerüst)
- Filtert Logdateien nach Log-Level (INFO, WARN, ERROR)
- Fehlerstatistiken und Zeitraumanalysen

### 🔄 **Daten-Konverter** (Grundgerüst)
- Konvertierung zwischen JSON, XML und CSV
- Formatvalidierung und Pretty-Print

### 🔄 **Backup- & Ordner-Organizer** (Grundgerüst)
- Automatisches Backup von Ordnern
- Komprimierung und inkrementelle Backups
- Datei-Organisation basierend auf Regeln

### 🔄 **API-Test-Client** (Grundgerüst)
- HTTP-Requests (GET, POST, PUT, DELETE)
- Header-Support und Response-Anzeige

---

## 🛠️ Tech Stack

- **Java 17+** (getestet mit Java 23)
- **Maven 3.9.5** Build System  
- **PicoCLI 4.7.5** Command Line Interface Framework
- **Apache Commons IO 2.13.0** File Operations
- **Jackson 2.15.2** JSON/XML Processing
- **OpenCSV 5.7.1** CSV Processing
- **Apache HttpClient 5.2.1** HTTP Requests
- **JUnit 5 & Mockito** Testing Framework

## � Installation & Setup

### Voraussetzungen
- ✅ **Java 17+** (Java 23 wird verwendet)
- ✅ **Maven 3.9.5+** (automatisch installiert)

### Installation
```bash
# 1. Repository klonen
git clone https://github.com/KDDevLab-Applied-Development/Java-CLI-Utility-Suite.git
cd Java-CLI-Utility-Suite

# 2. Maven installieren (falls nicht vorhanden)
# Windows PowerShell als Administrator:
Set-ExecutionPolicy Bypass -Scope Process -Force
[System.Net.ServicePointManager]::SecurityProtocol = 3072
iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
choco install maven

# 3. Projekt bauen
mvn clean package -DskipTests

# 4. CLI testen
java -jar target/cli-utility-suite.jar --help
```

Alternative: Siehe [INSTALL_MAVEN.md](./INSTALL_MAVEN.md) für detaillierte Installationsanweisungen.

## 🎯 Verwendung

### Kommandoübersicht
```bash
java -jar target/cli-utility-suite.jar [COMMAND] [OPTIONS]

Verfügbare Commands:
  scan      Datei-Scanner und Analyse
  parse     Log-Datei Parser  
  convert   Datenformat-Konverter
  backup    Backup und Ordner-Organisation
  api       API-Test Client
```

### Beispiele

#### 📁 Datei-Scanner
```bash
# Aktuelles Verzeichnis scannen
java -jar target/cli-utility-suite.jar scan .

# Rekursiv mit Statistiken und Duplikat-Erkennung
java -jar target/cli-utility-suite.jar scan /path/to/directory --recursive --stats --duplicates

# Nur bestimmte Dateitypen
java -jar target/cli-utility-suite.jar scan . --filter .java,.xml,.json
```

#### 🔍 Log-Parser (Vorbereitet)
```bash
java -jar target/cli-utility-suite.jar parse app.log --level ERROR --stats
java -jar target/cli-utility-suite.jar parse app.log --from "2025-08-01 00:00:00" --to "2025-08-10 23:59:59"
```

#### 🔄 Daten-Konverter (Vorbereitet)
```bash
java -jar target/cli-utility-suite.jar convert data.json data.xml --from json --to xml --pretty
java -jar target/cli-utility-suite.jar convert data.csv data.json --from csv --to json
```

#### 💾 Backup (Vorbereitet)
```bash
java -jar target/cli-utility-suite.jar backup /source/path /backup/path --compress
java -jar target/cli-utility-suite.jar backup /source /dest --incremental --exclude "*.tmp,*.log"
```

#### 🌐 API-Tests (Vorbereitet)
```bash
java -jar target/cli-utility-suite.jar api GET https://httpbin.org/get
java -jar target/cli-utility-suite.jar api POST https://httpbin.org/post --data '{"key":"value"}'
java -jar target/cli-utility-suite.jar api GET https://api.github.com/users/octocat --header "Accept: application/json"
```

## 🔧 Development

### Build Commands
```bash
# Kompilieren
mvn compile

# Tests ausführen  
mvn test

# JAR erstellen
mvn package

# Alles zusammen
mvn clean compile test package
```

### Projektstruktur
```
src/main/java/com/kddevlab/cli/
├── CliUtilitySuite.java        # Haupt-CLI Anwendung
├── scanner/                    # ✅ Datei-Scanner (implementiert)
├── parser/                     # 🔄 Log-Parser (Grundgerüst)
├── converter/                  # 🔄 Daten-Konverter (Grundgerüst)  
├── backup/                     # 🔄 Backup-Manager (Grundgerüst)
└── api/                        # 🔄 API-Client (Grundgerüst)
```

### Contributing
Das Projekt ist in aktiver Entwicklung. Die Scanner-Funktionalität ist vollständig implementiert, andere Module können erweitert werden.

---

## 📄 Lizenz & Nutzungshinweis

    📌 Hinweis: Dieses Repository ist öffentlich einsehbar, aber nicht Open Source.
    Eine Nutzung, Veränderung oder Weitergabe ist ausschließlich mit schriftlicher Genehmigung des Eigentümers erlaubt.

## 📝 Lizenz

Dieses Projekt unterliegt einer benutzerdefinierten Lizenz.  
Details findest du in der Datei [LICENSE.md](./LICENSE.md).  
**Nutzung oder Weitergabe nur mit ausdrücklicher Genehmigung erlaubt.**

---

⏳ **Status**: Funktionsfähig ✅ | Letzte Aktualisierung: 10.08.2025