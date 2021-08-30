package co.com.challenge.challenge.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "SatelliteEntity")
@Table(name = "satellite_data")
public class SatelliteEntity {

    @Id
    @Column(name = "satellite_name")
    private String satelliteName;

    @Column(name = "x_position")
    private float x_position;

    @Column(name = "y_position")
    private float y_position;
}



