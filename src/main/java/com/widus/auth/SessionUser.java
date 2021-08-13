package com.widus.auth;

import java.io.Serializable;

import com.widus.dto.user.User;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
	private static final long serialVersionUID = 4310157320602323689L;
	private String name;
	private String email;
	private String picture;

   public SessionUser(User user) {
       this.name = user.getName();
       this.email = user.getEmail();
       this.picture = user.getPicture();
   }
}