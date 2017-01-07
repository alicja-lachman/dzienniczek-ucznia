package entity;
// Generated 2017-01-06 14:50:13 by Hibernate Tools 4.3.1


import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Klasy generated by hbm2java
 */
@Entity
@Table(name="klasy"
    ,catalog="io"
)
public class Klasy  implements java.io.Serializable {


     private Integer idk;
     private Nauczyciele nauczyciele;
     private String nazwa;
     private Date rocznik;
     private Set<Uczniowie> uczniowies = new HashSet<Uczniowie>(0);
     private Set<Lekcje> lekcjes = new HashSet<Lekcje>(0);

    public Klasy() {
    }

	
    public Klasy(Nauczyciele nauczyciele, String nazwa, Date rocznik) {
        this.nauczyciele = nauczyciele;
        this.nazwa = nazwa;
        this.rocznik = rocznik;
    }
    public Klasy(Nauczyciele nauczyciele, String nazwa, Date rocznik, Set<Uczniowie> uczniowies, Set<Lekcje> lekcjes) {
       this.nauczyciele = nauczyciele;
       this.nazwa = nazwa;
       this.rocznik = rocznik;
       this.uczniowies = uczniowies;
       this.lekcjes = lekcjes;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idk", unique=true, nullable=false)
    public Integer getIdk() {
        return this.idk;
    }
    
    public void setIdk(Integer idk) {
        this.idk = idk;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Nauczyciele_idn", nullable=false)
    public Nauczyciele getNauczyciele() {
        return this.nauczyciele;
    }
    
    public void setNauczyciele(Nauczyciele nauczyciele) {
        this.nauczyciele = nauczyciele;
    }

    
    @Column(name="Nazwa", nullable=false, length=45)
    public String getNazwa() {
        return this.nazwa;
    }
    
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Rocznik", nullable=false, length=19)
    public Date getRocznik() {
        return this.rocznik;
    }
    
    public void setRocznik(Date rocznik) {
        this.rocznik = rocznik;
    }


@OneToMany(fetch=FetchType.LAZY, mappedBy="klasy")
    public Set<Uczniowie> getUczniowies() {
        return this.uczniowies;
    }
    
    public void setUczniowies(Set<Uczniowie> uczniowies) {
        this.uczniowies = uczniowies;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="klasy")
    public Set<Lekcje> getLekcjes() {
        return this.lekcjes;
    }
    
    public void setLekcjes(Set<Lekcje> lekcjes) {
        this.lekcjes = lekcjes;
    }




}


