package tn.esprit.workmood.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String emailAddress;
	private String name;
	private String firstName;
	@Temporal(TemporalType.DATE)
	private Date subDate;
	private String passwd;
	private String confirmPasswd;
	private boolean enabled;
	
	@ManyToMany(mappedBy="users",cascade = CascadeType.ALL , fetch= FetchType.EAGER)
	@JsonIgnore
	private Set<Role> roles;
	
	
	

}
