package yoot.project_demo.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.sql.results.graph.Fetch;
import yoot.project_demo.domain.AuditableEntity;
import yoot.project_demo.domain.enums.Gender;
import yoot.project_demo.domain.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Table(name = "students")
@Entity
@Data
public class Student extends AuditableEntity {
    @Column(name = "student_code", columnDefinition = "varchar(20)", nullable = false, unique = true)
    private String studentCode;

    @Column(name = "full_name", columnDefinition = "varchar(100)", nullable = false)
    private String fullName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender = Gender.OTHER;

    @Column(name = "grade_level", columnDefinition = "varchar(30)")
    private String gradeLevel;

    @Column(name = "school_name", columnDefinition = "varchar(100)")
    private String schoolName;

    @Column(columnDefinition = "varchar(20)")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;

    @Column(name = "latest_score", precision = 5, scale = 2)
    private BigDecimal latestScore = BigDecimal.ZERO;

    @Column(length = 255)
    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "fk_students_parent"))
    private Parent parent;
}
