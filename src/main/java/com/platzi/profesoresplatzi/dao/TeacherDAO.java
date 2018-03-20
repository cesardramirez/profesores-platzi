package com.platzi.profesoresplatzi.dao;

import java.util.List;

import com.platzi.profesoresplatzi.model.Teacher;

public interface TeacherDAO {
	
	/**
	 * Guardar la información de un Teacher.
	 * @param teacher
	 */
	void saveTeacher(Teacher teacher);
	
	/**
	 * Con el id elimina un Teacher específico.
	 * @param idTeacher
	 */
	void deleteTeacherById(Long idTeacher);
	
	/**
	 * Actualizar la información del Teacher.
	 * @param teacher
	 */
	void updateTeacher(Teacher teacher);
	
	 /**
	  * Devuelve un listado de todos los Teacher.
	  * @return List of Teacher
	  */
	List<Teacher> findAllTeachers();
	
	/**
	 * Devuelve un Teacher con base a su id.
	 * @param idTeacher
	 * @return Teacher
	 */
	Teacher findById(Long idTeacher);
	
	/**
	 * Devuelve un Teacher con base a su nombre.
	 * @param nameTeacher
	 * @return Teacher
	 */
	Teacher findByName(String nameTeacher);
}
