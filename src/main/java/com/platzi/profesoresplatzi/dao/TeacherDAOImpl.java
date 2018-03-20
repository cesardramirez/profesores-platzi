package com.platzi.profesoresplatzi.dao;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.platzi.profesoresplatzi.model.Teacher;
import com.platzi.profesoresplatzi.model.TeacherSocialMedia;

@Repository
@Transactional
public class TeacherDAOImpl extends AbstractSession implements TeacherDAO {

	@Override
	public void saveTeacher(Teacher teacher) {
		getSession().persist(teacher);		
	}

	@Override
	public void deleteTeacherById(Long idTeacher) {
		Teacher teacher = this.findById(idTeacher);
		if (teacher != null) {
			Iterator<TeacherSocialMedia> iter = teacher.getTeacherSocialMedias().iterator();
			while (iter.hasNext()) {
				TeacherSocialMedia teacherSocialMedia = iter.next();
				iter.remove();
				getSession().delete(teacherSocialMedia);
			}
			
			teacher.getTeacherSocialMedias().clear();
			getSession().delete(teacher);
		}		
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		getSession().update(teacher);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> findAllTeachers() {
		return getSession().createQuery("from Teacher").list();
	}

	@Override
	public Teacher findById(Long idTeacher) {
		return (Teacher) getSession().get(Teacher.class, idTeacher);
	}

	@Override
	public Teacher findByName(String nameTeacher) {
		return (Teacher) getSession()
				.createQuery("from Teacher where name = :n")
				.setParameter("n", nameTeacher).uniqueResult();
	}
}
