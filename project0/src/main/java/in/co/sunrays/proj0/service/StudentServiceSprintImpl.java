package in.co.sunrays.proj0.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.StudentDAOInt;
import in.co.sunrays.proj0.dto.CollegeDTO;
import in.co.sunrays.proj0.dto.StudentDTO;
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
@Service("studentService")
public class StudentServiceSprintImpl implements StudentServiceInt {
	Logger log = Logger.getLogger(StudentServiceSprintImpl.class);
	@Autowired
	private StudentDAOInt studentDao = null;

	@Autowired
	private CollegeServiceInt collegeservice = null;
	/**
	 * Adds a Student
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(StudentDTO dto) throws DuplicateRecordException {
		log.debug("StudentServiceSprintImpl method  add start");
		long pk = 0;

		try {
			StudentDTO existDTO = studentDao.findByLogin(dto.getEmailId());
			if (existDTO != null) {
				throw new DuplicateRecordException("StudentEmail  Already Exist");
			}
		} catch (DataBaseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		CollegeDTO cdto = null;
		try {
			cdto = (CollegeDTO) collegeservice.findByPK(dto.getCollegeId());
			dto.setCollegeName(cdto.getName());
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pk = studentDao.add(dto);
		log.debug("StudentServiceSprintImpl method  add End");
		return pk;
	}
	/**
	 * Update a Student
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(StudentDTO dto) throws DuplicateRecordException {
		log.debug("StudentServiceSprintImpl method  Update start");
		CollegeDTO cdto = null;
		try {
			cdto = (CollegeDTO) collegeservice.findByPK(dto.getCollegeId());
			dto.setCollegeName(cdto.getName());
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		studentDao.update(dto);
		log.debug("StudentServiceSprintImpl method  Update End");
	}
	/**
	 * Delete a Student
	 *
	 * @param id
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {
		log.debug("StudentServiceSprintImpl method  Delete start");
		studentDao.delete(id);
		log.debug("StudentServiceSprintImpl method  Delete End");
	}
	/**
	 * findByLogin a Student
	 *
	 * @param login
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public StudentDTO findByLogin(String login) throws DataBaseException {
		log.debug("StudentServiceSprintImpl method  findByLogin start");
		StudentDTO dto = null;
		dto = studentDao.findByLogin(login);
		log.debug("StudentServiceSprintImpl method  findByLogin End");
		return dto;
	}
	/**
	 * findByPK a Student
	 *
	 * @param pk
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public StudentDTO findByPK(long pk) throws DataBaseException {
		log.debug("StudentServiceSprintImpl method  findByPK start");
		StudentDTO dto = null;
		dto = studentDao.findByPk(pk);
		log.debug("StudentServiceSprintImpl method  findByPK End");
		return dto;
	}
	/**
	 * Search with pagination a Student
	 *
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public List search(StudentDTO dto, int pageNo, int pageSize) throws DataBaseException {
		log.debug("StudentServiceSprintImpl method  search with pagination start");
		List list = null;
		list = studentDao.search(dto, pageNo, pageSize);
		log.debug("StudentServiceSprintImpl method  search with pagination End");
		return list;
	}
	/**
	 * Search with pagination a Student
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public List search(StudentDTO dto) throws DataBaseException {
		log.debug("StudentServiceSprintImpl method  search start");
		List list = null;
		list = studentDao.search(dto);
		log.debug("StudentServiceSprintImpl method  search end");
		return list;
	}

}
