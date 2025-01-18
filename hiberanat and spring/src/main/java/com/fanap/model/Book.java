package com.fanap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String mainTitle;
    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private Author author;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Version
    private int version;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", mainTitle='" + mainTitle + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", version=" + version +
                '}';
    }
}
