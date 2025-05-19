package model;

import java.util.List;
import java.util.ArrayList;

/**
 * {@code RevenueNotifier} hanterar observermönstret för att meddela intäktsändringar.
 * 
 * Denna klass håller reda på alla {@link RevenueObserver}-instanser som registrerats,
 * och notifierar dem när en ny försäljning har genomförts genom att skicka med den uppdaterade totalintäkten.
 */
public class RevenueNotifier {
    private final List<RevenueObserver> observers = new ArrayList<>();
    private double totalRevenue = 0;

    /**
     * Lägger till en observerare som kommer notifieras vid uppdatering av total intäkt.
     *
     * @param observer En instans som implementerar {@link RevenueObserver}-gränssnittet.
     */
    public void addObserver(RevenueObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifierar alla registrerade observerare om en uppdaterad totalintäkt.
     * Totalintäkten uppdateras med beloppet från den senaste försäljningen innan notifiering sker.
     *
     * @param revenueFromSale Intäkten från en enskild försäljning som ska läggas till den totala intäkten.
     */
    public void notifyObservers(double revenueFromSale) {
        totalRevenue += revenueFromSale;
        for (RevenueObserver observer : observers) {
            observer.newRevenue(totalRevenue);
        }
    }
}
