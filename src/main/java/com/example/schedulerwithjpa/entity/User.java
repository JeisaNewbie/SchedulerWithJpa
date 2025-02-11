package com.example.schedulerwithjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
* @Entity -> 기본생성자 필수, 없으면 컴파일 에러
* @Builder -> 생성자가 없을경우 AllArgsConstructor 를 생성하여 Builder code 를 작성하지만,
* @XArgsConstructor 가 있거나 다른 생성자가 작성된 경우 따로 생성하지 않음 -> 컴파일 에러
* 둘 중 하나만 사용할 경우 @XArgsConstructor 를 적용하지 않아도 괜찮다.
* @Entity 만 사용할 경우 생성자를 명시하지 않고 @XArgsConstructor 도 사용하지 않으면 컴파일러가 기본생성자를 생성
* @Builder 만 사용할 경우 Lombok 이 AllArgsConstructor 를 생성 하기 때문
*/

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long password;

    @Column(nullable = false, unique = true)
    private String email;

    public void updateUser(String username, Long newPassword) {
        this.username = username;
        this.password = newPassword;
    }

//    @OneToMany(mappedBy = "Schedule")
//    private List<Schedule> schedules;

}
