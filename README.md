Aplikacija ima zelo špartanski UI, ker mi je enostavno zmanjkalo časa za izpopolnjevanje vsega skupaj. Predvsem bi lahko dodal še napis, za kater kraj se prikazujejo vremenski podatki. Če uporabnik izbriše vrednost iz vnosnega polja, tega podatka ni več.
Pri iskanju lokacij je nekaj bugov:
- nekoliko nerodno obnašanje ob kliku na gumb za brisanje vnosa
- občasen glitch ob prikazovanju predlog
  Kako točno deluje prikazovanje se nisem poglabljal - zadeva je bila skopirana iz nekega članka na Medium-u.

Predvsem DI bi bil lahko narejen bolje. Bolje bi bilo če bi uporabil več modulov, zgolj da bi ločil instance različnih APIjev, npr. Prav tako bi moral nekatere instance izločiti in jih potem samo injectati  (npr. interceptor).
DI sem sicer hotel narediti z uporabo annotationov, ampak mi je nekaj nagajalo, potem pa nisem spet poskušal. Verjetno bom v prihodnjih dneh, ker moram stvari tako ali drugače priti do dna. S tega naslova je ostalo kar nekaj zakomentirane kode.

Koda bi v splošnem verjetno lahko bila nekoliko bolj pospravljena.

To, da je arhitektura absolutno pretiravanje, smo se že pogovarjali. Za takšen app bi jaz uporabil en modul. DI bi verjetno naredil na roke.

Logiranje sem precej zanemaril. Tega bi moralo biti precej več. Še posebej za kakšne napake.

Do pisanja testov nisem prišel.

Implementiral sem plugine, ki se jih potem lahko uporablja v vseh modulih, da se configuracijo obdrži na enem mestu. Tu bi šel lahko še dlje in združil nekatere dele pluginov. Predvsem za `ui` in `data` module. Tu je še nekaj dupliciranja. Prav tako bi se lahko dodalo še skupne dependencyje.

Shranjevanje preteklih iskanj v bazo je pretiravanje. Za to bi bili `SharedPreferences` povsem ok.
Prav tako je navigacija seveda nesmiselna.

Nisem preveril kako se aplikacija obnaša, če se požene release build. Verjetno kaj odleti zaradi obfuskacije.
