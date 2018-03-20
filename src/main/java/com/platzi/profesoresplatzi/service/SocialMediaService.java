package com.platzi.profesoresplatzi.service;

import java.util.List;

import com.platzi.profesoresplatzi.model.SocialMedia;
import com.platzi.profesoresplatzi.model.TeacherSocialMedia;

public interface SocialMediaService {
	
	/**
	 * Guardar la información de un socialMedia.
	 * @param socialMedia
	 */
	void saveSocialMedia(SocialMedia socialMedia);
	
	/**
	 * Con el id elimina un SocialMedia específico.
	 * @param idSocialMedia
	 */
	void deleteSocialMediaById(Long idSocialMedia);
	
	/**
	 * Actualizar la información del SocialMedia.
	 * @param socialMedia
	 */
	void updateSocialMedia(SocialMedia socialMedia);
	
	/**
	 * Devuelve un listado de todos los SocialMedia.
	 * @return SocialMedia
	 */
	List<SocialMedia> findAllSocialMedias();
	
	/**
	 * Devuelve un SocialMedia con base a su id.
	 * @param idSocialMedia
	 * @return SocialMedia
	 */
	SocialMedia findById(Long idSocialMedia);
	
	/**
	 * Devuelve un SocialMedia con base a su name.
	 * @param nameSocialMedia
	 * @return SocialMedia
	 */
	SocialMedia findByName(String nameSocialMedia);
	
	/**
	 * Encuentra un SocialMedia con base al id y su nickname.
	 * @param idSocialMedia
	 * @return TeacherSocialMedia
	 */
	TeacherSocialMedia findSocialMediaByIdAndName(Long idSocialMedia, String nickname);
	
	/**
	 * Encuentra un SocialMedia con base al id del teacher y el id del socialMedia.
	 * @param idTeacher
	 * @param idSocialMedia
	 * @return TeacherSocialMedia
	 */
	TeacherSocialMedia findSocialMediaByIdTeacherAndIdSocialMedia(Long idTeacher, Long idSocialMedia);
}
