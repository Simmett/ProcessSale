package model.DTO;

public class ArtikelDTO {
    private String artikelID;
    private double artikelPris;
    private int VAT;
    private String artikelBeskrivning;
    private int antalAvArtikel;
    private String artikelNamn;

    public ArtikelDTO() {
        
    }


    public ArtikelDTO( String artikelID,
                       int antalAvArtikel) {
        this.artikelID = artikelID;
        this.antalAvArtikel = antalAvArtikel;
    }

    /**
     * Hämtar artikelns ID.
     * @return Artikelns ID
     */
    public String getartikelID() {
        return artikelID;
    }

    /**
     * Hämtar artikelns pris.
     * @return Artikelns pris
     */
    public double getartikelPris() {
        return artikelPris;
    }

    /**
     * Hämtar artikelns VAT (moms) procent.
     * @return Artikelns VAT
     */
    public int getVAT() {
        return VAT;
    }

    /**
     * Hämtar artikelns beskrivning.
     * @return Artikelns beskrivning
     */
    public String getartikelBeskrivning() {
        return artikelBeskrivning;
    }

    /**
     * Hämtar antal av artikeln i lager.
     * @return Antalet av artikeln
     */
    public int getantalAvArtikel() {
        return antalAvArtikel;
    }

    /**
     * Sätter artikelns ID.
     * @param artikelID Artikelns ID som ska sättas
     */
    public void setArtikelID(String artikelID) {
        this.artikelID = artikelID;
    }

    /**
     * Hämtar artikelns namn.
     * @return Artikelns namn
     */
    public String getArtikelNamn() {
        return artikelNamn;
    }

    /**
     * Sätter artikelns namn.
     * @param artikelNamn Artikelns namn som ska sättas
     */
    public void setArtikelNamn(String artikelNamn) {
        this.artikelNamn = artikelNamn;
    }

    /**
     * Sätter artikelns pris.
     * @param artikelPris Artikelns pris som ska sättas
     */
    public void setPris(double artikelPris) {
        this.artikelPris = artikelPris;
    }

    /**
     * Sätter artikelns VAT (moms) procent.
     * @param VAT Artikelns VAT som ska sättas
     */
    public void setVAT(int VAT) {
        this.VAT = VAT;
    }

    /**
     * Sätter artikelns beskrivning.
     * @param artikelBeskrivning Artikelns beskrivning som ska sättas
     */
    public void setBeskrivning(String artikelBeskrivning) {
        this.artikelBeskrivning = artikelBeskrivning;
    }

    /**
     * Sätter antal av artikeln i lager.
     * @param antalAvArtikel Antalet av artikeln som ska sättas
     */
    public void setAntal(int antalAvArtikel) {
        this.antalAvArtikel = antalAvArtikel;
    }

    /**
     * Ökar antalet av artikeln i lager med det angivna värdet.
     * Om det angivna värdet är negativt eller noll, görs ingen ändring.
     * @param extraAntal Antalet som ska läggas till artikeln
     */
    public void ökaAntal(int extraAntal) {
        if (extraAntal > 0) {
            this.antalAvArtikel += extraAntal;
        } else {
            System.out.println("Antalet som ska ökas kan inte vara negativt eller noll.");
        }
    }
}
