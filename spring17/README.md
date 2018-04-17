
### Aprašymas

Spring egzamino užduotis.

Realizuoti REST tipo servisą, naudojant **Spring Boot** technologiją.

### Vertinimas

Pažymį sudarys sėkmingai išpildytų reikalavimų kiekis bei kodo stilius. 

Testų pabaigoje yra atspausdinamas **galimas** pažymys:

```
Score:         10/10
Avarage grade: 10.00
Grade:         10
```

Nusirašinėti, dalintis patarimais, kopijuoti iš kolegų github'o yra draudžiama.
Destytojų akis yra pakankamai įgudusi pamatyti copy-paste.

### Aplikacijos aprašymas

#### Aplikacijos paleidimas

Aplikacija gali būti paleista šiais būdais:

`$ mvn clean tomcat7:run`

Sėkmingai paleidus aplikaciją, adresu http://localhost:9092/spring-exam/ turėtumėte pamatyti tekstą: 'Spring-Repeated-Exam 2017'.

Aplikacijos darinio (angl. *build*) paruošimas įvykdant testus:

`$ mvn clean verify`

#### Aplikacijos konfigūracija

Apache tomcat serverio port'as nurodomas pom.xml esančiame parametre `<server.port>9092</server.port>`.

### Reikalavimai

#### Veikianti aplikacija (10 taškų)

##### Korektiškai sukonfigūruotas Spring aplikacijų kontekstas (5 taškai iš 10)

Korektiškai panaudotos Spring aplikacijų konteksto anotacijos (@Component, @Repository, @Autowired ir t.t.).

Klasės, kurių objektai turi būti valdomi Spring aplikacijų konteksto:
- `lt.itakademija.controller.MessengerServiceController`
- `lt.itakademija.repository.MessengerRepository`
- `lt.itakademija.repository.InMemoryMessengerRepository`
- `lt.itakademija.repository.SequenceGenerator`
- `lt.itakademija.repository.SequenceGeneratorImpl`

Testas, kuris testuoja realizaciją: `lt.itakademija.SpringConfigurationTask` (galima paleisti per Eclipse aplinką)

##### Korektiškai realizuotas REST serviso kontroleris (5 taškai iš 10)

Vadovaudamiesi šiame dokumente pateiktu REST serviso aprašymu bei integraciniu testu, realizuokite serviso kontrolerį iki galo.
**Jums tereikia sujungti skirtingas programos dalis (kontroleris, repozitorija ir pan.), todėl programavimo darbai yra 
minimalūs.**

Serviso kontrolerio klasė: `lt.itakademija.controller.MessengerServiceController`.

Serviso URL: 
- http://localhost:9092/spring-exam/webapi/messenger/contacts
- http://localhost:9092/spring-exam/webapi/messenger/messages

Testas, kuris testuoja realizaciją: `lt.itakademija.RestServicesTask`

### REST serviso aprašymas

Servisas `/webapi/messenger` teikia operacijas, leidžiančias valdyti kontaktus bei susirašinėti su jais.

---

##### Operacija: `POST /webapi/messenger/contacts`

Sukuria naują kontaktą.

###### Užklausos duomenys:

Modelis: `lt.itakademija.model.command.CreateContact`

| Laukas        | Aprašymas                  |
| ------------- |:-------------------------- |
| username      | kontakto unikalus vardas   |
| name          | kontakto pavadinimas       |

Pavyzdys (JSON):
```
{
  "username"  : "test",
  "name"      : "Testas Testaitis"
}
```

###### Atsakymo duomenys:

HTTP Statusas: 201 Created

JSON:
```
{
  "id" : 1
}
```

---

##### Operacija: `GET /webapi/messenger/contacts`

Grąžinamas sąrašas registruotų kontaktų.

###### Užklausos duomenys:

Nėra

###### Atsakymo duomenys:

HTTP Statusas: 200 OK

Pavyzdys (JSON):
```
[
  {
    "id"       : 1,
    "username" : "testas",
    "name"     : "Testas Testaitis"
  }
]
```
---

##### Operacija: `DELETE /webapi/messenger/contacts/{contactId}`

Ištrina kontaktą.

###### Užklausos duomenys:

Parametrai:

| Parametras    | Duomenų tipas | Aprašymas            |
| ------------- |:------------- | :--------------------|
| contactId     | `long`        | Trinamo kontakto ID. |

###### Atsakymo duomenys:

HTTP Statusas: 200 OK

---

##### Operacija: `PUT /webapi/messenger/contacts/{contactId}`

Modifikuoja kontakto duomenis.

###### Užklausos duomenys:

Parametrai:

| Parametras    | Duomenų tipas | Aprašymas                  |
| ------------- |:------------- | :------------------------- |
| contactId     | `long`        | kontakto pavadinimas.      |

Modelis: `lt.itakademija.model.command.UpdateContact`

| Laukas        | Aprašymas                  |
| ------------- |:-------------------------- |
| name          | kontakto unikalus vardas   |

###### Atsakymo duomenys:

HTTP Statusas: 200 OK

---

##### Operacija: `GET /webapi/messenger/contacts/{contactId}`

Grąžina konkretaus kontakto duomenis.

###### Užklausos duomenys:

Parametrai:

| Parametras    | Duomenų tipas | Aprašymas            |
| ------------- |:------------- | :--------------------|
| contactId     | `long`        | Trinamo kontakto ID. |

###### Atsakymo duomenys:

HTTP Statusas: 200 OK

Pavyzdys (JSON):
```
{
"id"       : 1,
"username" : "testas",
"name"     : "Testas Testaitis"
}
```

---

##### Operacija: `POST /webapi/messenger/messages/{contactId}`

Siunčia pranešimą gavėjui (vienam iš registruotų kontaktų).

###### Užklausos duomenys:

Parametrai:

| Parametras    | Duomenų tipas | Aprašymas             |
| ------------- |:------------- | :---------------------|
| contactId     | `long`        | Gavėjo (kontakto) ID. |

Modelis: `lt.itakademija.model.command.CreateMessage`

| Laukas        | Duomenų tipas | Aprašymas             |
| ------------- |:------------- | :---------------------|
| text          | `string`      | Žinutės tekstas       |

###### Atsakymo duomenys:

HTTP Statusas: 201 Created

JSON:
```
{
  "id" : 1
}
```

##### Operacija: `GET /webapi/messenger/messages/{contactId}`

Grąžinamas sąrašas pranešimų išsiųstų konkrečiam gavėjui.

###### Užklausos duomenys:

Parametrai:

| Parametras    | Duomenų tipas | Aprašymas             |
| ------------- |:------------- | :---------------------|
| contactId     | `long`        | Gavėjo (kontakto) ID. |

###### Atsakymo duomenys:

HTTP Statusas: 200 OK

Modelis: `lt.itakademija.model.Message`

JSON:
```
[
  {
    "id"   : 1,
    "text" : "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor"
  }
]
```