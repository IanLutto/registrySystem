package com.lutomiah.registry.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int client_id;
    private String firstName;
    private String lastName;
    private String emailId;
    private int age;
    private Long national_Id;
    private String gender;
    private String place_of_birth;
    private String marital_status;
    private String employment_status;
    private String address;
    private String reason_for_admission;

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", age=" + age +
                ", national_Id=" + national_Id +
                ", gender='" + gender + '\'' +
                ", place_of_birth='" + place_of_birth + '\'' +
                ", marital_status='" + marital_status + '\'' +
                ", employment_status='" + employment_status + '\'' +
                ", address='" + address + '\'' +
                ", reason_for_admission='" + reason_for_admission + '\'' +
                '}';
    }
}
