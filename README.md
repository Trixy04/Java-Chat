# JAVA CHAT
## Indice
- <a href="#Descrizione">Descrizione</a>
- <a href="#Strumenti">Strumenti utilizzati</a>
- <a href="#TipM">Tipologia messaggi</a>
- <a href="#Seq">Diagrammi di sequenza</a>
  - <a href="#conn">Diagramma di connessione</a>
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
Ogni comando come riportato in tabella sottostante, è separata dal testo da `:`.<br><br>
Esempio -> `MSB:`

<table>
  <tr>
     <td>Prefisso</td>
     <td>Funzionalità</td>
   </tr>
   <tr>
   <td>MSB:</td>
     <td>Permette di inviare un messaggio in broadcast</td>
   </tr>
      <tr>
   <td>NOME_UTENTE:</td>
     <td>Specificando inizialmente il nome dell'utente permette di inviare un messaggio privatamente</td>
   </tr>
      <td>CMD:</td>
     <td>Permette di ricevere in risposta la lista di tutti i comandi accettati dal server</td>
   </tr>
      <td>ULIST:</td>
     <td>Permette di ricevere in risposta la lista di tutti gli utenti connessi</td>
   </tr>
</table>

## <a name="Seq">Diagramma di sequenza</a>
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

## <a name="Lic">Licenza</a>
Questo software è sotto licenza MIT, consultabile al seguente [link](https://mit-license.org/)

