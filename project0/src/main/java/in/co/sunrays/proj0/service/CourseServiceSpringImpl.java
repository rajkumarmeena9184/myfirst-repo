package in.co.sunrays.proj0.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.CourseDAOInt;
import in.co.sunrays.proj0.dto.CourseDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

/**
 * Session facade of Course Service. It is transactional, apply delcarative
 * transactions with help of Spring AOP.
 *
 * If unchecked exception is propagated from a method then transaction will be
 * rolled back.
 *
 * Default propogation value is Propagation.REQUIRED and readOnly = false
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Service("courseService")
public class CourseServiceSpringImpl implements CourseServiceInt {
	Logger log = Logger.getLogger(CourseServiceSpringImpl.class);
	@Autowired
	private CourseDAOInt courseDao = null;

	/**
	 * Adds a Course
	 *
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(CourseDTO dto) throws DuplicateRecordException {
		log.debug("CourseServiceSpringImpl method  add Start");
		long pk = 0;

		CourseDTO dtoExist = null;
		dtoExist = courseDao.findByName(dto.getName());
		if (dtoExist != null) {
			throw new DuplicateRecordException("College Already Exist");
		}

		pk = courseDao.add(dto);
		log.debug("CourseServiceSpringImpl method  add End");
		return pk;
	}

	/**
	 * Updates a Course
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException : throws when updated Course is already
	 *                                  exists
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(CourseDTO dto) throws DuplicateRecordException {
		log.debug("CourseServiceSpringImpl method  Update Start");
		courseDao.update(dto);
		log.debug("CourseServiceSpringImpl method  Update End");
	}

	/**
	 * Deletes a Course
	 *
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {
		log.debug("CourseServiceSpringImpl method  Delete Start");
		courseDao.delete(id);
		log.debug("CourseServiceSpringImpl method  Delete End");
	}

	/**
	 * Finds Course by Name
	 *
	 * @param name : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	@Transactional(readOnly = true)
	public CourseDTO findByName(String name) throws DataBaseException {
		log.debug("CourseServiceSpringImpl method  findByName Start");
		CourseDTO dto = null;
		dto = courseDao.findByName(name);
		log.debug("CourseServiceSpringImpl method  findByName End");
		return dto;
	}

	/**
	 * Finds record by Primary Key
	 */
	@Transactional(readOnly = true)
	public CourseDTO findByPK(long pk) throws DataBaseException {
		log.debug("CourseServiceSpringImpl method  findByPk Start");
		CourseDTO dto = null;
		dto = courseDao.findByPk(pk);
		log.debug("CourseServiceSpringImpl method  findByPk End");
		return dto;
	}

	/**
	 * Searches Course with pagination
	 *
	 * @return list : List of Course
	 * @param dto      : Search Parameters
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */
	@Transactional(readOnly = true)
	public List search(CourseDTO dto, int pageNo, int pageSize) throws DataBaseException {
		log.debug("CourseServiceSpringImpl method  search Start");
		List list = courseDao.search(dto, pageNo, pageSize);
		log.debug("CourseServiceSpringImpl method  search End");
		return list;
	}

	/**
	 * Searches Course
	 *
	 * @return list : List of Course
	 * @param dto : Search Parameters
	 * @throws ApplicationException
	 */
	@Transactional(readOnly = true)
	public List search(CourseDTO dto) throws DataBaseException {
		log.debug("CourseServiceSpringImpl method  search Start");
		List list = null;
		list = courseDao.search(dto);
		log.debug("CourseServiceSpringImpl method  search End");
		return list;
	}

}
