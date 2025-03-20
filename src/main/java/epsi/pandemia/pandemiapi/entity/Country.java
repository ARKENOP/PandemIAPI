package epsi.pandemia.pandemiapi.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String iso3;
    private Integer code3;
    private Long population;
    private String whoRegion;

    @ManyToOne
    @JoinColumn(name = "continent_id")
    private Continent continent;

    @OneToMany(mappedBy = "country")
    private List<Region> regions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public Integer getCode3() {
        return code3;
    }

    public void setCode3(Integer code3) {
        this.code3 = code3;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public String getWhoRegion() {
        return whoRegion;
    }

    public void setWhoRegion(String whoRegion) {
        this.whoRegion = whoRegion;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }
}
