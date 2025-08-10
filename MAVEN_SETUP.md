# Maven Installation Guide für Windows

## Option 1: Chocolatey (Empfohlen)
Wenn du Chocolatey installiert hast:
```powershell
choco install maven
```

## Option 2: Scoop
Wenn du Scoop installiert hast:
```powershell
scoop install maven
```

## Option 3: Manuelle Installation

### Schritt 1: Maven herunterladen
1. Gehe zu https://maven.apache.org/download.cgi
2. Lade "Binary zip archive" herunter (z.B. apache-maven-3.9.5-bin.zip)

### Schritt 2: Maven extrahieren
1. Extrahiere das ZIP-Archiv nach `C:\apache-maven-3.9.5`

### Schritt 3: Umgebungsvariablen setzen
1. Öffne "Systemsteuerung" → "System" → "Erweiterte Systemeinstellungen"
2. Klicke auf "Umgebungsvariablen"
3. Füge eine neue Systemvariable hinzu:
   - Name: `MAVEN_HOME`
   - Wert: `C:\apache-maven-3.9.5`
4. Bearbeite die `PATH` Variable und füge hinzu: `%MAVEN_HOME%\bin`

### Schritt 4: Installation testen
Öffne eine neue Eingabeaufforderung und führe aus:
```cmd
mvn -version
```

## Alternativen

### Option A: Maven Wrapper verwenden
Wenn Maven nicht installiert werden kann, erstelle einen Maven Wrapper:
```cmd
# Wrapper herunterladen (manuell erforderlich)
# Dann verwenden:
.\mvnw clean compile
.\mvnw test
.\mvnw package
```

### Option B: IDE verwenden
- **IntelliJ IDEA**: Hat Maven bereits integriert
- **Eclipse**: Installiere m2e Plugin
- **VS Code**: Installiere "Extension Pack for Java"

## Nach der Installation

Führe das Setup-Script aus:
```cmd
setup.bat
```

Oder baue manuell:
```cmd
mvn clean compile test package
```
