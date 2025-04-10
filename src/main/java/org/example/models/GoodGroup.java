package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="goods_groups")
public class GoodGroup {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_group_id_gen")
    @SequenceGenerator(name="goods_group_id_gen",sequenceName = "goods_group_id_seq",allocationSize = 1)
    @Id
    Integer id;

    @Column(name="name")
    String Name;

    @Column(name="food")
    Integer Food;

    @JsonIgnore
    @OneToMany(mappedBy = "goodGroup",cascade = CascadeType.REMOVE)
    List<Good> goods;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="depot_id")
    Depot depot;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    Integer depotId;

    public Integer getDepotId() {
        return depotId;
    }

    public void setDepotId(Integer depotId) {
        this.depotId = depotId;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getFood() {
        return Food;
    }

    public void setFood(Integer food) {
        Food = food;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", Name='" + Name + '\'' +
                ", Food=" + Food;
    }
}
