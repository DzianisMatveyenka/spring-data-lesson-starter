package com.matveyenka.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie", schema = "movie_storage")
public class Movie extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;
}
