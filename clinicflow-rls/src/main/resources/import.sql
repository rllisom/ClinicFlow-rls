-- Profesionales
INSERT INTO profesional (id, nombre, especialidad) VALUES (1, 'Dr. Juan Pérez', 'Cardiología');
INSERT INTO profesional (id, nombre, especialidad) VALUES (2, 'Dra. Ana López', 'Dermatología');
INSERT INTO profesional (id, nombre, especialidad) VALUES (3, 'Dr. Carlos Ruiz', 'Pediatría');

-- Pacientes
INSERT INTO paciente (id, nombre, email, fecha_nacimiento) VALUES (1, 'María García', 'maria.garcia@email.com', '1985-04-12');
INSERT INTO paciente (id, nombre, email, fecha_nacimiento) VALUES (2, 'Pedro Sánchez', 'pedro.sanchez@email.com', '1990-09-23');
INSERT INTO paciente (id, nombre, email, fecha_nacimiento) VALUES (3, 'Lucía Fernández', 'lucia.fernandez@email.com', '1978-12-05');
INSERT INTO paciente (id, nombre, email, fecha_nacimiento) VALUES (4, 'Javier Torres', 'javier.torres@email.com', '2000-07-15');
INSERT INTO paciente (id, nombre, email, fecha_nacimiento) VALUES (5, 'Elena Martín', 'elena.martin@email.com', '1995-02-28');

-- Consultas
INSERT INTO consulta (id, observaciones, diagnostico, fecha) VALUES (1, 'Revisión anual', 'Saludable', '2026-01-10T10:00:00');
INSERT INTO consulta (id, observaciones, diagnostico, fecha) VALUES (2, 'Erupción cutánea', 'Dermatitis', '2026-01-11T11:30:00');
INSERT INTO consulta (id, observaciones, diagnostico, fecha) VALUES (3, 'Dolor torácico', 'Angina', '2026-01-12T09:00:00');
INSERT INTO consulta (id, observaciones, diagnostico, fecha) VALUES (4, 'Control pediátrico', 'Normal', '2026-01-13T12:00:00');

-- Citas
INSERT INTO cita (id, fecha_hora, estado, paciente_id, profesional_id, consulta_id) VALUES (1, '2026-01-10T10:00:00', 'ATENDIDA', 1, 1, 1);
INSERT INTO cita (id, fecha_hora, estado, paciente_id, profesional_id, consulta_id) VALUES (2, '2026-01-11T11:30:00', 'ATENDIDA', 2, 2, 2);
INSERT INTO cita (id, fecha_hora, estado, paciente_id, profesional_id, consulta_id) VALUES (3, '2026-01-12T09:00:00', 'ATENDIDA', 3, 1, 3);
INSERT INTO cita (id, fecha_hora, estado, paciente_id, profesional_id, consulta_id) VALUES (4, '2026-01-13T12:00:00', 'ATENDIDA', 4, 3, 4);
INSERT INTO cita (id, fecha_hora, estado, paciente_id, profesional_id, consulta_id) VALUES (5, '2026-01-14T08:30:00', 'PROGRAMADA', 5, 2, NULL);
INSERT INTO cita (id, fecha_hora, estado, paciente_id, profesional_id, consulta_id) VALUES (6, '2026-01-14T09:30:00', 'PROGRAMADA', 1, 3, NULL);
