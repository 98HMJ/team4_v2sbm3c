package dev.mvc.ai_history;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

import dev.mvc.ai_sort.AISort;

@Entity
@Table(name = "AI_HISTORY")
@Getter @Setter @ToString
public class AIHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "aihistory_seq")
  @SequenceGenerator(name="aihistory_seq", sequenceName="AIHISTORY_SEQ", allocationSize=1)
  private int historyno;

  @Column(nullable = false, length = 3000)
  private String explaination;

  @Column(nullable = false)
  private Date rdate;
  
  @Column(nullable = false)
  private Integer memberno;
  
  // Mapping to the AI_SORT table
  @ManyToOne
  @JoinColumn(name = "sortno", nullable = false)
  private AISort aiSort;

  public void AISort() {
  }
}