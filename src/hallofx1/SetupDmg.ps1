<#
 .Synopsis
 Erstellen einer Dmg-Datei mit Hilfe von JPackage
#>

# Falls erforderlich, zuerst kompilieren
# javac --module-path /Library/JavaFx/javafx-sdk-17.0.2/lib --add-modules  javafx.controls App.java
# Später ausführen
# java -jar App.jar --module-path /Library/JavaFx/javafx-sdk-17.0.2/lib --add-modules  javafx.controls

# Schritt 1: Temp-Verzeichnis löschen und danach neu anlegen
$TempPfad = Join-Path -Path $PSScriptRoot -ChildPath "temp/hallofx1"
Remove-Item -Path $TempPfad -Recurse -Force
New-Item -Path $TempPfad -ItemType Directory -ErrorAction Ignore | Out-Null

# Schritt 2: Jar-Datei mit Manifestdatei anlegen
$ClassPath = "/Users/pemo/Projects/HalloFx1/hallofx1/bin/hallofx1/app.class"

Copy-Item -Path $ClassPath -Destination $TempPfad -Force

$ClassPath = Join-Path -Path $TempPfad -ChildPath (Split-Path -Path $ClassPath -Leaf)

$Manifestpfad = Join-Path -Path $TempPfad -ChildPath "MainClass.txt"
$JarName = "App.jar"

"Main-Class: hallofx1.App" | Set-Content -Path $Manifestpfad

# c = create jar file
# m = add manifest
# f = "send output to the file name"?
# v = verbose
jar cmfv $Manifestpfad $JarName $ClassPath

if ($LASTEXITCODE -eq 0 -and (Test-Path -path $JarName))
{
    Copy-Item -Path $JarName -Destination $TempPfad -Verbose
    Write-Verbose "*** Jar-Datei $JarName wurde in $TempPfad angelegt ***" -Verbose
}
else 
{
    Write-Warning "!!! Jar-Datei anlegen - ExitCode=$LastExitCode !!!"
}

# Schritt 2: Icons für die App anlegen

$IconsetPfad = ""

# Schritt 3: Alle Dateien in das input-Verzeichnis kopieren


# Schritt 4: Jpackage aufrufen

$Args = ""