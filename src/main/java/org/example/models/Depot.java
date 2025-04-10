package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Table(name="depot")
@Entity
public class Depot {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "depot_id_gen")
    @SequenceGenerator(name="depot_id_gen",sequenceName = "depot_id_seq",allocationSize = 1)
    @Id
    Integer id;
    @Column(name="name")
    String Name;
    @Column(name="manager")
    String Manager;
    @Column(name="surface")
    Integer Surface;

    @JsonIgnore
    @OneToMany(mappedBy = "depot",cascade = CascadeType.REMOVE)
    List<GoodGroup> goodsGroups=new ArrayList<>();

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<GoodGroup> getGoodsGroups() {
        return goodsGroups;
    }

    public void setGoodsGroups(List<GoodGroup> goodsGroups) {
        this.goodsGroups = goodsGroups;
    }

    public String getManager() {
        return Manager;
    }

    public void setManager(String manager) {
        Manager = manager;
    }

    public Integer getSurface() {
        return Surface;
    }

    public void setSurface(Integer surface) {
        Surface = surface;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", Name='" + Name + '\'' +
                ", Manager='" + Manager + '\'' +
                ", Surface=" + Surface ;
    }
}