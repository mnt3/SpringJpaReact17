
#### Aprašymas

React Egzamino užduotis.

Užduotyje reikės sukurti puslapį su lėktuvų skrydžių sąrašu.
Backend'as jau yra sukurtas už jus. Išbandyti jį galite http://localhost:8080/swagger-ui.html.
Front-end kodas rašoma bus `/src/main/resources/static`. Jau yra Hello World tipo implementacija.

Paleidimas iš komandinės eilutės:

```
$ mvn clean spring-boot:run -Drun.addResources=true
```

Statiniai resursai (js,html) atsinaujina iškart po pakeitimų.

#### Vertinimas

Pažymį sudarys sėkmingai išpildytų reikalavimų kiekis, kodo stilius.

Nusirašinėti, dalintis patarimais,
kopijuoti iš kolegų github'o yra draudžiama.
Dėstytojų akis yra pakankamai įgudusi pamatyti copy-paste.

#### Reikalavimai (viso 15 taškų)

##### Skydžių sąrašas (5 taškai)

Sukurkite oro uosto skrydžių sąrašo puslapį.

- Atėjus į namų puslapį (localhost:8080) turime matyti skrydžių sąrašą. 
  Pakanka paprastos HTML lentelės.
- Užkraukite skrydžius iš `GET /api/flights` (5 taškai)
- Sąraše turi matytis: id, data, laikas, skrydžio numeris, skraidinanti įmonė, skrydžio statusas.

##### Skrydžio trynimas (10 taškų)

- Paspaudus ant skrydžio ID tas skrydis turi būti pažymimas "trynimui" (5 taškai)
- Trinamas skrydis - visa skrydžio eilutė nuspalvinama raudonai (3 taškai)
- Apačioje sąrašo yra mygtukas "Trinti" (2 taškai)
  - Paspaudus trinti yra ištrinami visi pažymėti skrydžiai naudojant `DELETE /api/flights/{id}`
  - Po paspaudimo ištrinti skrydžiai turi dingti iš sąrašo
