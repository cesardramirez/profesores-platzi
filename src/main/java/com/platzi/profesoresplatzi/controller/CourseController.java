package com.platzi.profesoresplatzi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.platzi.profesoresplatzi.model.Course;
import com.platzi.profesoresplatzi.service.CourseService;
import com.platzi.profesoresplatzi.util.CustomErrorType;

@Controller
@RequestMapping("/v1")
public class CourseController {
	
	@Autowired
	private CourseService _courseService;
	
	// GET
	@RequestMapping(value = "/courses", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Course>> getCourses(@RequestParam(value="name", required=false) String name, @RequestParam(value="id_teacher", required=false) Long id_teacher) {
		List<Course> courses = new ArrayList<Course>();
		
		if (id_teacher != null) {
			courses = _courseService.findByIdTeacher(id_teacher);
			if (courses.isEmpty()) {
				return new ResponseEntity<List<Course>>(HttpStatus.NO_CONTENT);
			}
		}
		
		if (name != null) {
			Course course = _courseService.findByName(name);
			if (course == null) {
				return new ResponseEntity<List<Course>>(HttpStatus.NOT_FOUND);
			}
			courses.add(course);
		}
		
		if (name == null && id_teacher == null) {
			courses = _courseService.findAllCourses();
			if (courses.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
		}
		
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
	
	// GET Id
	@RequestMapping(value = "/courses/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Course> getCourseById(@PathVariable("id") Long idCourse) {
		Course course = _courseService.findById(idCourse);
		
		if (course == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}
	
	// POST
	@RequestMapping(value = "/courses", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> createCourse(@RequestBody Course course, UriComponentsBuilder uriComponentsBuilder) {
		if (_courseService.findByName(course.getName()) != null) {
			// logger.error("Unable to create. A User with name {} already exist", user.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A course with name " + course.getName() + " already exist."), HttpStatus.CONFLICT);
		}
		
		_courseService.saveCourse(course);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				uriComponentsBuilder.path("/v1/courses/{id}")
				.buildAndExpand(course.getIdCourse())
				.toUri());
		
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	// UPDATE
	@RequestMapping(value = "/courses/{id}", method = RequestMethod.PATCH, headers = "Accept=application/json")
	public ResponseEntity<Course> updateCourse(@PathVariable("id") Long id, @RequestBody Course course) {
		Course currentCourse = _courseService.findById(id);
		if (currentCourse == null) {
			return new ResponseEntity(new CustomErrorType("Unable to update. Course with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		
		currentCourse.setName(course.getName());
		currentCourse.setThemes(course.getThemes());
		currentCourse.setProject(course.getProject());
		
		_courseService.updateCourse(currentCourse);
		
		return new ResponseEntity<Course>(currentCourse, HttpStatus.OK);
	}
	
	// DELETE
	@RequestMapping(value = "/courses/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id) {
		Course course = _courseService.findById(id);
		if (course == null) {
			return new ResponseEntity(new CustomErrorType("Unable to delete. Course with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		
		_courseService.deleteCourseById(id);
		
		return new ResponseEntity<Course>(HttpStatus.OK);
	}
}
