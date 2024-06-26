package dev.mvc.ai_sort;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "AI_SORT")
@Getter @Setter @ToString
public class AISort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "aisort_seq")
    @SequenceGenerator(name="aisort_seq", sequenceName="AISORT_SEQ", allocationSize=1)
    private int sortno;

    @Column(nullable = false, length = 3000)
    private String name;

    // Getters and Setters
    public void AISort(){
    }
}