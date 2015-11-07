package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by Tomislav S. Mitic on 11/2/15.
 */
@Entity
public class Address extends Model {

    @Id
    public Long id;

    @OneToOne(mappedBy = "address")
    public Warehouse warehouse;

    public String street;

    public String number;

    public String postalCode;

    public String city;

    public String country;
}
