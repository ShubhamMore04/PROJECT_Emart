package com.example.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CategoryMaster")
public class CategoryMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Catmaster_id")
    private int catMasterId;

    @Column(name = "Cat_id", nullable = false)
    private String catId;

    @Column(name = "SubCat_id")
    private String subCatId;

    @Column(name = "Cat_Name")
    private String catName;

    @Column(name = "Cat_Image_Path")
    private String catImagePath;

    @Column(name = "flag")
    private Boolean flag;
    
    
    
    @OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="catMasterId")
	private List<ProductMaster> product;




   

    public int getCatMasterId() {
        return catMasterId;
    }

    public void setCatMasterId(int catMasterId) {
        this.catMasterId = catMasterId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(String subCatId) {
        this.subCatId = subCatId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatImagePath() {
        return catImagePath;
    }

    public void setCatImagePath(String catImagePath) {
        this.catImagePath = catImagePath;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}