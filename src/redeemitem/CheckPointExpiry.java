
package redeemitem;

import java.time.LocalDate;
import java.time.Period;

public class CheckPointExpiry {

    private int points;
    private LocalDate earnedDate;
    private LocalDate expiryDate;

    CheckPointExpiry(int points, LocalDate earnedDate, Period expiryPeriod) {
        this.points = points;
        this.earnedDate = earnedDate;
        this.expiryDate = earnedDate.plus(expiryPeriod);
    }
    
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public int getPoints() {
        return points;
    }

    public LocalDate getEarnedDate() {
        return earnedDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    
    /*
    point_expiry newpoint1 = new point_expiry(100, LocalDate.now(), Period.ofDays(10));
        if (newpoint1.isExpired()) {
            System.out.println("Points have expired.");
        } else {
            
        }
    */
}
