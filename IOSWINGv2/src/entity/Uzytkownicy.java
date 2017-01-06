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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Uzytkownicy generated by hbm2java
 */
@Entity
@Table(name="uzytkownicy"
    ,catalog="io"
    , uniqueConstraints = @UniqueConstraint(columnNames="Pesel") 
)
public class Uzytkownicy  implements java.io.Serializable {


     private Integer iduz;
     private String imie;
     private String nazwisko;
     private String pesel;
     private String adres;
     private Set<Wiadomosc> wiadomoscsForIdOdbiorca = new HashSet<Wiadomosc>(0);
     private Set<Wiadomosc> wiadomoscsForIdNadawca = new HashSet<Wiadomosc>(0);
     private Set<Dyrektorzy> dyrektorzies = new HashSet<Dyrektorzy>(0);
     private Set<Nauczyciele> nauczycieles = new HashSet<Nauczyciele>(0);
     private Set<Uczniowie> uczniowies = new HashSet<Uczniowie>(0);
     private Set<Rodzice> rodzices = new HashSet<Rodzice>(0);

    public Uzytkownicy() {
    }

	
    public Uzytkownicy(String imie, String nazwisko, String pesel, String adres) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.adres = adres;
    }
    public Uzytkownicy(String imie, String nazwisko, String pesel, String adres, Set<Wiadomosc> wiadomoscsForIdOdbiorca, Set<Wiadomosc> wiadomoscsForIdNadawca, Set<Dyrektorzy> dyrektorzies, Set<Nauczyciele> nauczycieles, Set<Uczniowie> uczniowies, Set<Rodzice> rodzices) {
       this.imie = imie;
       this.nazwisko = nazwisko;
       this.pesel = pesel;
       this.adres = adres;
       this.wiadomoscsForIdOdbiorca = wiadomoscsForIdOdbiorca;
       this.wiadomoscsForIdNadawca = wiadomoscsForIdNadawca;
       this.dyrektorzies = dyrektorzies;
       this.nauczycieles = nauczycieles;
       this.uczniowies = uczniowies;
       this.rodzices = rodzices;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="iduz", unique=true, nullable=false)
    public Integer getIduz() {
        return this.iduz;
    }
    
    public void setIduz(Integer iduz) {
        this.iduz = iduz;
    }

    
    @Column(name="Imie", nullable=false, length=45)
    public String getImie() {
        return this.imie;
    }
    
    public void setImie(String imie) {
        this.imie = imie;
    }

    
    @Column(name="Nazwisko", nullable=false, length=45)
    public String getNazwisko() {
        return this.nazwisko;
    }
    
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    
    @Column(name="Pesel", unique=true, nullable=false, length=45)
    public String getPesel() {
        return this.pesel;
    }
    
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    
    @Column(name="Adres", nullable=false, length=45)
    public String getAdres() {
        return this.adres;
    }
    
    public void setAdres(String adres) {
        this.adres = adres;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="uzytkownicyByIdOdbiorca")
    public Set<Wiadomosc> getWiadomoscsForIdOdbiorca() {
        return this.wiadomoscsForIdOdbiorca;
    }
    
    public void setWiadomoscsForIdOdbiorca(Set<Wiadomosc> wiadomoscsForIdOdbiorca) {
        this.wiadomoscsForIdOdbiorca = wiadomoscsForIdOdbiorca;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="uzytkownicyByIdNadawca")
    public Set<Wiadomosc> getWiadomoscsForIdNadawca() {
        return this.wiadomoscsForIdNadawca;
    }
    
    public void setWiadomoscsForIdNadawca(Set<Wiadomosc> wiadomoscsForIdNadawca) {
        this.wiadomoscsForIdNadawca = wiadomoscsForIdNadawca;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="uzytkownicy")
    public Set<Dyrektorzy> getDyrektorzies() {
        return this.dyrektorzies;
    }
    
    public void setDyrektorzies(Set<Dyrektorzy> dyrektorzies) {
        this.dyrektorzies = dyrektorzies;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="uzytkownicy")
    public Set<Nauczyciele> getNauczycieles() {
        return this.nauczycieles;
    }
    
    public void setNauczycieles(Set<Nauczyciele> nauczycieles) {
        this.nauczycieles = nauczycieles;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="uzytkownicy")
    public Set<Uczniowie> getUczniowies() {
        return this.uczniowies;
    }
    
    public void setUczniowies(Set<Uczniowie> uczniowies) {
        this.uczniowies = uczniowies;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="uzytkownicy")
    public Set<Rodzice> getRodzices() {
        return this.rodzices;
    }
    
    public void setRodzices(Set<Rodzice> rodzices) {
        this.rodzices = rodzices;
    }




}


