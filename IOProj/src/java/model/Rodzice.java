package model;
// Generated 2017-01-06 01:06:25 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Rodzice generated by hbm2java
 */
@Entity
@Table(name="rodzice"
    ,catalog="io"
)
public class Rodzice  implements java.io.Serializable {


     private Integer idr;
     private Uzytkownicy uzytkownicy;

    public Rodzice() {
    }

    public Rodzice(Uzytkownicy uzytkownicy) {
       this.uzytkownicy = uzytkownicy;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idr", unique=true, nullable=false)
    public Integer getIdr() {
        return this.idr;
    }
    
    public void setIdr(Integer idr) {
        this.idr = idr;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Uzytkownicy_iduz", nullable=false)
    public Uzytkownicy getUzytkownicy() {
        return this.uzytkownicy;
    }
    
    public void setUzytkownicy(Uzytkownicy uzytkownicy) {
        this.uzytkownicy = uzytkownicy;
    }




}

