package in.co.sunrays.proj0.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.CollegeDAOInt;
import in.co.sunrays.proj0.dto.CollegeDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

/**
 * Session facade of College Service. It is transactional, apply delcarative
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
@Service(value = "collegeService")
public class CollegeServiceSpringImpl implements CollegeServiceInt {
	Logger log = Logger.getLogger(CollegeServiceSpringImpl.class);
	@Autowired
	private CollegeDAOInt collegeDao = null;

	/**
	 * Adds a College
	 *
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(CollegeDTO dto) throws DuplicateRecordException {
		log.debug("CollegeServiceSpringImpl Method Add Start");
		CollegeDTO cdto = null;

		try {
			cdto = findByName(dto.getName());
			if (cdto != null) {
				throw new DuplicateRecordException("College already Exist");
			}
		} catch (DataBaseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		long pk = collegeDao.add(dto);
		log.debug("CollegeServiceSpringImpl Method Add End");
		return pk;
	}

	/**
	 * Updates a College
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException : throws when updated College is already
	 *                                  exists
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(CollegeDTO dto) throws DuplicateRecordException {
		log.debug("CollegeServiceSpringImpl Method Update Start");
		collegeDao.update(dto);
		log.debug("CollegeServiceSpringImpl Method Update End");
	}

	/**
	 * Deletes a College
	 *
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {
		log.debug("CollegeServiceSpringImpl Method Delete Start");
		collegeDao.delete(id);
		log.debug("CollegeServiceSpringImpl Method Delete End");
	}

	/**
	 * Finds College by Name
	 *
	 * @param name : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public CollegeDTO findByName(String name) throws DataBaseException {
		log.debug("CollegeServiceSpringImpl Method FindByName Start");
		CollegeDTO dto = collegeDao.findByName(name);
		log.debug("CollegeServiceSpringImpl Method FindByName End");
		return dto;
	}

	/**
	 * Finds record by Primary Key
	 */
	@Transactional(readOnly = true)
	public CollegeDTO findByPK(long pk) throws DataBaseException {
		log.debug("CollegeServiceSpringImpl Method FindByPk Start");
		CollegeDTO dto = null;
		dto = collegeDao.findByPk(pk);
		log.debug("CollegeServiceSpringImpl Method FindByPk End");
		return dto;
	}

	/**
	 * Searches Colleges with pagination
	 *
	 * @return list : List of Colleges
	 * @param dto      : Search Parameters
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */

	@Transactional(readOnly = true)
	public List search(CollegeDTO dto, int pageNo, int pageSize) throws DataBaseException {
		log.debug("CollegeServiceSpringImpl Method search Start");
		List list = null;
		list = collegeDao.search(dto, pageNo, pageSize);
		log.debug("CollegeServiceSpringImpl Method search End");
		return list;
	}

	/**
	 * Searches Colleges
	 *
	 * @return list : List of Colleges
	 * @param dto : Search Parameters
	 * @throws ApplicationException
	 */
	@Transactional(readOnly = true)
	public List search(CollegeDTO dto) throws DataBaseException {
		log.debug("CollegeServiceSpringImpl Method search Start");
		List list = null;
		list = collegeDao.search(dto);
		log.debug("CollegeServiceSpringImpl Method search end");
		return list;
	}

}
