package main.second.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="job_id", referencedColumnName = "id")
    private JobTitle jobTitle;

    @ManyToOne
    @JoinColumn(name="store_id", referencedColumnName = "id")
    private Store store;

    public Employee(String name, JobTitle jobTitle, Store store) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.store = store;
    }

}
