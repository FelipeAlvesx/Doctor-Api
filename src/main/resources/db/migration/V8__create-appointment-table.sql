create table appointment(

    id bigint not null auto_increment,
    doctors_id bigint not null,
    patients_id bigint not null,
    date datetime not null,

    primary key(id),
    constraint fk_doctors_appointment_id foreign key(doctors_id) references doctors(id),
    constraint fk_patients_appointment_id foreign key(patients_id) references patients(id)

);