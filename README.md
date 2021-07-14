# Schach

Dieses Programm ist ein Schachspiel. Dieses Spiel basiert auf Konsole und GUI.

Für die Architektur wird das MVC-Modell verwendet.

Sowohl in "Cli als auch in Gui" hat es die Möglichkeit, gegen Mensch oder KI zu spielen. 

In GUI kann man dieses Spiel über zwei Computer in einem LAN durchführen.

Als Zusatzfeatures stehen Schachuhr und Rückgängig zur Verfügung.

This program is a game of chess. This game is based on console and GUI.

For architecture the MVC model is used.

In "Cli as well as in Gui" it has the option of playing against humans or AI.

In GUI you can play this game on two computers in a LAN.

Chess clock and undo are available as additional features.

# Maven

Kurzübersicht nützlicher Maven-Befehle. Weitere Informationen finden sich im Tutorial:

* `mvn clean` löscht alle generierten Dateien
* `mvn compile` übersetzt den Code
* `mvn javafx:jlink` packt den gebauten Code als modulare Laufzeit-Image. Das Projekt kann danach gestartet werden
  mit `target/chess/bin/chess`
* `mvn test` führt die Tests aus
* `mvn compile site` baut den Code, die Dokumentation und die Tests und führt alle Tests, sowie JaCoCo und PMD inklusive
  CPD aus. Die Datei `target/site/index.html` bietet eine Übersicht über alle Reports.
* `mvn javafx:run` führt das Projekt aus
* `mvn javafx:run -Dargs="--no-gui"` führt das Projekt mit Command-Line-Parameter `--no-gui` aus.
