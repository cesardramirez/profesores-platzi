package com.platzi.profesoresplatzi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzi.profesoresplatzi.dao.TeacherDAO;
import com.platzi.profesoresplatzi.model.Teacher;

@Service("teacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherDAO _teacherDAO;

	@Override
	public void saveTeacher(Teacher teacher) {
		_teacherDAO.saveTeacher(teacher);
	}

	@Override
	public void deleteTeacherById(Long idTeacher) {
		_teacherDAO.deleteTeacherById(idTeacher);
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		_teacherDAO.updateTeacher(teacher);
	}

	@Override
	public List<Teacher> findAllTeachers() {
		return _teacherDAO.findAllTeachers();
	}

	@Override
	public Teacher findById(Long idTeacher) {
		return _teacherDAO.findById(idTeacher);
	}

	@Override
	public Teacher findByName(String nameTeacher) {
		return _teacherDAO.findByName(nameTeacher);
	}
}
