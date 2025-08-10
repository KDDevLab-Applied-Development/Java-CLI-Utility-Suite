# Maven Installation - Einfache Anleitung

## Option 1: Chocolatey installieren (empfohlen)

Öffne PowerShell **als Administrator** und führe aus:

```powershell
Set-ExecutionPolicy Bypass -Scope Process -Force
[System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072
iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
```

Nach der Installation von Chocolatey:
```powershell
choco install maven
```

## Option 2: Scoop installieren (alternative)

```powershell
Set-ExecutionPolicy RemoteSigned -Scope CurrentUser
irm get.scoop.sh | iex
scoop install maven
```

## Option 3: Maven direkt herunterladen (wenn Package Manager nicht gewünscht)

1. **Download**: https://maven.apache.org/download.cgi
   - Lade "Binary zip archive" herunter (z.B. `apache-maven-3.9.5-bin.zip`)

2. **Extrahieren**: 
   - Nach `C:\Tools\apache-maven-3.9.5`

3. **PATH setzen**:
   ```cmd
   setx MAVEN_HOME "C:\Tools\apache-maven-3.9.5"
   setx PATH "%PATH%;%MAVEN_HOME%\bin"
   ```

4. **Neue Eingabeaufforderung öffnen** und testen:
   ```cmd
   mvn -version
   ```

## Option 4: IDE verwenden (ohne Maven Installation)

- **VS Code**: Extension "Extension Pack for Java" installieren
- **IntelliJ IDEA**: Hat Maven bereits integriert
- **Eclipse**: m2e Plugin ist standardmäßig installiert

## Schneller Test ohne Maven

Falls du erstmal ohne Maven arbeiten möchtest:

```cmd
# Manuell kompilieren (nur für Tests)
javac -cp "lib/*" src/main/java/com/kddevlab/cli/*.java
```

Aber Maven ist definitiv empfohlen für das vollständige Build-System!
