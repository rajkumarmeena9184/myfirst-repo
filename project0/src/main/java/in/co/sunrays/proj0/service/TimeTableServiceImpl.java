package in.co.sunrays.proj0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.TimeTableDAOInt;
import in.co.sunrays.proj0.dto.TimeTableDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

@Service("timeTableService")
public class TimeTableServiceImpl implements TimeTableServiceInt {

	@Autowired
	private TimeTableDAOInt timeTableDao = null;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(TimeTableDTO dto) throws DuplicateRecordException {
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
		return pk;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(TimeTableDTO dto) throws DuplicateRecordException {
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
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {
		timeTableDao.delete(id);
	}

	public TimeTableDTO findByLogin(String login) throws DataBaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public TimeTableDTO findByPK(long pk) throws DataBaseException {
		TimeTableDTO dto = null;
		dto = timeTableDao.findByPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List search(TimeTableDTO dto, int pageNo, int pageSize) throws DataBaseException {
		List list = null;
		list = timeTableDao.search(dto, pageNo, pageSize);
		return list;
	}

	@Transactional(readOnly = true)
	public List search(TimeTableDTO dto) throws DataBaseException {
		List list = null;
		list = timeTableDao.search(dto);
		return list;
	}

}
