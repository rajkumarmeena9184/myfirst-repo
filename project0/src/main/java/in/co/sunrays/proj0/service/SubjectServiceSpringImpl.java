package in.co.sunrays.proj0.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.SubjectDAOInt;
import in.co.sunrays.proj0.dto.CourseDTO;
import in.co.sunrays.proj0.dto.SubjectDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;
/**
 * Session facade of Student Service. It is transactional, apply delcarative
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
@Service("subjectService")
public class SubjectServiceSpringImpl implements SubjectServiceInt {
	Logger log = Logger.getLogger(SubjectServiceSpringImpl.class);
	@Autowired
	private SubjectDAOInt subjectDao = null;
	@Autowired
	private CourseServiceInt courseService = null;
	/**
	 * Adds a Subject 
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(SubjectDTO dto) throws DuplicateRecordException {
		log.debug("SubjectServiceSpringImpl method  add Start");
		long pk = 0;

		try {
			SubjectDTO existDTO = subjectDao.findByName(dto.getSubjectName());
			if (existDTO != null) {
				throw new DuplicateRecordException("Subject Alredy Exist");
			}
			CourseDTO cdto = null;
			try {
				cdto = (CourseDTO) courseService.findByPK(dto.getCourseId());
			} catch (DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dto.setCourseName(cdto.getName());
		} catch (DataBaseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		pk = subjectDao.add(dto);
		log.debug("SubjectServiceSpringImpl method  add End");
		return pk;
	}
	/**
	 * Update a Subject 
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(SubjectDTO dto) throws DuplicateRecordException {
		log.debug("SubjectServiceSpringImpl method  update Start");
		CourseDTO cdto = null;
		try {
			cdto = (CourseDTO) courseService.findByPK(dto.getCourseId());
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dto.setCourseName(cdto.getName());
		subjectDao.update(dto);
		log.debug("SubjectServiceSpringImpl method  update End");
	}
	/**
	 * Delete a Subject 
	 *
	 * @param id
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {
		log.debug("SubjectServiceSpringImpl method  Delete Start");
		subjectDao.delete(id);
		log.debug("SubjectServiceSpringImpl method  Delete End");
	}
	/**
	 * findByName a Subject 
	 *
	 * @param name
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public SubjectDTO findByName(String name) throws DataBaseException {
		log.debug("SubjectServiceSpringImpl method  findByName Start");
		SubjectDTO dto = subjectDao.findByName(name);
		log.debug("SubjectServiceSpringImpl method  findByName End");
		return dto;
	}

	public SubjectDTO findByLogin(String login) throws DataBaseException {
		return null;
	}
	/**
	 * findByPk a Subject 
	 *
	 * @param pk
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public SubjectDTO findByPK(long pk) throws DataBaseException {
		log.debug("SubjectServiceSpringImpl method  findByPk Start");
		SubjectDTO dto = subjectDao.findByPk(pk);
		log.debug("SubjectServiceSpringImpl method  findByPk End");
		return dto;
	}
	/**
	 * findByName a Subject 
	 *
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public List search(SubjectDTO dto, int pageNo, int pageSize) throws DataBaseException {
		log.debug("SubjectServiceSpringImpl method  Search with pagination Start");
		List list = null;
		list = subjectDao.search(dto, pageNo, pageSize);
		log.debug("SubjectServiceSpringImpl method  Search with pagination End");
		return list;
	}
	/**
	 * findByName a Subject 
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public List search(SubjectDTO dto) throws DataBaseException {
		log.debug("SubjectServiceSpringImpl method  Search Start");
		List list = null;
		list = subjectDao.search(dto);
		log.debug("SubjectServiceSpringImpl method  Search End");
		return list;
	}

}
