package in.co.sunrays.proj0.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.TimeTableDAOInt;
import in.co.sunrays.proj0.dto.TimeTableDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

/**
 * Session facade of Timetable Service. It is transactional, apply delcarative
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
@Service("timeTableService")
public class TimeTableServiceImpl implements TimeTableServiceInt {
	Logger log = Logger.getLogger(TimeTableServiceImpl.class);
	@Autowired
	private TimeTableDAOInt timeTableDao = null;

	/**
	 * Add method a TimeTable
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(TimeTableDTO dto) throws DuplicateRecordException {
		log.debug(" TimeTableServiceImpl method Add start");
		long pk = 0;

		try {
			TimeTableDTO dtoExist1 = timeTableDao.findByTimeTableDublicacy(dto.getCourseId(), dto.getSubjectId(),
					dto.getDate());
			TimeTableDTO dtoExist2 = timeTableDao.findByTimeTableDublicacy(dto.getCourseId(), dto.getSemester(),
					dto.getDate());

			TimeTableDTO dtoExist3 = timeTableDao.findByTimeTableDublicacy(dto.getCourseId(), dto.getSemester(),
					dto.getSubjectId());

			if (dtoExist1 != null || dtoExist2 != null || dtoExist3 != null) {
				throw new DuplicateRecordException("TimeTable Already Exist");
			}

			/*
			 * if (dtoExist2 != null) { throw new
			 * DuplicateRecordException("TimeTable Already Exist"); }
			 * 
			 * if (dtoExist3 != null) { throw new
			 * DuplicateRecordException("TimeTable Already Exist"); }
			 */
		} catch (DataBaseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		pk = timeTableDao.add(dto);
		log.debug(" TimeTableServiceImpl method Add End");
		return pk;
	}
	/**
	 * Update method a TimeTable
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(TimeTableDTO dto) throws DuplicateRecordException {
		log.debug(" TimeTableServiceImpl method Update start");
		try {
			TimeTableDTO dtoExist1 = timeTableDao.findByTimeTableDublicacy(dto.getCourseId(), dto.getSubjectId(),
					dto.getDate());
			TimeTableDTO dtoExist2 = timeTableDao.findByTimeTableDublicacy(dto.getCourseId(), dto.getSemester(),
					dto.getDate());

			TimeTableDTO dtoExist3 = timeTableDao.findByTimeTableDublicacy(dto.getCourseId(), dto.getSemester(),
					dto.getSubjectId());
			if (dtoExist1 != null && dtoExist1.getId() != dto.getId()) {
				throw new DuplicateRecordException("Time Table Already Exist");
			}
			if (dtoExist2 != null && dtoExist2.getId() != dto.getId()) {
				throw new DuplicateRecordException("Time Table Already Exist");
			}
			if (dtoExist2 != null && dtoExist2.getId() != dto.getId()) {
				throw new DuplicateRecordException("Time Table Already Exist");
			}

		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timeTableDao.update(dto);
		log.debug(" TimeTableServiceImpl method Update End");
	}
	/**
	 * Delete method a TimeTable
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {
		log.debug(" TimeTableServiceImpl method Delete start");
		timeTableDao.delete(id);
		log.debug(" TimeTableServiceImpl method Delete End");
	}

	public TimeTableDTO findByLogin(String login) throws DataBaseException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * findByPK method a TimeTable
	 * 
	 * @param pk
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public TimeTableDTO findByPK(long pk) throws DataBaseException {
		log.debug(" TimeTableServiceImpl method findByPK start");
		TimeTableDTO dto = null;
		dto = timeTableDao.findByPk(pk);
		log.debug(" TimeTableServiceImpl method findByPK End");
		return dto;
	}
	/**
	 * Search with pagination method a TimeTable
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public List search(TimeTableDTO dto, int pageNo, int pageSize) throws DataBaseException {
		log.debug(" TimeTableServiceImpl method Search  with pagination start");
		List list = null;
		list = timeTableDao.search(dto, pageNo, pageSize);
		log.debug(" TimeTableServiceImpl method Search with pagination End");
		return list;
	}
	/**
	 * Search with method a TimeTable
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DataBaseException
	 */
	@Transactional(readOnly = true)
	public List search(TimeTableDTO dto) throws DataBaseException {
		log.debug(" TimeTableServiceImpl method Search start");
		List list = null;
		list = timeTableDao.search(dto);
		log.debug(" TimeTableServiceImpl method Search End");
		return list;
	}

}
