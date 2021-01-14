package in.co.sunrays.proj0.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.MarksheetDAOInt;
import in.co.sunrays.proj0.dto.MarksheetDTO;
import in.co.sunrays.proj0.dto.StudentDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

/**
 * Session facade of Marksheet Service. It is transactional, apply delcarative
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
@Service("marksheetService")
public class MarksheetServiceSpringImpl implements MarksheetServiceInt {
	Logger log = Logger.getLogger(MarksheetServiceSpringImpl.class);
	@Autowired
	private MarksheetDAOInt marksheetDao = null;
	@Autowired
	private StudentServiceInt studentservice = null;
	/**
	 * Adds a Marksheet
	 *
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(MarksheetDTO dto) throws DuplicateRecordException {
		log.debug("MarksheetServiceSpringImpl method add start");
		long pk = 0;

		try {
			MarksheetDTO dtoExist = marksheetDao.findByRollNumber(dto.getRollnumber());
			if (dtoExist != null) {
				throw new DuplicateRecordException("Roll_no already exist");
			}
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		StudentDTO sdto = null;
		try {
			sdto = studentservice.findByPK(dto.getStudentId());
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dto.setStudentName(sdto.getFirstName());
		pk = (Long) marksheetDao.add(dto);
		log.debug("MarksheetServiceSpringImpl method add End");
		return pk;
	}
	/**
	 * Update a Marksheet
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(MarksheetDTO dto) throws DuplicateRecordException {
		log.debug("MarksheetServiceSpringImpl method update start");
		StudentDTO sdto = null;
		try {
			sdto = studentservice.findByPK(dto.getStudentId());
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dto.setStudentName(sdto.getFirstName());

		marksheetDao.update(dto);
		log.debug("MarksheetServiceSpringImpl method update End");
	}
	/**
	 * Delete a Marksheet
	 *
	 * @param id-long
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {
		log.debug("MarksheetServiceSpringImpl method delete start");
		marksheetDao.delete(id);
		log.debug("MarksheetServiceSpringImpl method delete end");
	}
	/**
	 * FindByrollno a Marksheet
	 *
	 * @param String rollno
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public MarksheetDTO findByRollNumber(String rollNo) throws DataBaseException {
		log.debug("MarksheetServiceSpringImpl method findByRollno start");
		MarksheetDTO dto = marksheetDao.findByRollNumber(rollNo);
		log.debug("MarksheetServiceSpringImpl method findByRollno End");
		return dto;
	}
	/**
	 * FindByLogin a Marksheet
	 *
	 * @param String Login
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public MarksheetDTO findByLogin(String login) throws DataBaseException {
		return null;
	}
	/**
	 * FindByPk a Marksheet
	 *
	 * @param pk
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public MarksheetDTO findByPK(long pk) throws DataBaseException {
		log.debug("MarksheetServiceSpringImpl method findByPk start");
		MarksheetDTO dto = marksheetDao.findByPk(pk);
		log.debug("MarksheetServiceSpringImpl method findByPk End");
		return dto;
	}
	/**
	 * Search a Marksheet
	 *
	 * @param dto
	 * @param pageNo
	 * @param PageSize
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public List search(MarksheetDTO dto, int pageNo, int pageSize) throws DataBaseException {
		log.debug("MarksheetServiceSpringImpl method Search with Pagination start");
		List list = marksheetDao.search(dto, pageNo, pageSize);
		log.debug("MarksheetServiceSpringImpl method Search with Pagination End");
		return list;
	}
	/**
	 * Search a Marksheet
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public List search(MarksheetDTO dto) throws DataBaseException {
		log.debug("MarksheetServiceSpringImpl method Search start");
		List list = marksheetDao.search(dto);
		log.debug("MarksheetServiceSpringImpl method Search start");
		return list;
	}
	/**
	 * meritList a Marksheet
	 *
	 * @param pageNo
	 * @param pageSize
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public List meritList(int pageNo, int pageSize) throws DataAccessException {
		// TODO Auto-generated method stub
		log.debug("MarksheetServiceSpringImpl method List start");
		List list = null;
		list = marksheetDao.meritList(pageNo, pageSize);
		log.debug("MarksheetServiceSpringImpl method List End");
		return list;
	}

}
