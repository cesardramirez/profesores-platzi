package com.platzi.profesoresplatzi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzi.profesoresplatzi.dao.SocialMediaDAO;
import com.platzi.profesoresplatzi.model.SocialMedia;
import com.platzi.profesoresplatzi.model.TeacherSocialMedia;

@Service("socialMediaService")
@Transactional
public class SocialMediaServiceImpl implements SocialMediaService {
	
	@Autowired
	private SocialMediaDAO _socialMediaServiceDAO;

	@Override
	public void saveSocialMedia(SocialMedia socialMedia) {
		_socialMediaServiceDAO.saveSocialMedia(socialMedia);
	}

	@Override
	public void deleteSocialMediaById(Long idSocialMedia) {
		_socialMediaServiceDAO.deleteSocialMediaById(idSocialMedia);
	}

	@Override
	public void updateSocialMedia(SocialMedia socialMedia) {
		_socialMediaServiceDAO.updateSocialMedia(socialMedia);
	}

	@Override
	public List<SocialMedia> findAllSocialMedias() {
		return _socialMediaServiceDAO.findAllSocialMedias();
	}

	@Override
	public SocialMedia findById(Long idSocialMedia) {
		return _socialMediaServiceDAO.findById(idSocialMedia);
	}

	@Override
	public SocialMedia findByName(String nameSocialMedia) {
		return _socialMediaServiceDAO.findByName(nameSocialMedia);
	}

	@Override
	public TeacherSocialMedia findSocialMediaByIdAndName(Long idSocialMedia, String nickname) {
		return _socialMediaServiceDAO.findSocialMediaByIdAndName(idSocialMedia, nickname);
	}

	@Override
	public TeacherSocialMedia findSocialMediaByIdTeacherAndIdSocialMedia(Long idTeacher, Long idSocialMedia) {
		return _socialMediaServiceDAO.findSocialMediaByIdTeacherAndIdSocialMedia(idTeacher, idSocialMedia);
	}
}
