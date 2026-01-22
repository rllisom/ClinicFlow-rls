package com.salesianos.triana.dam.clinicflowrls.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.jpa.domain.PredicateSpecification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cita {

    @Id @GeneratedValue
    private Long id;

    private LocalDateTime fechaHora;
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id",
    foreignKey = @ForeignKey(name = "fk_cita_paciente"))
    private Paciente paciente;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "profesional_id",
            foreignKey = @ForeignKey(name = "fk_cita_profesional"))
    private Profesional profesional;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_id",
    foreignKey = @ForeignKey(name = "fk_cita_consulta"))
    @Builder.Default
    private Consulta consulta = new Consulta();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Cita cita = (Cita) o;
        return getId() != null && Objects.equals(getId(), cita.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public class CitaSpecs {
        public static PredicateSpecification<Cita> mayorEdad(LocalDate fechaFiltro){
            return ((from, criteriaBuilder) ->
                    criteriaBuilder.greaterThan(from.join("paciente").get("fechaNacimiento"),fechaFiltro)
            );
        }

    }
}
