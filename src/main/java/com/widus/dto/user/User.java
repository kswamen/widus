package com.widus.dto.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.widus.dto.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

   @Id
   private String email;

   @Column(nullable = false)
   private String name;

   @Column
   private String picture;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private UserRole role;

   @Builder
   public User(String name, String email, String picture, UserRole role) {
       this.name = name;
       this.email = email;
       this.picture = picture;
       this.role = role;
   }

   public User update(String name, String picture) {
       this.name = name;
       this.picture = picture;
       
       return this;
   }

   public String getRoleKey() {
       return this.role.getKey();
   }
}