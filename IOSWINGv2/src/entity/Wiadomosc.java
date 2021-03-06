package entity;
// Generated 2017-01-06 14:50:13 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Wiadomosc generated by hbm2java
 */
@Entity
@Table(name="wiadomosc"
    ,catalog="io"
)
public class Wiadomosc  implements java.io.Serializable {


     private Integer idwiad;
     private Uzytkownicy uzytkownicyByIdOdbiorca;
     private Uzytkownicy uzytkownicyByIdNadawca;
     private Date data;

    public Wiadomosc() {
    }

    public Wiadomosc(Uzytkownicy uzytkownicyByIdOdbiorca, Uzytkownicy uzytkownicyByIdNadawca, Date data) {
       this.uzytkownicyByIdOdbiorca = uzytkownicyByIdOdbiorca;
       this.uzytkownicyByIdNadawca = uzytkownicyByIdNadawca;
       this.data = data;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idwiad", unique=true, nullable=false)
    public Integer getIdwiad() {
        return this.idwiad;
    }
    
    public void setIdwiad(Integer idwiad) {
        this.idwiad = idwiad;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_odbiorca", nullable=false)
    public Uzytkownicy getUzytkownicyByIdOdbiorca() {
        return this.uzytkownicyByIdOdbiorca;
    }
    
    public void setUzytkownicyByIdOdbiorca(Uzytkownicy uzytkownicyByIdOdbiorca) {
        this.uzytkownicyByIdOdbiorca = uzytkownicyByIdOdbiorca;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_nadawca", nullable=false)
    public Uzytkownicy getUzytkownicyByIdNadawca() {
        return this.uzytkownicyByIdNadawca;
    }
    
    public void setUzytkownicyByIdNadawca(Uzytkownicy uzytkownicyByIdNadawca) {
        this.uzytkownicyByIdNadawca = uzytkownicyByIdNadawca;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Data", nullable=false, length=19)
    public Date getData() {
        return this.data;
    }
    
    public void setData(Date data) {
        this.data = data;
    }




}


