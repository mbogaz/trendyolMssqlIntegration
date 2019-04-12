package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClCard {
    String firstName;
    String lastName;
    String email;
    String taxNumber;
    String tckn;
    String address1;
    String address2;
    String city;
    String district;
    long date;
    Integer logicalRef;

    public boolean equals(ClCard clCard) {
        return this.firstName.equals(clCard.getFirstName())
                && this.lastName.equals(clCard.getLastName())
                && this.address1.equals(clCard.getAddress1())
                && this.address2.equals(clCard.getAddress2());
    }
}
