package com.platzi.profesoresplatzi.service;

import java.util.List;

import com.platzi.profesoresplatzi.model.Course;

public interface CourseService {
	
	/**
	 * Guarda la información de un Course.
	 * @param course
	 */
	void saveCourse(Course course);
	
	/**
	 * Con el id elimina un Course específico.
	 * @param idCourse
	 */
	void deleteCourseById(Long idCourse);
	
	/**
	 * Actualizar la información del Course.
	 * @param course
	 */
	void updateCourse(Course course);
	
	/**
	 * Devuelve un listado de todos los Course.
	 * @return List of Course
	 */
	List<Course> findAllCourses();
	
	/**
	 * Devuelve un Course con base a su id.
	 * @param idCourse
	 * @return Course
	 */
	Course findById(Long idCourse);
	
	/**
	 * Devuelve un Course con base a su nombre.
	 * @param nameCourse
	 * @return Course
	 */
	Course findByName(String nameCourse);
	
	/**
	 * Devuelve un listado de Course según el id del Teacher.
	 * @param idTeacher
	 * @return List of Course
	 */
	List<Course> findByIdTeacher(Long idTeacher);
}
