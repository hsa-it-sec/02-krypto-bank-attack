# 🕵️‍♂️ Mission: Bank Hack

Ich habe eine Online-Bank gefunden, deren RSA-Implementierung so löchrig ist wie ein Schweizer Käse. Das ist unsere Chance! 💸

## 📝  Logbuch - Was ich bisher herausgefunden

### 🔓 Zugriff
Die Banking-App lässt sich easy im Container hochfahren (Docker muss installiert sein):
```bash
docker run -p 8080:8080 --pull=always --rm ghcr.io/hsa-it-sec/krypto2-bank:latest
```
Anschließend kann ich über den Browser auf die Login-Seite zugreifen:
```bash
http://localhost:8080
```

### ⚙️ Funktionsweise
- Der Nutzer gibt Kontonummer und eine **vierstellige PIN** ein.
- Client-Side Encryption: Die PIN wird mit dem Public Key der Bank (RSA) verschlüsselt.
- Payload: Kontonummer + verschlüsselte PIN (Hex) gehen an den Server.
- Server entschlüsselt mit die PIN mit dem Private Key.
- Login Erfolg, wenn Kontonummer existiert und PIN matcht.

### 📡 Intercepted Data
Ich habe mich in die Leitung gehängt (Man-in-the-Middle) und Traffic mitgeschnitten.
Ein Opfer hat sich mit der Kontonummer `123456` eingeloggt. Bingo! 🎣

Die abgefangene, verschlüsselte PIN ist in der Datei `captured_pin.txt` gespeichert.

### 🔨 Cracking & Status
Ich habe in `RSACracker.java` schon mal ein Tool vorbereitet, um PINs testweise zu verschlüsseln.

**Problem:** Mir rennt die Zeit davon, ich muss untertauchen... 🚓💨
Ihr müsst den Job zu Ende bringen! Findet einen Weg, die abgefangene PIN zu knacken und holt euch den Zugriff.

Viel Erfolg! 🏴‍☠️
