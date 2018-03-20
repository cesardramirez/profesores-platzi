package com.platzi.profesoresplatzi.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.platzi.profesoresplatzi.model.Course;

@Repository
@Transactional
public class CourseDAOImpl extends AbstractSession implements CourseDAO {
	
	@Override
	public void saveCourse(Course course) {
		getSession().persist(course);
	}

	@Override
	public void deleteCourseById(Long idCourse) {
		Course course = this.findById(idCourse);
		if (course != null) {
			getSession().delete(course);
		}
	}

	@Override
	public void updateCourse(Course course) {
		getSession().update(course);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findAllCourses() {
		return getSession().createQuery("from Course").list();
	}

	@Override
	public Course findById(Long idCourse) {
		return (Course) getSession().get(Course.class, idCourse);
	}

	@Override
	public Course findByName(String nameCourse) {
		return (Course) getSession()
				.createQuery("from Course where name = :n")
				.setParameter("n", nameCourse).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findByIdTeacher(Long idTeacher) {
		return (List<Course>) getSession()
				.createQuery("from Course c join c.teacher t where t.idTeacher = :idT")
				.setParameter("idT", idTeacher).list();
	}
}
