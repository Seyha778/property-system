package com.apextech.property.senghang.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "provinces", schema = "property")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvincesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "province_id", nullable = false)
    private String provinceId;

    @Column(name = "name_km")
    private String nameKm;

    @Column(name = "name_en")
    private String nameEn;

}