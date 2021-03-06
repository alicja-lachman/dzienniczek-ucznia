package model;
// Generated 2017-01-06 01:06:25 by Hibernate Tools 4.3.1


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
 * Uczniowie generated by hbm2java
 */
@Entity
@Table(name="uczniowie"
    ,catalog="io"
)
public class Uczniowie  implements java.io.Serializable {


     private Integer idu;
     private Klasy klasy;
     private Uzytkownicy uzytkownicy;
     private Set<Obecnosci> obecnoscis = new HashSet<Obecnosci>(0);
     private Set<Oceny> ocenies = new HashSet<Oceny>(0);
     private Set<Uwaga> uwagas = new HashSet<Uwaga>(0);

    public Uczniowie() {
    }

	
    public Uczniowie(Klasy klasy, Uzytkownicy uzytkownicy) {
        this.klasy = klasy;
        this.uzytkownicy = uzytkownicy;
    }
    public Uczniowie(Klasy klasy, Uzytkownicy uzytkownicy, Set<Obecnosci> obecnoscis, Set<Oceny> ocenies, Set<Uwaga> uwagas) {
       this.klasy = klasy;
       this.uzytkownicy = uzytkownicy;
       this.obecnoscis = obecnoscis;
       this.ocenies = ocenies;
       this.uwagas = uwagas;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idu", unique=true, nullable=false)
    public Integer getIdu() {
        return this.idu;
    }
    
    public void setIdu(Integer idu) {
        this.idu = idu;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Klasy_idk", nullable=false)
    public Klasy getKlasy() {
        return this.klasy;
    }
    
    public void setKlasy(Klasy klasy) {
        this.klasy = klasy;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Uzytkownicy_iduz", nullable=false)
    public Uzytkownicy getUzytkownicy() {
        return this.uzytkownicy;
    }
    
    public void setUzytkownicy(Uzytkownicy uzytkownicy) {
        this.uzytkownicy = uzytkownicy;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="uczniowie")
    public Set<Obecnosci> getObecnoscis() {
        return this.obecnoscis;
    }
    
    public void setObecnoscis(Set<Obecnosci> obecnoscis) {
        this.obecnoscis = obecnoscis;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="uczniowie")
    public Set<Oceny> getOcenies() {
        return this.ocenies;
    }
    
    public void setOcenies(Set<Oceny> ocenies) {
        this.ocenies = ocenies;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="uczniowie")
    public Set<Uwaga> getUwagas() {
        return this.uwagas;
    }
    
    public void setUwagas(Set<Uwaga> uwagas) {
        this.uwagas = uwagas;
    }




}


