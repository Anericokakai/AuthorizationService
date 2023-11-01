package com.auth.authorizationserver.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")

public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int Id;
    private  String  name;

}
