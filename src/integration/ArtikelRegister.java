package integration;

import model.DTO.ArtikelDTO;

/**
 * ArtikelRegister ansvarar för att tillhandahålla information om artiklar 
 * baserat på deras ID. Det fungerar som ett hårdkodat register över artiklar 
 * i systemet.
 */
public class ArtikelRegister {
    
    /**
     * Hämtar artikelinformation baserat på artikelID och antal.
     * Metoden kollar artikelID och skapar ett ArtikelDTO-objekt med relevant information.
     * Om artikelID inte finns, kastas ett IllegalArgumentException.
     * 
     * @param artikelID Artikelns unika ID
     * @param antalAvArtikel Antalet av den specifika artikeln
     * @return ArtikelDTO-objekt som innehåller all relevant artikelinformation
     * @throws IllegalArgumentException Om artikelID inte finns i det fördefinierade urvalet
     */
    public ArtikelDTO hämtaArtikelInformation(String artikelID, int antalAvArtikel) {
        ArtikelDTO artikel = new ArtikelDTO();

        switch (artikelID) {
            case "abc123":
                artikel.setArtikelID(artikelID);
                artikel.setArtikelNamn("BigWheel Oatmeal");
                artikel.setPris(29.90);
                artikel.setVAT(6);
                artikel.setBeskrivning("BigWheel Oatmeal 500 g, whole grain oats, high fiber, gluten free");
                artikel.setAntal(antalAvArtikel);
                break;
            case "def456":
                artikel.setArtikelID(artikelID);
                artikel.setArtikelNamn("YouGoGo Blueberry");
                artikel.setPris(14.90);
                artikel.setVAT(6);
                artikel.setBeskrivning("YouGoGo Blueberry 240 g, low sugar yoghurt, blueberry flavour");
                artikel.setAntal(antalAvArtikel);
                break;
            default:
                throw new IllegalArgumentException("ArtikelID finns inte: " + artikelID);
        }

        return artikel;
    }
}
