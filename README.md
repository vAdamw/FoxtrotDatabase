# FoxtrotDatabase

- Ickefunktionella krav -
Databasen ska byggas med MySQL.
Byggas i Maven.
Byggas med Java V.11.
Använda Hibernate för persistens.


- Funktioner -
Lägga till, ändra och ta bort spelare
Lägga till, ändra och ta bort lag
Lägga till, ändra och ta bort spel
Lägga till, ändra och ta bort matcher (player vs player)
Lägga till, ändra och ta bort matcher (lag vs lag)
Lägga till, ändra och ta bort personal (Pied Piper anställda)


- Övriga krav -
Profilsida?
*Lista spelare ska dom kunna välja att se spelare från ett, flera eller alla spel.
*Lista spelare ska det synas vilket lag spelaren ingår i, om hen ingår i ett lag.
*Vid registrering så ska spelaren ska dom registreras med förnamn, efternamn och 
nickname.
*När personalen hanterar lag ska dom kunna lägga till och ta bort befintliga spelarprofiler i 
laget. Ett lag är alltid knutet till ett visst spel och en spelare får bara ingå i ett lag.
*När personalen listar lag ska det synas vilket spel laget tävlar inom.
*När personalen listar matcher ska det synas vilka spelare/lag som tävlar mot varandra samt 
om matchen är kommande eller avgjord, om matchen är avgjord ska det gå att se vem som 
blev vinnare.
*Matchers ska alltid ha ett datum.
*Matchen ska räknas som avgjord om Pied Pipers personal gått in och registrerat resultatet.
*Matchens resultat ska registreras med en score för varje deltagare (spelare eller lag)
*När det gäller personal (användare) så behövs ingen inloggning med lösenord, samtliga 
användare som finns registrerade ska listas på startsidan där användare kan välja sig själv och 
komma in i systemet. Väl inne ska det gå att återgå till startskärmen för att välja ny 
användare.
*Alla personer (spelare/personal) i databasen ska ha följande uppgifter
förnamn
efternamn
nickname
gatuadress
postnummer
postort
land
e-mail


- Icke krav -

Att personalen kan
Visa, lägga till, ändra och ta bort turneringar (både players vs player och lag vs lag)
Visa, lägga till, ändra och ta bort ”stages” i turneringar
Visa, lägga till, ändra och ta bort matcher i ”stages”

Där i så fall
*En turnering ska bestå av ett antal steg (stages), som i sin tur består av ett antal matcher, det 
första steget innehåller matcher mellan alla tävlande spelarna/lagen. Det är alltid ett jämt 
antal spelare/lag i starten av en turnering. Vinnaren i varje match går vidare till nästa steg, 
detta behöver inte skötas per automatik, Pied Pipers personal ska bara kunna lägga till ett 
nästa steg och registrera matcher där mellan dom som vann matcherna i första steget och så 
vidare.
*Turneringar börjar alltid med 1 steg, steg 2 skapas när alla matcher i första steget är avgjorda 
och så vidare. När turneringar listas ska det visas vilket spel som turneringen avser samt i 
vilket steg turneringen är, d.v.s. det senast skapade där inte alla matcher är avgjorda.
