package integration;

/* 
public class Kassa: Representerar en fysisk kassa som håller koll på försäljningssaldo.
*/

public class Kassa {
    private float saldo = 0;

    /**
     * Uppdaterar kassans saldo med det totala priset från en försäljning.
     * Metoden lägger till det totala priset på kassans aktuella saldo.
     * Om totalpriset är negativt, kastas ett IllegalArgumentException.
     * 
     * @param totalPris Det totala priset som ska läggas till kassans saldo
     * @throws IllegalArgumentException Om totalpris är negativt
     */
    public void uppdateraKassaSaldo(float totalPris) {
    
        if (totalPris < 0) {
            throw new IllegalArgumentException("Totalpris kan inte vara negativt.");
        }

      
        saldo += totalPris;
        System.out.println("Kassa uppdaterad. Nytt saldo: " + saldo);
    }

    /**
     * Hämtar det aktuella saldot på kassan.
     * 
     * @return Det aktuella saldot
     */
    public float getSaldo() {
       
        return saldo;
    }

    /**
     * Återställer kassans saldo till noll.
     * Denna metod kan användas för att återställa saldot för tester eller vid omstart.
     */
    public void återställSaldo() {
     
        saldo = 0;
        System.out.println("Kassan har återställts till noll.");
    }
}

//Hello world


