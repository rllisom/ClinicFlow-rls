package com.salesianos.triana.dam.clinicflowrls.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Paciente {


    @Id @GeneratedValue
    private Long id;

    private String nombre;
    private String email;
    private LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "paciente",fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Cita> citas = new HashSet<>();

    //Helpers
    public void addCita(Cita cita){
        citas.add(cita);
        cita.setPaciente(this);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Paciente paciente = (Paciente) o;
        return getId() != null && Objects.equals(getId(), paciente.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
