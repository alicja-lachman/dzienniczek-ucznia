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
 * Lekcje generated by hbm2java
 */
@Entity
@Table(name="lekcje"
    ,catalog="io"
)
public class Lekcje  implements java.io.Serializable {


     private Integer idl;
     private Klasy klasy;
     private Plan plan;
     private Przedmioty przedmioty;
     private Set<Obecnosci> obecnoscis = new HashSet<Obecnosci>(0);
     private Set<Sala> salas = new HashSet<Sala>(0);

    public Lekcje() {
    }

	
    public Lekcje(Klasy klasy, Plan plan, Przedmioty przedmioty) {
        this.klasy = klasy;
        this.plan = plan;
        this.przedmioty = przedmioty;
    }
    public Lekcje(Klasy klasy, Plan plan, Przedmioty przedmioty, Set<Obecnosci> obecnoscis, Set<Sala> salas) {
       this.klasy = klasy;
       this.plan = plan;
       this.przedmioty = przedmioty;
       this.obecnoscis = obecnoscis;
       this.salas = salas;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idl", unique=true, nullable=false)
    public Integer getIdl() {
        return this.idl;
    }
    
    public void setIdl(Integer idl) {
        this.idl = idl;
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
    @JoinColumn(name="Plan_idp", nullable=false)
    public Plan getPlan() {
        return this.plan;
    }
    
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Przedmioty_idprz", nullable=false)
    public Przedmioty getPrzedmioty() {
        return this.przedmioty;
    }
    
    public void setPrzedmioty(Przedmioty przedmioty) {
        this.przedmioty = przedmioty;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="lekcje")
    public Set<Obecnosci> getObecnoscis() {
        return this.obecnoscis;
    }
    
    public void setObecnoscis(Set<Obecnosci> obecnoscis) {
        this.obecnoscis = obecnoscis;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="lekcje")
    public Set<Sala> getSalas() {
        return this.salas;
    }
    
    public void setSalas(Set<Sala> salas) {
        this.salas = salas;
    }




}


