package com.platzi.profesoresplatzi.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.platzi.profesoresplatzi.model.SocialMedia;
import com.platzi.profesoresplatzi.model.TeacherSocialMedia;

@Repository
@Transactional
public class SocialMediaDAOImpl extends AbstractSession implements SocialMediaDAO {

	@Override
	public void saveSocialMedia(SocialMedia socialMedia) {
		getSession().persist(socialMedia);
	}

	@Override
	public void deleteSocialMediaById(Long idSocialMedia) {
		SocialMedia socialMedia = this.findById(idSocialMedia);
		if (socialMedia != null) {
			getSession().delete(socialMedia);
		}
	}

	@Override
	public void updateSocialMedia(SocialMedia socialMedia) {
		getSession().update(socialMedia);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SocialMedia> findAllSocialMedias() {
		return getSession().createQuery("from SocialMedia").list();
	}

	@Override
	public SocialMedia findById(Long idSocialMedia) {
		return (SocialMedia) getSession().get(SocialMedia.class, idSocialMedia);
	}

	@Override
	public SocialMedia findByName(String nameSocialMedia) {
		return (SocialMedia) getSession()
				.createQuery("from SocialMedia where name = :n")
				.setParameter("n", nameSocialMedia).uniqueResult();
	}

	@Override
	public TeacherSocialMedia findSocialMediaByIdAndName(Long idSocialMedia, String nickname) {
		@SuppressWarnings("unchecked")
		List<Object[]> objects = getSession()
				.createQuery("from TeacherSocialMedia tsm join tsm.socialMedia sm where sm.idSocialMedia = :idSM and tsm.nickname = :nickn")
				.setParameter("idSM", idSocialMedia)
				.setParameter("nickn", nickname).list();
		
		if (objects.size() > 0) {
			for (Object[] obj : objects) {
				for (Object ob : obj) {
					if (ob instanceof TeacherSocialMedia) {
						return (TeacherSocialMedia) ob;
					}
				}
			}
		}
		
		return null;
	}

	@Override
	public TeacherSocialMedia findSocialMediaByIdTeacherAndIdSocialMedia(Long idTeacher, Long idSocialMedia) {
		@SuppressWarnings("unchecked")
		List<Object[]> objects = getSession()
				.createQuery("from TeacherSocialMedia tsm join tsm.socialMedia sm join tsm.teacher t where sm.idSocialMedia = :idSM and t.idTeacher = :idT")
				.setParameter("idSM", idSocialMedia)
				.setParameter("idT", idTeacher).list();
		
		if (objects.size() > 0) {
			for (Object[] obj : objects) {
				for (Object ob : obj) {
					if (ob instanceof TeacherSocialMedia) {
						return (TeacherSocialMedia) ob;
						
					}
				}
			}
		}
		
		return null;
	}	
}
