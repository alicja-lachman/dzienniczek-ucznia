package entity;
// Generated 2017-01-06 14:50:13 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Nauczyciele generated by hbm2java
 */
@Entity
@Table(name="nauczyciele"
    ,catalog="io"
)
public class Nauczyciele  implements java.io.Serializable {


     private Integer idn;
     private Uzytkownicy uzytkownicy;
     private Set<Oceny> ocenies = new HashSet<Oceny>(0);
     private Set<Klasy> klasies = new HashSet<Klasy>(0);
     private Set<Obecnosci> obecnoscis = new HashSet<Obecnosci>(0);
     private Set<Przedmioty> przedmioties = new HashSet<Przedmioty>(0);
     private Set<Wydarzenie> wydarzenies = new HashSet<Wydarzenie>(0);
     private Set<Uwaga> uwagas = new HashSet<Uwaga>(0);

    public Nauczyciele() {
    }

	
    public Nauczyciele(Uzytkownicy uzytkownicy) {
        this.uzytkownicy = uzytkownicy;
    }
    public Nauczyciele(Uzytkownicy uzytkownicy, Set<Oceny> ocenies, Set<Klasy> klasies, Set<Obecnosci> obecnoscis, Set<Przedmioty> przedmioties, Set<Wydarzenie> wydarzenies, Set<Uwaga> uwagas) {
       this.uzytkownicy = uzytkownicy;
       this.ocenies = ocenies;
       this.klasies = klasies;
       this.obecnoscis = obecnoscis;
       this.przedmioties = przedmioties;
       this.wydarzenies = wydarzenies;
       this.uwagas = uwagas;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idn", unique=true, nullable=false)
    public Integer getIdn() {
        return this.idn;
    }
    
    public void setIdn(Integer idn) {
        this.idn = idn;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Uzytkownicy_iduz", nullable=false)
    public Uzytkownicy getUzytkownicy() {
        return this.uzytkownicy;
    }
    
    public void setUzytkownicy(Uzytkownicy uzytkownicy) {
        this.uzytkownicy = uzytkownicy;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="nauczyciele")
    public Set<Oceny> getOcenies() {
        return this.ocenies;
    }
    
    public void setOcenies(Set<Oceny> ocenies) {
        this.ocenies = ocenies;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="nauczyciele")
    public Set<Klasy> getKlasies() {
        return this.klasies;
    }
    
    public void setKlasies(Set<Klasy> klasies) {
        this.klasies = klasies;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="nauczyciele")
    public Set<Obecnosci> getObecnoscis() {
        return this.obecnoscis;
    }
    
    public void setObecnoscis(Set<Obecnosci> obecnoscis) {
        this.obecnoscis = obecnoscis;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="nauczyciele")
    public Set<Przedmioty> getPrzedmioties() {
        return this.przedmioties;
    }
    
    public void setPrzedmioties(Set<Przedmioty> przedmioties) {
        this.przedmioties = przedmioties;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="nauczyciele")
    public Set<Wydarzenie> getWydarzenies() {
        return this.wydarzenies;
    }
    
    public void setWydarzenies(Set<Wydarzenie> wydarzenies) {
        this.wydarzenies = wydarzenies;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="nauczyciele")
    public Set<Uwaga> getUwagas() {
        return this.uwagas;
    }
    
    public void setUwagas(Set<Uwaga> uwagas) {
        this.uwagas = uwagas;
    }




}


