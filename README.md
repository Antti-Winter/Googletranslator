# Google Translation API -asiakasohjelma

Komentoriviohjelma, joka hyödyntää Google Cloud Translation API:a. Tämän työkalun avulla voit kääntää tekstiä eri kielten välillä käyttäen Googlen käännöspalveluita ja edistyneitä prompt engineering -ominaisuuksia.

## Ominaisuudet

- Tekstinkäännös Google Cloud Translation API:n avulla
- Lähde- ja kohdekielen määrittely
- Kontekstitietoinen kääntäminen prompt engineering -tekniikalla
- Tyyli- ja toimialakohtaisen terminologian hallinta
- Yksinkertainen komentorivityöliittymä

## Vaatimukset

- Java 17 tai uudempi
- Maven projektin kääntämiseen
- Google Cloud API -avain, jossa Translation API on käytössä

## Asennus

1. Kloonaa tämä repositorio:
   ```bash
   git clone https://github.com/yourusername/googletranslator.git
   cd googletranslator
   ```

2. Käännä sovellus Mavenilla:
   ```bash
   mvn clean package
   ```

3. Käynnistä sovellus:
   ```bash
   java -jar target/googletranslator.jar
   ```

## Miten hankkia Google Cloud API -avain

Sovelluksen käyttö vaatii Google Cloud API -avaimen, jossa Cloud Translation API on käytössä. Seuraa näitä ohjeita avaimen hankkimiseksi:

1. **Luo Google Cloud -tili**
   - Siirry [Google Cloud Consoleen](https://console.cloud.google.com/)
   - Rekisteröidy Google Cloud -tilille, jos sinulla ei ole vielä sellaista
   - Google tarjoaa ilmaisen tason ja aloituskrediittejä uusille käyttäjille

2. **Luo uusi projekti**
   - Google Cloud Consolessa, klikkaa projektin pudotusvalikkoa sivun yläosassa
   - Klikkaa "Uusi projekti"
   - Anna projektille nimi ja klikkaa "Luo"

3. **Ota käyttöön Cloud Translation API**
   - Siirry vasemmasta valikosta kohtaan "APIs & Services" > "Library"
   - Etsi "Cloud Translation API"
   - Valitse API ja klikkaa "Enable"

4. **Luo API-avain**
   - Siirry kohtaan "APIs & Services" > "Credentials"
   - Klikkaa "Create Credentials" ja valitse pudotusvalikosta "API key"
   - Uusi API-avaimesi näytetään; kopioi se sovellusta varten

5. **Rajoita API-avaimen käyttöä (suositeltavaa)**
   - Etsi avaimen tiedoista klikkaa "Edit"
   - Valitse "API restrictions" kohdasta "Restrict key"
   - Valitse pudotusvalikosta "Cloud Translation API"
   - Klikkaa "Save"

6. **Määritä laskutustiedot (tarvittaessa)**
   - Google Cloud saattaa vaatia laskutustietojen määrittämistä
   - Translation API:lla on ilmainen taso, joka sisältää tietyn määrän merkkejä kuukaudessa

## Käyttö

1. Käynnistä sovellus:
   ```bash
   java -jar target/googletranslator.jar
   ```

2. Syötä Google Cloud API -avain kehotteeseen.

3. Valitse käännösvaihtoehto valikosta:
   - Peruskäännös: Yksinkertainen tekstin kääntäminen kielten välillä
   - Edistynyt käännös Prompt Engineering -tekniikalla: Kontekstitietoinen käännös tyyli- ja terminologiavaihtoehdoilla

4. Seuraa kehotteita ja syötä:
   - Käännettävä teksti
   - Lähdekieli (tai jätä tyhjäksi automaattista tunnistusta varten)
   - Kohdekieli
   - Edistyneessä käännöksessä: konteksti, tyyli ja toimialatiedot

## Käännösten kielikoodit

Kun sinulta kysytään kielikoodeja, käytä ISO-639-1 -standardin mukaisia kaksikirjaimisia koodeja, kuten:
- `en` - englanti
- `fi` - suomi
- `fr` - ranska
- `es` - espanja
- `de` - saksa
- `ja` - japani
- `zh` - kiina
- `ru` - venäjä

## Käyttöesimerkit

### Peruskäännös

```
Google Translation API CLI
=========================
Syötä Google Cloud API -avain: [SINUN_API_AVAIN]

Valikko:
1. Peruskäännös
2. Edistynyt käännös Prompt Engineering -tekniikalla
3. Lopeta
Valintasi: 1

Syötä käännettävä teksti: Hello, how are you?
Syötä lähdekielen koodi (esim. 'en', 'fi', jätä tyhjäksi automaattiselle tunnistukselle): en
Syötä kohdekielen koodi (esim. 'en', 'fi'): fi

Käännöstulos:
Hei, miten voit?
```

### Edistynyt käännös Prompt Engineering -tekniikalla

```
Valikko:
1. Peruskäännös
2. Edistynyt käännös Prompt Engineering -tekniikalla
3. Lopeta
Valintasi: 2

Syötä käännettävä teksti: The patient should take the medication twice daily.
Syötä lähdekielen koodi (esim. 'en', 'fi', jätä tyhjäksi automaattiselle tunnistukselle): en
Syötä kohdekielen koodi (esim. 'en', 'fi'): fi
Syötä konteksti (esim. 'tekninen ohje', 'markkinointimateriaali', valinnainen): lääketieteellinen ohje
Syötä tyyli (esim. 'muodollinen', 'epämuodollinen', valinnainen): muodollinen
Syötä toimiala terminologiaa varten (esim. 'lääketiede', 'lakiala', valinnainen): terveydenhuolto

Käännöstulos:
Potilaan tulee ottaa lääke kahdesti päivässä.
```

## Vianmääritys

- **Virhe: Could not find or load main class** - Varmista, että suoritat JAR-tiedoston oikeasta hakemistosta tai määritä JAR-tiedoston täydellinen polku.
- **Translation failed: 400 Bad Request** - Tarkista, että API-avaimesi on voimassa ja Translation API on käytössä.
- **Translation failed: Unauthorized** - Varmista, että API-avaimellasi on tarvittavat käyttöoikeudet.


## Viitteet

- Google Cloud Translation API
- Vitecin työpajamateriaalit