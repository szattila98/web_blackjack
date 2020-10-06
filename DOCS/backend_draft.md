## API vázlat:

- POST /api/user (új játékos létrehozása)
    - input:
        játékos neve
    - output:
        játékos id
        játékos neve
        
- GET /api/game (függőben lévő játékok lekérdezése)
    - input:
        játékos id
    - output: 
        függőben lévő játékok (amikbe csak 1 user lépett be, és nem részese a megadott játékos)

- GET /api/game/{gameId} (adott játék adatainak lekérdezése)
    - output:
        adott játék adatai (1. játékos id-ja, neve, lapjai, pontszáma; 2. játékos id-ja, neve, lapjai, pontszáma)
        
- POST /api/game (új játék létrehozása)
    - input:
        játékos id
    - output:
        adott játék adatai (1. játékos id-ja, neve, lapjai, pontszáma; 2. játékos null)
        
- POST /api/game/{gameId}/user/{userId}/join (adott játékba adott user belépne)
    - output:
        adott játék adatai (1. játékos id-ja, neve, lapjai, pontszáma; 2. játékos id-ja, neve, lapjai, pontszáma)
        
- POST /api/game/{gameId}/user/{userId}/pull (adott játékban adott user lapot kér)
    - output:
        400 Bad Request, ha nem az adott játékos következik
        kártya szín, szám

- POST /api/game/{gameId}/user/{userId}/stop (adott játékban adott user megáll)
    - output:
        200 OK, 400 Bad Request
        
        
## Modell vázlat:

- User
    - id
    - name
    
- Card:
    - number 
    - rank: KING, QUEEN, JACK, NUMBER
    - color: CLUBS, SPADES, HEARTHS, DIAMONDS

- Player
    - user: User
    - cards: Card[]
    - points: int
    - state: IN_GAME, STOPPED
   
- Game:
    - id
    - player1: Player
    - player2: Player
    - state (PENDING, IN_PROGRESS, CLOSED)
    
## Fontosabb business logic
Megállásnál/húzásnál vizsgálni kell azt, hogy ki léphet:

Vizsgálni kell azt, hogy a megadott user részese-e az adott játéknak
- Ha nem, akkor hibával térünk vissza.

Megnézzük, hogy ki az, aki megállt:

- Ha ez 1 játékos, akkor a másik léphet.

- Ha ez 2 játékos, akkor a játéknak már vége van.

- Ha ez 0 játékos, akkor az léphet, akinek kevesebb kártyája van.


Ha az aktuális kérést küldő user éppen nem léphet, hibaüzenetet kell neki visszaküldeni 400-as HTTP hibakód mellett!
