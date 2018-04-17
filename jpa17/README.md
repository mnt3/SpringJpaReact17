
#### Aprašymas

JPA Egzamino užduotis.

#### Vertinimas

Pažymį sudarys testų rezultatai.
Testų pabaigoje yra atspausdinamas galimas pažymys:

```
Score:         26/26
Avarage grade: 10.00
Grade:         10
```

Nusirašinėti, dalintis patarimais, kopijuoti iš kolegų github'o yra draudžiama.
Destytojų akis yra pakankamai įgudusi pamatyti copy-paste.

Paleidimas iš komandinės eilutės:

```
$ mvn clean test
```

#### Užduotis

Susigalvokite dalykinę sritį kurią modeliuosite (pvz elektroninė parduotuvė).
Esybių pavyzdžiai: prekių krepšelis, prekė ir t.t..

Sugalvokite dviejų tipų esybes.

- Tėvinė esybė 
  - Implementuoti UniqueEntityWithAssociation interfeisą.
  - Turi turėti automatiškai generuojamą ID lauką (Long getId()) 
  ir bent du su dalykine sritimi susijusius laukus.
  - Turi turėti OneToMany tipo sąryšį, kurio nuoroda grąžinama oneToMany()
  - Sąryšio savybės:
    - Aktyviai užkraunamas
    - išsaugant arba trinant tėvinį objektą sąryšis turi būti taip pat išsaugomas arba ištrinamas
    - tėvinio objekto atnaujinimo operacijos neturi propoguotis sąryšiui

- Vakinė (OneToMany) esybė
    - Implementuoti UpdatableEntity interfeisą
    - Turi turėti automatiškai generuojamą ID lauką (Long getId()).
    - getId, getString, setString metodus
    
- Sugalvokite prasmingus dalykinės srities pavadinimus. 
  Pvz getString turetų grąžinti prasmingai pavadinto lauko reikšmę.
     
- Užbaikite TestData.getNewEntity1() kuris turi grąžinti jūsų 
modeliujamą esybę su duomenimis ir sąryšiais.       

Vertinimas: viso 4 balai. 1 balas už prasmingus pavadinimus. 3 balai už pass'inančius testus.      
