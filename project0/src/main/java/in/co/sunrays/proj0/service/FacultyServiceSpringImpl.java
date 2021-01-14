package in.co.sunrays.proj0.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.FacultyDAOInt;
import in.co.sunrays.proj0.dto.FacultyDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

/**
 * Session facade of Faculty Service. It is transactional, apply delcarative
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
@Service("facultyService")
public class FacultyServiceSpringImpl implements FacultyServiceInt {
	Logger log = Logger.getLogger(FacultyServiceSpringImpl.class);
	@Autowired
	private FacultyDAOInt facultyDao = null;

	/**
	 * Adds a Faculty
	 *
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(FacultyDTO dto) throws DuplicateRecordException {
		log.debug("FacultyServiceSpringImpl method add Start");
		try {
			FacultyDTO dtoExist = facultyDao.findByLogin(dto.getEmailID());
			if (dtoExist != null) {
				throw new DuplicateRecordException("Faculty Already Exist");
			}
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		long pk = facultyDao.add(dto);
		log.debug("FacultyServiceSpringImpl method add End");
		return pk;
	}
	/**
	 * Update a Faculty
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(FacultyDTO dto) throws DuplicateRecordException {
		log.debug("FacultyServiceSpringImpl method Update Start");
		facultyDao.update(dto);
		log.debug("FacultyServiceSpringImpl method Update End");
	}
	/**
	 * Delete a Faculty
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {
		log.debug("FacultyServiceSpringImpl method Delete Start");
		facultyDao.delete(id);
		log.debug("FacultyServiceSpringImpl method Delete End");
	}
	/**
	 * FindByLogin a Faculty
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public FacultyDTO findByLogin(String login) throws DataBaseException {
		log.debug("FacultyServiceSpringImpl method findByLogin Start");
		FacultyDTO dto = null;
		dto = facultyDao.findByLogin(login);
		log.debug("FacultyServiceSpringImpl method findByLogin End");
		return dto;
	}
	/**
	 * FindByPk a Faculty
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public FacultyDTO findByPK(long pk) throws DataBaseException {
		log.debug("FacultyServiceSpringImpl method findBypk Start");
		FacultyDTO dto = null;
		dto = facultyDao.findByPk(pk);
		log.debug("FacultyServiceSpringImpl method findBypk End");
		return dto;
	}
	/**
	 * Search a Faculty
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public List search(FacultyDTO dto, int pageNo, int pageSize) throws DataBaseException {
		log.debug("FacultyServiceSpringImpl method with pagination Search Start");
		List list = null;
		list = facultyDao.search(dto, pageNo, pageSize);
		log.debug("FacultyServiceSpringImpl method with pagination Search End");
		return list;
	}
	/**
	 * Search a Faculty
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public List search(FacultyDTO dto) throws DataBaseException {
		log.debug("FacultyServiceSpringImpl method Search Start");
		List list = null;
		list = facultyDao.search(dto);
		log.debug("FacultyServiceSpringImpl method Search End");
		return list;
	}

}
