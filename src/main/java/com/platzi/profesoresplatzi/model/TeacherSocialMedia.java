package com.platzi.profesoresplatzi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @JsonIgnore en Teacher. Se coloca ya que en el atributo teacherSocialMedias de la clase Teacher tiene FetchType.EAGER, esto quiere decir que
 *   cuando se pide un Teacher este traerá todos sus teacherSocialMedias relacionados, pero como en el atributo teacher de la clase TeacherSocialMedia también tiene un FetchType.EAGER 
 *   pedirá nuevamente los teacherSocialMedias creará un bucle infinito, se agrega el @JsonIgnore para no hacerle get a esta propiedad.
 *   
 *   @JsonIgnore no se le agrega al atributo socialMedia de la clase TeacherSocialMedia porque el atributo teacherSocialMedias de la clase SocialMedia ya tiene el @JsonIgnore cortando el bucle infinito.
 *   
 *   Conclusión: TeacherSocialMedia trae los SocialMedia relacionados, pero SocialMedia no puede traer los TeacherSocialMedia gracias al @JsonIgonre.
 * @author desarrollo
 */
@Entity
@Table(name="teacher_social_media")
public class TeacherSocialMedia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_teacher_social_media")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idTeacherSocialMedia;
	
	@Column(name="nickname")
	private String nickname;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_teacher")
	@JsonIgnore  // Se coloca para poder asignar un socialMedia a un Teacher específico.
	private Teacher teacher;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_social_media")
	private SocialMedia socialMedia;
	
	public TeacherSocialMedia() {
		super();
	}
	
	public TeacherSocialMedia(Teacher teacher, SocialMedia socialMedia, String nickname) {
		super();
		this.teacher = teacher;
		this.socialMedia = socialMedia;
		this.nickname = nickname;
	}

	public Long getIdTeacherSocialMedia() {
		return idTeacherSocialMedia;
	}
	public void setIdTeacherSocialMedia(Long idTeacherSocialMedia) {
		this.idTeacherSocialMedia = idTeacherSocialMedia;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public SocialMedia getSocialMedia() {
		return socialMedia;
	}
	public void setSocialMedia(SocialMedia socialMedia) {
		this.socialMedia = socialMedia;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}