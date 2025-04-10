package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Table(name="goods")
@Entity
public class Good {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_id_gen")
    @SequenceGenerator(name="goods_id_gen",sequenceName = "goods_id_seq",allocationSize = 1)
    @Id
    Integer id;

    @Column(name="name")
    String Name;

    @Column(name ="price")
    Integer Price;

    @Column(name="amount")
    Integer Amount;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="group_id")
    GoodGroup goodGroup;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    Integer goodGroupId;

    public Integer getGoodGroupId() {
        return goodGroupId;
    }

    public void setGoodGroupId(Integer goodGroupId) {
        this.goodGroupId = goodGroupId;
    }

    public GoodGroup getGoodGroup() {
        return goodGroup;
    }

    public void setGoodGroup(GoodGroup goodGroup) {
        this.goodGroup = goodGroup;
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

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", Name='" + Name + '\'' +
                ", Price=" + Price +
                ", Amount=" + Amount;
    }
}
