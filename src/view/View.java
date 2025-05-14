package view;

import java.time.LocalTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import model.DTO.Kvitto;
import model.DTO.ArtikelDTO;

public class View {
    
    private Scanner scanner = new Scanner(System.in);
    public View() {
    }

    /**
     * Skriver ut kvittoinformation på konsolen.
     * Den här metoden kan modifieras för att skriva ut till andra enheter, 
     * exempelvis en fysisk skrivare om det behövs i framtiden.
     * 
     * @param kvitto Kvittoobjekt som innehåller försäljningsinformation.
     * @throws IllegalArgumentException Om kvitto är null.
     */
    public void skrivUtKvitto(Kvitto kvitto) {
        
        if (kvitto == null) {
            throw new IllegalArgumentException("Kvitto kan inte vara null.");
        }

       
        System.out.println("----- KVITTO -----");
        System.out.println("Tid: " + kvitto.gettidFörsäljning());  
        System.out.println("Datum: " + kvitto.getdatum());           
        System.out.println("Artiklar: " + kvitto.getlistaAvArtiklar()); 
        System.out.println("Totalpris: " + kvitto.gettotalPris() + " SEK"); 
        System.out.println("Rabatt: -" + kvitto.getrabatt() + " kr");  
        System.out.println("Moms: " + kvitto.gettotalVat() + " SEK");   
        System.out.println("Betalat: " + kvitto.getbetalatBelopp() + " SEK"); 
        System.out.println("Växel: " + kvitto.getväxel() + " SEK");     
        System.out.println("------------------");
    }

    public void startaFörsäljning(LocalTime tid){
        
        System.out.println("Försäljning Startad");

        System.out.println("Kontroller: tidFörsäljning = " + tid);
    }
    
    
     /**
     * Hanterar inmatning av artiklar och deras antal från användaren.
     * Fortsätter tills användaren skriver 'sluta'.
     * @param scanner Scanner för att läsa användarens inmatning
     */
    public List<ArtikelDTO> artikelInformation(){
        int antalAvArtikel = 0;
        String artikelID;
        List<ArtikelDTO> artikelLista = new ArrayList<>();


        while (true) {
            System.out.print("Ange artikel ID (eller 'sluta' för att avsluta): ");
            artikelID = scanner.nextLine();
            if (artikelID.equalsIgnoreCase("sluta")) {
                break; 
            }

            System.out.print("Ange antal av artikeln: ");
            antalAvArtikel = scanner.nextInt();
            scanner.nextLine(); 
            artikelLista.add(new ArtikelDTO(artikelID, antalAvArtikel));
        }
        return artikelLista;
       
}
    public int kundID(){
        System.out.print("Ange kund ID för rabatt (eller 0 om ingen): ");
        int kundID = scanner.nextInt();
        
        return kundID;
    }

    public float betalatBelopp(){
        System.out.print("Ange betalat belopp: ");
        float betalatBelopp = scanner.nextFloat();
        return betalatBelopp;
    }

    public void skrivUtVäxel(float växel){
        System.out.println("Växel att ge tillbaka: " + växel);
    }


    public void avslutaFörsäljning(){
        System.out.println("Försäljning avslutad.");
    }

    public void ingenRabatt(){
        System.out.println("Ingen rabatt tillämpad.");  
    }

    public void skrivUtRabatt(float rabatt){
        System.out.println("Rabatt tillämpad: -" + rabatt + " kr");
    }

}
