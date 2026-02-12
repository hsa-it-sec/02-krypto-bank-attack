# krypto2-atm-attack

Ich habe eine Online-Bank gefunden, die eine gravierende Schwachstelle bei der Verwendung von RSA enthält.

## Was ich bisher herausgefunden habe
### Zugriff
Ich kann die Banking-App mit folgendem Befehl starten (Docker muss installiert sein):
```bash
docker-compose up --build
```
Anschließend kann ich über den Browser auf die Login-Seite zugreifen:
```bash
http://localhost:8080
```

### Funktionsweise
- Der Nutzer gibt seine Kontonummer und seine vierstellige PIN ein
- Die PIN wird mit dem öffentlichen Schlüssel der Bank RSA verschlüsselt
- Anschließend wird die Kontonummer und die verschlüsselte PIN (in hexadezimaler Darstellung) an den Banking-Server geschickt
- Der Server entschlüsselt die PIN mit dem privaten Schlüssel
- Der Login ist erfolgreich, wenn es die Kontonummer gibt und der entschlüsselte PIN mit dem auf dem Server gespeicherten PIN übereinstimmt

### Abgefangene PIN
Ich konnte den Netzwerk-Traffic beobachten, als sich der Nutzer mit der Kontonummer `123456` eingeloggt hat.

Die abgefangene, verschlüsselte PIN ist in der Datei `captured_pin.txt` gespeichert.

### Cracking
Ich habe auch angefangen, in der Datei `RSACracker.java` ein Programm zu schreiben, mit dem ich eine PIN mit RSA verschlüsseln kann.

Aber irgendwie fehlt mir noch die zündende Idee, wie ich die abgefangene, verschlüsselte PIN knacken könnte ...
