# JAVA CHAT
## Indice
- <a href="#Descrizione">Descrizione</a>
- <a href="#Strumenti">Strumenti utilizzati</a>
- <a href="#TipM">Tipologia messaggi</a>
 - [Diagrammi di sequenza](#diagrammi-di-sequenza)
    - [Diagramma della connessione](#diagramma-della-connessione)
    - [Diagramma della disconnessione](#diagramma-della-disconnessione)
    - [Diagramma del messaggio pubblico](#diagramma-del-messaggio-pubblico)
    - [Diagramma del messaggio privato](#diagramma-del-messaggio-privato)
- <a href="#Lic">Licenza</a>

## <a name="Descrizione">Descrizione</a>
Progettazione e implementazione di una chat sviluppata in Java, basata sull'architettura Client - Server, basandosi sul protocollo TCP. Il Client e il server comunicano in modalità full duplex. La chat supporta l'invio di messaggi in broadcast sia messaggi "privati".

## <a name="Strumenti">Strumenti utilizzati</a>
- Java 18 -> Maven
- JSON 
- Visual Studio Code
- NetBeans

## <a name="TipM">Tipologia messaggi</a>
Tutti i messaggi scambiati saranno serializzati in <a href="https://www.json.org/json-it.html">JSON</a>.<br><br>
Ogni comando come riportato in tabella sottostante, è separata dal testo da `" "`.<br><br>
Esempio -> `@user`

<table>
  <tr>
     <td>Prefisso</td>
     <td>Funzionalità</td>
   </tr>
   <tr>
   <td>@all & senza intestazione</td>
     <td>Permette di inviare un messaggio in broadcast</td>
   </tr>
      <tr>
   <td>@user</td>
     <td>Specificando dopo la @ il nome dell'utente permette di inviare un messaggio privatamente</td>
   </tr>
  <tr>
      <td>/list</td>
     <td>Permette di ricevere in risposta la lista di tutti gli utenti connessi</td>
  </tr>
  <tr>
  <td>/close</td>
     <td>Permette di chiudere la connessione col server ed uscire dalla chat</td>
   </tr>
</table>

## Diagramma di connessione
### <a name="conn">Diagramma di connessione</a>
```mermaid
sequenceDiagram
    participant Client
    participant Server
    participant Client già connessi
    Client->>Server: richiesta di connessione
        Server->>Server: controlla se username è valido
    Server->>Client: connessione accettata
        Server ->> Client già connessi: inoltro messaggio nuovo utente
```
---
### Diagramma della disconnessione
```mermaid
sequenceDiagram
    participant Client
    participant Server
    participant Partecipanti
    Client->>Server: avvisa della disconnessione
    Server->>Client: disconnette
        Server ->> Partecipanti: notifica del client disconesso
```
---
### Diagramma del messaggio pubblico
```mermaid
sequenceDiagram
    participant Client
    participant Server
    participant Partecipanti
    Client->>Server: invia il messaggio
        Server->>Server: controlla la validità
    Server ->> Partecipanti: inoltra il messaggio
    Server->>Client: messaggio inviato
```
---
### Diagramma del messaggio privato
```mermaid
sequenceDiagram
    participant Client
    participant Server
    participant Target
    Client->>Server: invia il messaggio
        Server->>Server: controlla la validità
        Server->>Server: cerca il target
    Server ->> Target: inoltra il messaggio
    Server->>Client: messaggio inviato
```

## <a name="Lic">Licenza</a>
Questo software è sotto licenza MIT, consultabile al seguente [link](https://mit-license.org/)

