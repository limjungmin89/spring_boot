package com.ljm.spring_boot.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="user_name", length = 50, unique = true)
    private String userName;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "nick_name", length = 50)
    private String nickName;

    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @JoinTable(
        name="user_authority",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}
