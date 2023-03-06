package org.olegmell.domain;

import lombok.Getter;
import lombok.Setter;
import org.olegmell.domain.Types.CountryType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "country")
@Getter
@Setter
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "countryCode")
    @Enumerated(EnumType.STRING)
    private CountryType countryCode;

    //private String countryCode;

    private String name;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Set<Address> addresses;

    @Override
    public String toString() {
        return String.join(" ","country name: ",this.name);
    }


}
